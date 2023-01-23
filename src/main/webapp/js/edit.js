window.onload = ()=> {
    const params = new URLSearchParams(window.location.search);
    document.getElementById("input").value =  params.get("get");
    document.getElementById("input").disabled = true;
}