<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传文件</title>
    <link rel="stylesheet" type="text/css"
          href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        // 项div中间添加文本框
        // function run() {
        //     var div = document.getElementById("divId");
        //     div.innerHTML += "<input style='width:300px' name='myfile'><input type='button' value='删除' onclick='del(this)'/></div>";
        // }
        $(function () {

            $("#add").click(
                function () {
                    var div = "<div style='margin-bottom:20px'><input class='file' type='text' style='width:80%' name='myfile'><a href='##' onclick='del(this)'>删除</a>";
                    $("#divId").append(div);

                    // $("input[type=text]").filebox({
                    //     buttonText: '选择文件',
                    //     buttonAlign: 'right',
                    //     prompt:'请选择文件',
                    //     required:true
                    //
                    // });
                    $(".file").each(function () {
                        if(!$(this).hasClass("filebox-f")){
                            $(this).filebox({
                                    buttonText: '选择文件',
                                    buttonAlign: 'right',
                                    prompt:'请选择文件',
                                    required:true

                            });
                        }
                    });

                    $("a[href='##']").linkbutton({
                        iconCls: 'icon-clear'
                    })
                }
            )
        });

        // 删除某一行
        function del(who) {
            // 获取删除的按钮的父节点
            var divv = who.parentNode;
            divv.parentNode.removeChild(divv); // 最外围的div
        }
    </script>

</head>
<body>
<div class="easyui-panel" title="文件上传" style="width: 700px; height: 1000px;" data-options="collapsible:true">
    <div style="padding: 40px 60px 20px 60px;">
        <form id="ff"
              method="post" enctype="multipart/form-data">
            <%--<input type="button" value="添加" id="add">&nbsp;<input--%>
                <%--type="submit" value="上传"/>--%>
                <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-upload'" onclick="submitForm()">上传文件</a>
            <div id="divId" style='margin-top:20px'>
            </div>

        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    function submitForm() {
        $('#ff').form('submit', {
            url : "${ pageContext.request.contextPath }/UploadServlet",
            onSubmit : function() {
                return $(this).form('enableValidation').form('validate');
            },
            success:function (data) {
                if(data=='0'){
                    $.messager.alert('上传失败', '没有选择文件', 'error');
                }else{
                    $.messager.alert('上传成功', '文件已经成功上传', 'info');
                    $("#divId").empty();
                }


            }

        });
    }
</script>
</html>