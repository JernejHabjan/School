/*navbar fixed after scroll*/
$(window).scroll(function () {
    if ($(this).scrollTop() >= $('.container').height()) {
        $('.navbar').css({
            'position': 'fixed',
        });
        $('.main').css({
            'margin-top': $('.navbar').height()
        });
    }
    else {
        $('.navbar').css({
            'position': 'initial'
        });
        $('.main').css({
            'margin-top': '0'
        });
    }
});

// ===== Scroll to Top Button ====
$(window).scroll(function () {
    if ($(this).scrollTop() >= 50) {        // If page is scrolled more than 50px
        $('#return-to-top').fadeIn(200);    // Fade in the arrow
    } else {
        $('#return-to-top').fadeOut(200);   // Else fade out the arrow
    }
});
$('#return-to-top').click(function () {      // When arrow is clicked
    $('body,html').animate({
        scrollTop: 0                       // Scroll to top of body
    }, 500);
});


function validateForm() {
    const x = document.forms["myForm"]["username"].value;
    const y = document.forms["myForm"]["password"].value;
    if (x === null || x === "" || y === null || y === "") {
        alert("Vpisana morata biti uporabniško ime ali email, in geslo");
        return false;
    }
}



/*dropdown on click*/

/* When the user clicks on the button,
 toggle between hiding and showing the dropdown content */
function mydropdownFunction(element) {
    let container;
    let i;
    let array;
    if ($("#" + element).is(":visible")) {
        array = [$("#loginDropdown"), $("#profilQuickShow"), $("#vec_dropdown")];
        for (i in array) {
            container = array[i];
            container.hide();
        }
        $("#" + element).hide();
    } else {
        array = [$("#loginDropdown"), $("#profilQuickShow"), $("#vec_dropdown")];
        for (i in array) {
            container = array[i];
            container.hide();
        }
        $("#" + element).show();
    }

}

// Close the dropdown if the user clicks outside of it
window.onclick = function (e) {


    if (!e.target.matches('.dropbtn')) {

        const array = [$("#loginDropdown"), $("#profilQuickShow"), $("#vec_dropdown")];
        for (let i in array) {
            const container = array[i];
            if (!container.is(e.target) && container.has(e.target).length === 0) {

                container.hide();
            }
        }

    }


};


/*NEW NAVIGATION BAR*/
function myFunction() {
    const x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}

















