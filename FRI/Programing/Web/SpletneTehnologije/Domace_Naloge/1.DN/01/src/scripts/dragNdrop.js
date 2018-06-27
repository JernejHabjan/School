function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}



function dragNDrop_showPopup(index) {
    const pic_popups =["myPopup_pic1", "myPopup_pic2", "myPopup_pic3"];

    const popup = document.getElementById(pic_popups[index]);

    popup.classList.toggle("show");
    setTimeout(function(){
        // toggle back after 1 second
        popup.classList.toggle("show");
    },2000);

}



function drop(ev) {


    ev.preventDefault();
    const data = ev.dataTransfer.getData("text");

    if (ev.target.id == "img1_div" && data == "myImg1" ||
        ev.target.id == "img2_div" && data == "myImg2" ||
        ev.target.id == "img3_div" && data == "myImg3") {


        ev.target.appendChild(document.getElementById(data));

        let caption_name = "";
        if(ev.target.id == "img1_div") caption_name = "#img1_caption";
        if(ev.target.id == "img2_div") caption_name = "#img2_caption";
        if(ev.target.id == "img3_div") caption_name = "#img3_caption";

        $(caption_name).removeClass('small');
        $(caption_name).addClass('big');

        document.getElementById(data).setAttribute("draggable","false");

    }
    if (ev.target.id == "img1_div" && data != "myImg1"){dragNDrop_showPopup(0);}
    if (ev.target.id == "img2_div" && data != "myImg2"){dragNDrop_showPopup(1);}
    if (ev.target.id == "img3_div" && data != "myImg3"){dragNDrop_showPopup(2);}


}