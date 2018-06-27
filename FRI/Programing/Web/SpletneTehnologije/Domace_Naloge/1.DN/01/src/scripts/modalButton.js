
function displayInput(){
    if (localStorage.getItem("name") === null){
        const extender = $('#extender');
        extender.removeClass('small');
        extender.addClass('big');
    }else{
        const name = localStorage.getItem("name");
        const nameExtender = $('#nameExtender');
        $('#nameLabel').text("Pozdravljen nazaj " + name + "!");
        nameExtender.removeClass('small');
        nameExtender.addClass('big');
    }
}


$( document ).ready(function() {


    if (localStorage.getItem("radButton") != null) {
        //check the right button
        const rButton = localStorage.getItem("radButton");
        if     (rButton == "drzave"){$("#drzave").prop("checked", true);}
        else if(rButton == "mesta"){$("#mesta").prop("checked", true);}
        else if(rButton == "reke"){$("#reke").prop("checked", true);}

        displayInput();

    }

    $('.click').click(function() {
        localStorage.setItem("radButton", this.id);


        displayInput();
    });
});

function showPopup() {
    const popup = document.getElementById("myPopup");

    popup.classList.toggle("show");
    setTimeout(function(){
        // toggle back after 1 second
        popup.classList.toggle("show");
    },2000);

}

const CLASSNAME = function () {
    let poskusov = 5;
    let stPoskusov = 1;

    CLASSNAME.prototype.displayHtml = function (nameInputValue) {


        const nameInputShow = document.getElementById("nameInputShow");
        const drzave = ["Avstrija", "Južna Korea", "Afganistan", "Rusija", "Češka", "Avstralija", "Kanada", "Brazilija", "Čile"];
        const mesta = ["London", "Bangkok", "Pariz", "Dubaj", "Istanbul", "New York", "Singapur", "Seul", "Amsterdam", "Berlin"];
        const reke = ["Amazonka", "Nil", "Jangce", "Rumena reka", "Niger", "Volga", "Ind", "Ljubljanica", "Sora", "Sava"];

        let pravaBeseda = "";
        let zvrst = "";

        function chooseWord() {
            const ugib = document.querySelector('input[name = "ugib"]:checked').value; //!!!!!
            if (ugib == 'drzave') {
                pravaBeseda = drzave[Math.floor(Math.random()*drzave.length)];
                zvrst = "drzave";
            } else if (ugib == 'mesta') {
                pravaBeseda = mesta[Math.floor(Math.random()*mesta.length)];
                zvrst = "mesta";
            } else if (ugib == 'reke') {
                pravaBeseda = reke[Math.floor(Math.random()*reke.length)];
                zvrst = "reke";
            }
        }

        chooseWord(); //poklices jo tle



        //PRINT STRING OF ARRAY NA HTML
        let arr = [];
        if (zvrst == "drzave") arr = drzave;
        else if (zvrst == "mesta") arr = mesta;
        else arr = reke;

        $('#nameInputShow').html(
            "<div class='dropContainers'>" +
                "<div style='display:inline-block'>" +
                    "<div style='display:inline-block; padding:50px'>" +
                        "Hello <b><span id='nameInputValueField'></span></b>!<br> " +
                        "Na voljo imas <span class='poskusov'></span> poskusov <br><br>" +
                        "Lahko pricnes z ugibanjem<br>" +
                        "<input id='notranjiText' type='text'>   <br>" +
                        "<a class='innerButton'>Ugani</a>" +

                        "<article class ='wrong'></article>"+
                    "</div>"
                    +
                    "<div style='display:inline-block; padding-left:50px'>" +
                        "besede na izbiro so:<br>" +
                        "<table class ='tableId' style='border: 1px solid black; border-collapse: collapse; '></table>"+
                    "</div>"+

                    "<div style='display:inline-block; padding-left:50px'>" +
                        "<a class='cheatsButton'>Cheats</a>" +
                        "<a class='sortButton'>Sort</a>" +
                    "</div>" +
                "</div>"+
            "</div>"
        );

        const table = $(".tableId");
        for (let i = 0; i < arr.length; i++ ) {
            const emp = arr[i];
            const line = $("<tr></tr>");
            line.append( $( "<td style='border: 1px solid black'></td>" ).html( emp ) );
            table.append( line );
        }

        $(".poskusov").text(poskusov);



        $("#nameInputValueField").text(nameInputValue);
        $("#nameInputValueNonModal").text(nameInputValue);



        $('.cheatsButton').click(function () {
            $('.wrong').text("Dolzina besede je: " + pravaBeseda.length + ".");
            poskusov = 1;
            $(".poskusov").text(poskusov);

        });

        $('.sortButton').click(function () {
            //DELETE ROW
            arr.sort();
            table.empty(); //REDRAW TABLE
            for (let i = 0; i < arr.length; i++ ) {
                const emp = arr[i];
                const line = $("<tr></tr>");
                line.append( $( "<td style='border: 1px solid black'></td>" ).html( emp ) );
                table.append( line );
            }


        });

        $('.innerButton').click(function () { //rab bit po elementu k klikneš gor

            //TOJ ZA NOTRANJI TEKST FIELD ZA UGANKO
            /*UGOTOVI */
            const notranjiText = $('#notranjiText');
            const notranjiTextNonModal = $('#notranjiTextNonModal');
            let str;
            str = notranjiText.val() == "" ? notranjiTextNonModal.val(): notranjiText.val();

            if (str == pravaBeseda) {
                alert("BRAVO BESEDA JE PRAVA - st poskusov " + stPoskusov);
            }
            else if (str < pravaBeseda) {
                $('.wrong').text("Prava beseda je vecja po abecedi");

                poskusov--;
                stPoskusov++;
                $(".poskusov").text(poskusov);

                if (poskusov == 0) {

                    const r = confirm("u died - hočeš igrati še enkrat?");
                    if (r == true) {
                        modal.style.display = "none";
                        showUgankaForm();
                        onButtonClick(2);

                    }
                }
            }else {
                $('.wrong').text("Prava beseda je manjsa po abecedi");
                poskusov--;
                stPoskusov++;
                $(".poskusov").text(poskusov);
                if (poskusov == 0){
                    const r = confirm("u died - hočeš igrati še enkrat?");
                    if (r == true) {
                        modal.style.display = "none";
                        showUgankaForm();
                        onButtonClick(2)
                    }

                }
            }


            //DELETE ROW

            for (let i = 0; i < arr.length; i++){

                if(arr[i] ==  str){
                    arr.splice(i, 1);
                    //table.deleteRow(i);
                    //alert("lol");
                }
            }
            table.empty(); //REDRAW TABLE
            for (let i = 0; i < arr.length; i++ ) {
                const emp = arr[i];
                const line = $("<tr></tr>");
                line.append( $( "<td style='border: 1px solid black'></td>" ).html( emp ) );
                table.append( line );
            }



        });
    }
};





function skrijUgankaForm(){ //ko kliknemo na button se skrije form
    //skrij uganka form
    const radioButtons = $('#radioButtons');
    const extender = $('#extender');
    const nameExtender = $('#nameExtender');

    radioButtons.removeClass('big');
    radioButtons.addClass('small');

    extender.removeClass('big');
    extender.addClass('small');

    nameExtender.removeClass('big');
    nameExtender.addClass('small');

}

function showUgankaForm(){
    const radioButtons = $('#radioButtons');
    const extender = $('#extender');
    const nameExtender = $('#nameExtender');

    radioButtons.removeClass('small');
    radioButtons.addClass('big');

    nameExtender.removeClass('small');
    nameExtender.addClass('big');

}


function onButtonClick(button){
    let nameInputValue;
    if(button == 2){nameInputValue = localStorage.getItem("name");
    }else{
        nameInputValue = $('#nameInput').val();
        localStorage.setItem("name", nameInputValue);
    }


    const radioButtons = $('#radioButtons').val();

    if( nameInputValue ) { //ce je ksna vrednost vpisana

        skrijUgankaForm();




        const winWidth = $(window).width();

        if (winWidth < 768){
            const quizExtender = $('#quizExtender');
            quizExtender.removeClass('small');
            quizExtender.addClass('big');

        }else{
            modal.style.display = "block";

        }

        const className = new CLASSNAME();
        className.displayHtml(nameInputValue);



    }else{
        showPopup();
    }
}

const btn = document.getElementsByClassName("myQuizBtn")[0];
const btn1 = document.getElementsByClassName("myQuizBtn")[1];
const modal = document.getElementById('myModalButton');
const span = document.getElementsByClassName("closeButton")[0];

btn.onclick =  function() {onButtonClick(1)};
btn1.onclick = function() {onButtonClick(2)};
span.onclick = function() {
    modal.style.display = "none";
    showUgankaForm();
};

