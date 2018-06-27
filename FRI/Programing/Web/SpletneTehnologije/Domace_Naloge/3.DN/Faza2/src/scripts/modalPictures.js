// Get the modal
const modal_pictures = document.getElementById('myModal');

// Get the image and insert it inside the modal_pictures - use its "alt" text as a caption

for(let i = 0; i < arr.length; i++){

    const img = document.getElementById(arr[i]);

    console.log("image is :" + (img === null) ? "null": "not null");

    if (img !== null) img.onclick = function () {
        console.log("lala");
        neki(img);
    }
}


const modalImg = document.getElementById("img01");
const captionText = document.getElementById("caption");

function neki(img) {

    try{
        $("#navbar_for_hide").hide();
    }catch(e){}
    modal_pictures.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = img.alt;
}


// Get the <span_pictures> element that closes the modal_pictures
const span_pictures = document.getElementsByClassName("close")[0];

// When the user clicks on <span_pictures> (x), close the modal_pictures
span_pictures.onclick = function () {
    modal_pictures.style.display = "none";

    try{
        $("#navbar_for_hide").show();
    }catch(e){}
};

