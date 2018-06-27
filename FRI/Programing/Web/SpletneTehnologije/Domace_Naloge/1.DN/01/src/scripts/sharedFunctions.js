/**
 * Created by Jernej Habjan on 22. 03. 2017.
 */

$(document).keydown(function(e) { // close pic view on esc key
    // ESCAPE key pressed
    if (e.keyCode == 27) {
        modal_pictures.style.display = "none";

    }
});