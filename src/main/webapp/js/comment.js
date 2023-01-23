
function del(postId, userId, data){
    const comment = document.getElementsByClassName("comment");

    console.log(postId);
    console.log(comment);
    console.log(data);

    let delCom = confirm("Are sure you want to delete this comment");

    if(delCom){
        $.ajax({
            type: 'POST',
            url: '/DeleteCommentServlet',
            data: {"postId": postId, "userId":userId},

            success: function(data){
                alert(data);
                window.location.reload();
            },
            error: function(){
                // alert('error deleting post');
            }
        });
    }
}

function edit(postId, userId){
    console.log("working edit");

    const editedComment = document.getElementsByClassName("edit-comment");

    for (let i = 0; i < editedComment.length; i++){
        let comment = editedComment[i].value.trim();
        if(comment !== ""){
            const editPost = confirm("Are you sure you want to edit comment");

            if(editPost){
                $.ajax({
                    type: 'POST',
                    url: '/EditCommentServlet',
                    data: {"postId": postId, "userId":userId, "editedComment": comment},

                    success: function(data){
                        alert(data);
                        window.location.reload();
                    },
                    error: function(){
                        // alert('error editing post');
                    }
                });
            }
        }
    }
}