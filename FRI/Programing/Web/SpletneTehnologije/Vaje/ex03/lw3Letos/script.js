"use strict";

function doubleClickRow(event){
    const table = document.getElementById("participant-table");
    const row = event.target.parentElement;

    const first = row.getElementsByTagName("td")[0].innerText;
    const last = row.getElementsByTagName("td")[1].innerText;
    const role = row.getElementsByTagName("td")[2].innerText;

    const toDelete = window.confirm(`confirm deletion of ${first} ${last} ${role}`);
    if(toDelete) table.removeChild(row);
}

function domAddParticipant(participant) {

    //create
    const table = document.getElementById("participant-table");
    const tr = document.createElement("tr");
    tr.ondblclick = doubleClickRow; //!!!!!!!!!!!!!!!!!!


    ["first","last","role"].forEach(field =>{
        const td = document.createElement("td");
        td.innerText = participant[field];
        tr.appendChild(td);
        }
    );
    table.appendChild(tr);
}

function addParticipant() {
    // TODO: Get values
    const first = document.getElementById("first").value;
    const last = document.getElementById("last").value;
    const role = document.getElementById("role").value;

    localStorage.first = first;
    localStorage.last = last;
    localStorage.role = role;


    document.getElementById("first").value = "";
    document.getElementById("last").value = "";
    document.getElementById("role").value = "";

    console.log(first,last,role);

    // Create participant object
    const participant = {
        first: first,
        last: last,
        role: role
    };

    // Add participant to the HTML
    domAddParticipant(participant);

    // Move cursor to the first name input field
    document.getElementById("first").focus();
}

$(document).ready(() => {
    if (localStorage.getItem("first") !== null) {
        document.getElementById("first").value = localStorage.first;
        document.getElementById("last").value = localStorage.last;
        document.getElementById("role").value = localStorage.role;
    }
});