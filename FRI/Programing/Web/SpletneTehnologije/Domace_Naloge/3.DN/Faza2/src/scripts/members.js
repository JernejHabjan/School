//UPORABNIKI SLIDESHOW\\


function checkForButtonsOnOff() {

    if ($('#divContainer').width() + parseInt($(".container").css('marginLeft')) + $('#buttonright').width() < $('#everything').width()) {
        $('#buttonright').css({
            'display': 'none'
        });
        $('#buttonleft').css({
            'display': 'none'
        });
    } else {
        $('#buttonright').css({
            'display': 'initial'
        });
        $('#buttonleft').css({
            'display': 'initial'
        });
    }
}


$(window).resize(function () {
    checkForButtonsOnOff();

});


$(document).ready(function () {
    checkForButtonsOnOff();
    /*ob initu preveriš za gumbe*/


    const buttonright = $("#buttonright");
    const divContainer = $("#divContainer");
    const container = $(".container");
    const header_logo = $(".header-logo");


    buttonright.click(function () {
        if (divContainer.width() + parseInt(container.css('marginLeft')) + buttonright.width() >= $('#everything').width()) {/*če div večji od  širine bodyja*/
            const positionDiv = divContainer.position().left - parseFloat(container.css('marginLeft'));
            const toMove = divContainer.width();
            let newLeft = positionDiv + toMove;


            const $el = divContainer;
            const maxLeft = $el.position().left - header_logo.width();
            newLeft = newLeft < 0 ? 0 : newLeft > maxLeft ? maxLeft : newLeft;
            if (newLeft > -divContainer.width() + header_logo.width()) {
                $el.animate({left: newLeft});
            } else {
                if (newLeft > -divContainer.width()) {
                    $el.animate({left: -divContainer.width() + header_logo.width()});
                    /*da ga zamakne za čist mal*/
                }
            }

        }


    });
    $("#buttonleft").click(function () {
        const $el = divContainer;
        let newLeft = $el.position().left + header_logo.width();
        if (newLeft < 0) {
            $el.animate({left: newLeft});
        } else {
            $el.animate({left: 0});
            /*drugače ga reseta na nulo*/
        }
    });
});


/*slider*/
$(function () {
    $('#divContainer').slider();
});

$.fn.slider = function () {

    return this.each(function () {
        const $el = $('#divContainer');
        const $el1 = $('#leftRightMover');
        $el.css('left', 0);
        let dragging = false;
        let startX = 0;
        let startT = 0;
        $el1.mousedown(function (ev) {
            dragging = true;
            startX = ev.clientX;
            startT = $el.css('left');
        });
        $(window).mousemove(function (ev) {
            if (dragging) {
                // calculate new left
                let newLeft = parseInt(startT) + (ev.clientX - startX);

                //stay in parent
                const maxLeft = $el.parent().width() - $el.width();
                newLeft = newLeft < 0 ? 0 : newLeft > maxLeft ? maxLeft : newLeft;
                const sirinaBodyja = ($(".main").width() - parseFloat($(".container").css('marginLeft')));
                if (sirinaBodyja < $("#divContainer").width()) {

                    $el.animate({left: newLeft});
                } else {
                    $el.css('left', newLeft);
                }

            }
        }).mouseup(function () {
            dragging = false;
        });
    });

};


