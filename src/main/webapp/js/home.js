
window.onload = () => {
    const valid = document.getElementsByClassName("userLiked");
    let thumb = document.getElementsByClassName("fa fa-thumbs-o-up");


    for (let i = 0; i < valid.length; i++) {

        if(valid[i].innerHTML === "true") {
            console.log(thumb[i]);
            console.log(valid[i]);
            thumb[i].style.color = "#1977f2";
        }else {
            console.log(thumb[i]);
            console.log(valid[i]);

        }
    }
}

//edit post
function edit(post_Id){
    window.location.href = "/edit.jsp?post="+post_Id;
}

//comment on post
function com(post_Id){
    alert("i got here");
     window.location.href = 'CommentServlet';
    // window.location.href = "/comment.jsp";
}

//like on post
function like(post_Id, user_Id){
    const URL = "/LikeServlet";
    let like = document.getElementById(post_Id).style.color;
    console.log(like);

    if(like === "rgb(25, 119, 242)"){

        console.log("decrement");
        const valid = document.getElementsByClassName("thumb");
        document.getElementById(post_Id).style.color = "#ffffff";

        for (let i = 0; i < valid.length; i++) {
            let newId = valid[i].innerHTML.split(" ")[0];

            if(newId === post_Id){
                let like = Number(document.getElementsByClassName("likes")[i].innerHTML);
                like--;
                document.getElementsByClassName("likes")[i].innerHTML = like+"";
                console.log( document.getElementsByClassName("likes")[i]);

                const data = {post_Id, user_Id, "action": 0}
                ajaxCall(URL, data);
            }
        }
    }else{
        console.log("increment");

        const valid = document.getElementsByClassName("thumb");
        document.getElementById(post_Id).style.color = "#1977f2";

        for (let i = 0; i < valid.length; i++) {
            let newId = valid[i].innerHTML.split(" ")[0];

            if(newId === post_Id){
                let like = Number(document.getElementsByClassName("likes")[i].innerHTML);
                like++;
                document.getElementsByClassName("likes")[i].innerHTML = like+"";

                const data = {post_Id, user_Id, "action": 1}
                ajaxCall(URL, data);
            }
        }
    }
}

function ajaxCall(url, dataCall){
    $.ajax({
        type: 'POST',
        url: url,
        data: dataCall,

        success: function(data){
            //console.log(data);
        },
        error: function(){
            // alert('error liking');
        }
    });
}

//delete post
function del(post_Id){
    console.log("working");

    const delPost = confirm("Are you sure you want to delete post");

    if(delPost){
        $.ajax({
            type: 'POST',
            url: '/DeletePostServlet',
            data: {"post_Id": post_Id},

            success: function(data){
                console.log(data);
                alert(data);
                window.location.reload();
            },
            error: function(){
                // alert('error deleting post');
            }
        });
    }
}