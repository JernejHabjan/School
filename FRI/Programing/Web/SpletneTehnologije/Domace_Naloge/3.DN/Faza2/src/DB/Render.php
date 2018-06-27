<?php

/**
 * Created by PhpStorm.
 * User: Jernej Habjan
 * Date: 27. 04. 2017
 * Time: 17:57
 */
class Render
{
    public static function displayTopic($results, $display_doda_komentar = true, $display_oceni_komentar = true)
    {
        ?>
        <table style="border:1px solid grey;" align="center">
            <tr>
                <th>ID</th>
                <th>Naslov</th>
                <th style="width: 500px;">Besedilo</th>

                <th>Avtorjev id</th>
                <th>Avtor</th>
                <th>Datum objave</th>
                <th style="width: 100px;">Slika</th>
                <?php if ($display_doda_komentar) {
                    echo "<th>Dodaj komentar</th>";
                }
                if ($display_oceni_komentar) {
                    echo "<th>Oceni komentar</th>";
                } ?>

            </tr>
            <?php
            foreach ($results as $result) {
                $comment_topic_id = $result['topic_id'];
                $comment_id = $result['topic_content_id'];
                $topic_name = $result['topic_name'];
                $topic_date = $result['topic_date'];
                $comment_text = $result['content_text'];
                $comment_photo = $result['content_photo'];
                $user_id = $result['user_id'];
                $user_fullname = $result['fullname'];
                $topic_votes = $result['topic_votes'];
                $topic_downvotes = $result['topic_downvotes'];

                echo "<tr><td>$comment_id</td><td>$topic_name</td><td>$comment_text</td><td>$user_id</td><td><a href='../FRONT/member.php?id=$user_id'>" . $user_fullname . "</a></td><td>$topic_date</td>";

            if ($comment_photo != "") {//display photo
                $path = '../photos/topicPhotos/' . $comment_id . $comment_photo;
                ?>
                <script>arr.push("<?=$comment_id . 'img'?>");</script>

            <?php

            echo "<td><img id = $comment_id.'img' src=$path alt = 'content_photo' style='max-width: 100px; max-height: 100px; border-radius: 5px; cursor: pointer; transition: 0.3s;'></td>";
            } else {
                echo "<td>no image</td>";
            }
            if ($display_doda_komentar)
                echo "<td><form method=\"get\" action=\"../FRONT/new_topic.php?action=comment&id=\"<?=$comment_topic_id?>
                <input type=\"submit\" name=\"add_comment\" value=\"Dodaj komentar\"/>
            </form></td>";
            if ($display_oceni_komentar):
            ?>
                <td><span class="vote" id='<?= $comment_id ?>vote'><?= $topic_votes ?></span><span
                            class="down_vote" id='<?= $comment_id ?>downvote'>
                        <div style="-ms-transform: rotate(180deg); /* IE 9 */
    -webkit-transform: rotate(180deg); /* Safari */
    transform: rotate(180deg) translate(20px);"><?= $topic_downvotes ?></div></span></td>
                <?php
            endif;
                echo "</tr>";
            }


            ?>
        </table>
        <?php
    }

    public static function display_Page()
    {
        ?>

        <h2 style="margin-top:0;padding-top:20px;">Teme:</h2>


        <table style="border:1px solid black; width:90%; margin-left:5%; margin-right: 5%" align="center">

            <tr>
                <th>ID</th>
                <th>Naslov</th>
                <th style="width: 500px;">Besedilo</th>
                <th style="width: 100px;">Slika</th>
                <th>Datum</th>

            </tr>
            <?php
            if (isset($_GET["submit_najnovejse"]) && $_GET["submit_najnovejse"] != null) { //DISPLAY NEWEST FIRST
                $result = DBTopics_Slivko::getTopics(0, "", "", false, true, "topic_date");
            } else if (isset($_GET["submit_najbolj_popularne"]) && $_GET["submit_najbolj_popularne"] != null) { // DISPLAY DESC BY SCORE
                $result = DBTopics_Slivko::getTopics(0, "", "", false, true, "topic_votes");
            } else if (isset($_GET["submit_dolocen_dan"]) && $_GET["submit_dolocen_dan"] != null) { // DISPLAY ONLY ONE DAY
                $result = DBTopics_Slivko::getTopics(0, "", "", false, false, "", $_GET["date_input"]);
                echo "<a href='index.php'>Prikaži nazaj vse teme</a>";
            } else {
                $result = DBTopics_Slivko::getTopics(0, "", "", false);
            }


            $start = 0;
            $max = 5;
            $count = 0;


            if (isset($_GET["prev_page"]) && !empty($_GET["prev_page"])) {
                $max = $_GET["pageMax"] - 10;
                $start = $_GET["pageMax"] - 5;

                if ($max < 5) {
                    $max = 5;
                    $start = 0;
                }
            } else if (isset($_GET["next_page"]) && !empty($_GET["next_page"])) {

                $max = $_GET["pageMax"] + 5;
                $start = $_GET["pageMax"];

                if ($max > count($result)) {
                    $max = count($result);
                    $start = $_GET["pageMax"] + 5 % 5;

                    if ($start < 0) $start = 0;
                }

            }


            foreach ($result as $row) {
                if ($start <= $count && $count < $max) {
                    $topic_id = $row['topic_id'];
                    $topic_name = $row['topic_name'];
                    $topic_date = $row['topic_date'];
                    $content_text = $row['content_text'];
                    $content_photo = $row['content_photo'];
                    $is_comment = $row['is_comment'];

                    $path = 'src/photos/topicPhotos/' . $topic_id . $content_photo;

                    if (!$is_comment):
                        ?>
                        <tr>
                            <td><?= $topic_id ?></td>
                            <td><a href='src/FRONT/topic.php?id=<?= $topic_id ?>'><?= $topic_name ?></a></td>

                            <td>
                                <article><?= $content_text ?></article>
                            </td>

                            <script>arr.push("<?=$topic_id . 'img'?>");</script>
                            <td><img id="<?= $topic_id . 'img' ?>" src=<?= $path ?> alt = '<?= $topic_name ?>'
                                style ='max-width: 100px
                                ;
                                    max-height: 100px
                                ;
                                    border-radius: 5px
                                ;
                                    cursor: pointer
                                ;
                                    transition: 0.3s
                                ;'></td>

                            <td>

                                <form action="index.php" method="GET">
                                    <input type="hidden" name="date_input" value="<?= $topic_date ?>"/>
                                    <input class="dropbtn active custom" type="submit" name="submit_dolocen_dan"
                                           value="<?= $topic_date ?>">
                                </form>

                            </td>
                        </tr>

                    <?php endif;
                }
                $count++;
            }


            ?>


        </table>
        <?php
        $page = ceil($max / 5);
        $numPages = ceil(count($result) / 5);

        ?>
        <center>
            <form action='index.php' method="GET">
                <input type="hidden" name="pageMax" value='<?= $max ?>'>
                <table style="margin:auto" align="center">
                    <tr>
                        <?php


                        if ($page > 1):?>

                            <i style="font-size:24px" class="fa">&#xf053;</i><input class="dropbtn"
                                                                                    style="padding: 10px;background-color:gray"
                                                                                    type="submit"
                                                                                    name="prev_page"
                                                                                    value="Prejšna stran">
                        <?php endif;

                        if (count($result) > $max) : //dodej gumb next in redirect na ta gumb
                            ?>
                            <input class="dropbtn" style="padding: 10px;background-color:gray" type="submit"
                                   name="next_page" value="Naslednja stran"><i style="font-size:24px" class="fa">&#xf054;</i>

                        <?php endif; ?>

                    </tr>
                </table>
            </form>
        </center>
        <?php

        if ($numPages == 0) $page = 0;
        echo "<span style='margin-left:5%'>Prikazana $page. stran od $numPages strani.</span>";

    }

}
