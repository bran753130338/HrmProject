<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加部门</title>
    <link rel="stylesheet" type="text/css"
          href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div style="margin: 20px 0;"></div>
<div class="easyui-panel" title="添加职位"
     style="width: 700px; height: 500px;">
    <div style="padding: 40px 60px 20px 60px;">
        <form id="fb" class="easyui-form" method="post">
            <table cellpadding="10">
                <tr>
                    <td>职位名称:</td>
                    <td><input class="easyui-textbox" type="text" name="jobName"
                               data-options="required:true"></td>
                </tr>
                <tr>
                    <td>职位备注:</td>
                    <td><input class="easyui-textbox" name="jobRemark" data-options="multiline:true" style="width: 400px;height: 200px"></td>
                </tr>
            </table>
        </form>

        <div style="text-align: center; padding: 20px; margin-top: 50px">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="submitForm()">添加</a> &nbsp;&nbsp;&nbsp; <a
                href="javascript:void(0)" class="easyui-linkbutton"
                onclick="clearForm()">重置</a>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">

    //置空的方法
    function clearForm() {
        $('#fb').form('clear');
    }
    function submitForm(){
        $('#fb').form('submit',{
            url : "AddJobServlet",
            onSubmit:function () {
                return $(this).form('enableValidation').form('validate');
            },
            success:function (data) {
                if (data=="1") {
                    //easyui的信息提示框：1.标题，2.提示信息，3.图标
                    $.messager.alert('添加成功', '恭喜你,添加成功', 'info');
                }else if(data=="2") {
                    $.messager.alert('添加失败', '该职位名称已存在', 'error');
                }else{
                    $.messager.alert('添加失败', '对不起,添加失败了', 'error');
                }
            }
        })

    }

</script>
</body>
</html>
