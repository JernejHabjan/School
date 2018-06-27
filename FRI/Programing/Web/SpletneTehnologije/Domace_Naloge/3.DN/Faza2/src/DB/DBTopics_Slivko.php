<?php

require_once "DBInit_Slivko.php";
require_once "DBChecker.php";

class DBTopics_Slivko
{

    public static function addTopic($comment_topic_id = 0)
    {//if topic id is greater than 0 -> comment

        $topic_name = @$_POST["topic_name"];
        $topic_content = @$_POST["topic_content"];
        $topic_photo = $_FILES["image"]["name"];


        $date = date("Y-m-d");

        if (isset($_POST["submit"])) {

            if ($topic_name && $topic_content) {
                if (strlen($topic_name) >= 5 && strlen($topic_name) <= 30) {


                    //ADD TOPIC

                    //INSERT INTO CONTENT TABELO
                    $mysqli = DBInit_Slivko::getInstance();
                    $insert_into_topic_content = "INSERT INTO topic_content (content_text, content_photo)VALUES ('$topic_content', '$topic_photo')";
                    $statement = $mysqli->prepare($insert_into_topic_content);
                    $statement->execute();

                    $topic_content_id = $mysqli->lastInsertId(); //its id from primary key in topics_content
                    $user_id = $_SESSION["user_id"];

                    //add photo to proper folder
                    DBTopics_Slivko::addPic("topicPhotos", $topic_content_id, $topic_photo);


                    //INSERT V VMESNO TABELO
                    $statement = $mysqli->prepare("INSERT INTO topics (user_id, topic_content_id, topic_name, topic_date, is_comment)VALUES (:user_id, :topic_id, :topic_name, :topic_date, :is_comment)");
                    $statement->bindParam(":user_id", $user_id);
                    $statement->bindParam(":topic_id", $topic_content_id);
                    $statement->bindParam(":topic_name", $topic_name);
                    $statement->bindParam(":topic_date", $date);


                    if ($comment_topic_id > 0) { //HANDLE COMMENT
                        //insert that it is comment
                        $is_comment = TRUE;
                        $statement->bindParam(":is_comment", $is_comment);
                        $statement->execute();


                        $topic_id = $mysqli->lastInsertId(); //its id from primary key in topics_content


                        //CREATE NEW COMMENT TABELO IN POVEŽI S CONTENT TABELO IN AVTORJEM
                        $statement = $mysqli->prepare("INSERT INTO comments (topic_parent_id, topic_id, user_id)VALUES (:topic_parent_id, :topic_id, :user_id)");
                        $statement->bindParam(":topic_parent_id", $_SESSION["topic_id"]); //PARENT TOPIC
                        $statement->bindParam(":topic_id", $topic_id); //last inserted id
                        $statement->bindParam(":user_id", $user_id);
                        $statement->execute();


                        echo "successfully added new comment";

                    } else { //HANDLE NORMAL TOPIC
                        $is_comment = FALSE;
                        $statement->bindParam(":is_comment", $is_comment);
                        $statement->execute();
                        echo "succcessfully added new topic";
                    }


                } else {
                    echo "dolžina naslova mora biti med 5 in 30 znaki.";
                }
            } else {
                echo "please fill out all the fields";
            }

        }

        return true;
    }

    public static function addPic($endpath, $topic_id, $filename)
    {
        if (DBChecker::checkIfPhoto()) {

            $file_tmp = $_FILES["image"]["tmp_name"];
            $filename = $topic_id . $filename;

            $image_up = "../photos/" . $endpath . "/" . $filename; //se user_id da se slike ne overrwitajo
            move_uploaded_file($file_tmp, $image_up);
        }
    }

    public static function getTopics($n = 0, $user_id = "", $topic_id = "", $is_comment = true, $desc = false, $orderby = "", $day = "")
    {

        $query = "SELECT * FROM topics JOIN topic_content ON(topics.topic_id = topic_content.topic_content_id)
        JOIN users ON(users.user_id = topics.user_id) ";

        if ($user_id != "") { //if filter by $userid
            $query = $query . " WHERE topics.user_id = $user_id ";
            if ($topic_id != "") {
                $query = $query . " AND topics.topic_id = $topic_id ";
            }
        } else if ($topic_id != "") {
            $query = $query . " WHERE topics.topic_id = $topic_id ";
        }
        if($day !=""){

            $query = $query . " WHERE topics.topic_date = '$day' ";
        }

        if($is_comment == false){
            if($day !=""){
                $query = $query . " AND topics.is_comment = FALSE ";
            }else{
                $query = $query . " WHERE topics.is_comment = FALSE ";
            }

        }

        if($desc == true){
            $query = $query . " ORDER BY topics.$orderby DESC ";
        }


        if ($n != 0) //if limit num displayed
            $query = $query . " LIMIT $n ";



        $mysqli = DBInit_Slivko::getInstance();
        $statement = $mysqli->prepare($query);
        $statement->execute();
        $result = $statement->fetchAll();

        return $result;

    }

    public static function getComment($topic_id)
    {
        $mysqli = DBInit_Slivko::getInstance();
        $query = "SELECT topic_content.*, comments_topic.*, users.fullname FROM topics AS parent_topic JOIN comments ON(parent_topic.topic_id = comments.topic_parent_id)
                JOIN topics AS comments_topic ON(comments.topic_id = comments_topic.topic_id)
                JOIN topic_content ON(comments_topic.topic_content_id = topic_content.topic_content_id)
                JOIN users ON(comments_topic.user_id = users.user_id)
                
                
                WHERE parent_topic.topic_id = '$topic_id'";


        $statement = $mysqli->prepare($query);
        $statement->execute();
        $result = $statement->fetchAll();


        return $result;


    }

    public static function changeScore($topic_id, $isupvote){
        $mysqli = DBInit_Slivko::getInstance();
        //GET USER ID
        $query = "SELECT topic_votes, topic_downvotes, users.user_id, users.score FROM topics JOIN users ON(users.user_id = topics.user_id) WHERE topic_id = '$topic_id'";
        $statement = $mysqli->prepare($query);
        $statement->execute();
        $result = $statement->fetchAll();
        $user_id = $result[0]["user_id"];
        $topic_votes = $result[0]["topic_votes"];
        $topic_downvotes = $result[0]["topic_downvotes"];
        $score = $result[0]["score"];
        //UPDATE TOPIC VOTES/DOWNVOTES

        if($isupvote){
            //update upvotes
            $query = "UPDATE topics SET topic_votes = $topic_votes+1 WHERE topic_id = '$topic_id'";
            $statement = $mysqli->prepare($query);
            $statement->execute();

            //update user
            $query = "UPDATE users SET score = $score+1 WHERE user_id = '$user_id'";
            $statement = $mysqli->prepare($query);
            $statement->execute();
        }else{
            //update downvotes
            $query = "UPDATE topics SET topic_downvotes = $topic_downvotes+1 WHERE topic_id = '$topic_id'";
            $statement = $mysqli->prepare($query);
            $statement->execute();

            //update user
            $query = "UPDATE users SET score = $score -1 WHERE user_id = '$user_id'";
            $statement = $mysqli->prepare($query);
            $statement->execute();
            $statement->execute();
        }

    }
}