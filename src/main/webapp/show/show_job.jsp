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
            <td>职位编号：</td>
            <td><input name="jobId" class="easyui-textbox" required="true" data-options="readonly:true"></td>
            <td>职位名称：</td>
            <td><input name="jobName" class="easyui-textbox" required="true"></td>
        </tr>
        <tr>
            <td>职位备注</td>
            <td><input name="jobRemark" class="easyui-textbox" multiline="true"></td>
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
