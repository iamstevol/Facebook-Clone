//disable buttons of the first form
let input1 = document.getElementById("input1");
let input2 = document.getElementById("input2");
let btn1 = document.getElementById("btn1");
let btn2 = document.getElementById("btn2");
let btn3 = document.getElementById("password");

function work() {
    document.getElementById("modal").className = "modal";
    document.getElementsByClassName("main-content")[0].style.opacity = "1";
    document.getElementById("modal").style.display = "none";
    input1.disabled = false;
    input2.disabled = false;
    btn1.disabled = false;
    btn2.disabled = false
}

function modal() {
    input1.disabled = true;
    input2.disabled = true;
    btn1.disabled = true;
    btn2.disabled = true;

    //decrease the opacity of the first firm
    document.getElementsByClassName("main-content")[0].style.opacity = "0.2";
    document.getElementById("modal").style.opacity = "1";
    document.getElementById("modal").style.display = "";
    // document.getElementsByClassName("modal")[0].style.display = "yyy";
    document.getElementById("modal").className = "";
}