// Get the modal
const modal_pictures = document.getElementById('myModal');

// Get the image and insert it inside the modal_pictures - use its "alt" text as a caption
const img1 = document.getElementById('myImg1');
const img2 = document.getElementById('myImg2');
const img3 = document.getElementById('myImg3');

const modalImg = document.getElementById("img01");
const captionText = document.getElementById("caption");

function neki(img){
    modal_pictures.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = img.alt;
}

if(img1!= null) img1.onclick = function(){neki(img1);};
if(img2!= null) img2.onclick = function(){neki(img2);};
if(img3!= null) img3.onclick = function(){neki(img3);};

// Get the <span_pictures> element that closes the modal_pictures
const span_pictures = document.getElementsByClassName("close")[0];

// When the user clicks on <span_pictures> (x), close the modal_pictures
span_pictures.onclick = function() {
    modal_pictures.style.display = "none";
};

