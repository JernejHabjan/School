<?php

class ViewHelper_8 {

    //Displays a given view and sets the $variables array into scope.
    public static function render($file, $variables = array()) {

        //kaj naredi extract: ---iz teksta naredi variable
        /*
         $variables = [
         "test"->"neka vrednost"
        ]
        extract($variables);
        echo $test;
         */
        extract($variables);

        ob_start(); //da se vsi echoti v buffer zapisujejo in ne direkt
                //ce damo ob_get_clean -- se vse kar je v bufferju izpiše
        include($file);
        $renderedView = ob_get_clean();

        echo $renderedView;
    }

    // Redirects to the given URL
    public static function redirect($url) {
        header("Location: " . $url);
    }

    // Displays a simple 404 message
    public static function error404() {
        header('This is not the page you are looking for', true, 404);
        $html404 = sprintf("<!doctype html>\n" .
                            "<title>Error 404: Page does not exist</title>\n" .
                            "<h1>Error 404: Page does not exist</h1>\n".
                            "<p>The page <i>%s</i> does not exist.</p>", $_SERVER["REQUEST_URI"]);

        echo $html404;
    }
}
