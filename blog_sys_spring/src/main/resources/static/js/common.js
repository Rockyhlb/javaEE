// ajaxSend() ⽅法在 AJAX 请求开始时执⾏函数
// event - 包含 event 对象
// xhr - 包含 XMLHttpRequest 对象
// options - 包含 AJAX 请求中使⽤的选项
// 并且ajax是基于Jquery实现的，因此在html中的引用应该放在jquery.min.js下面
$(document).ajaxSend(function (e, xhr, opt) {
    var user_token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_token_header", user_token);
});

function getUserInfo(userUrl) {
    $.ajax({
        type: "get",
        url: userUrl,
        success: function (result) {
            console.log(result);
            if (result.code == 200 && result.data != null) {
                $(".left .card h3").text(result.data.userName);
                $(".left .card a").attr("href", result.data.githubUrl);
            }
        }
    });
}

function logout() {
    // 直接将本地存储的token移除
    localStorage.removeItem("user_token");
    location.href = "blog_login.html";
}