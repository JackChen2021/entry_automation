$(function(){
    $("#publishBtn").click(publish);
//    绑定单机事件,单机时掉publish方法
});

function publish() {
    $("#publishModal").modal("hide");
    //把弹出框隐藏

    // 获取标题和内容
    var title = $("#recipient-name").val();
// <label for="recipient-name" class="col-form-label">标题：</label>
//取标题的值
    var content = $("#message-text").val();
    // 发送异步请求(POST)
    $.post(
        //访问路径 ,标题,内容
    CONTEXT_PATH + "/discuss/add",
        //回调函数
        {"title":title,"content":content},
        //data字符串解析为对象
        function(data) {
            data = $.parseJSON(data);
            // 在提示框中显示返回消息
            $("#hintBody").text(data.msg);
            // 显示提示框
            $("#hintModal").modal("show");
            // 2秒后,自动隐藏提示框
            setTimeout(function(){
                $("#hintModal").modal("hide");
                // 刷新页面
                if(data.code == 0) {
                    window.location.reload();
                }
            }, 2000);
        }
    );

}