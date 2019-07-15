<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
<%  int index = Integer.parseInt(request.getParameter("index"));%>
<form method="post">
    <table class="dv-table" style="width:100%;background:#fafafa;padding:5px;margin-top:5px;">
        <tr>
            <td>编号：</td>
            <td><input name="id" class="easyui-textbox" required="true" data-options="readonly:true"></td>
            <td>姓名：</td>
            <td><input name="username" class="easyui-textbox" data-options="required:true"></td>
        </tr>
        <tr>
            <td>登录名：</td>
            <td><input name="loginname" class="easyui-textbox" required="true" data-options="required:true"></td>
            <td>密码:</td>
            <td><input class="easyui-textbox" name="password"
                       data-options="required:true"></td>
        </tr>
        <tr>
            <td>注册日期：</td>
            <td><input name="createdate" class="easyui-datetimebox" required="true" ></td>
            <td>角色：</td>
            <td><select id="cc" class="easyui-combobox" name="status"
                        style="width: 120px;">
                <option value="0">公司员工</option>
                <option value="1">普通管理员</option>
                <option value="2">超级管理员</option>
            </select></td>
        </tr>
    </table>
    <div style="padding:5px 0;text-align:right;padding-right:30px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveItem(<%=index%>)">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancelItem(<%=index%>)">取消</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteItem(<%=index%>)">删除</a>
    </div>
</form>
</body>
</html>
