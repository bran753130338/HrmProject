<%@ page import="cn.wuyi.service.impl.AnnoServiceImpl" %>
<%@ page import="cn.wuyi.service.AnnoService" %>
<%@ page import="cn.wuyi.domain.Anno" %>
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
<%
    int index = Integer.parseInt(request.getParameter("index"));
    AnnoService annoService = new AnnoServiceImpl();
    Anno anno = annoService.findById(index);
    request.setAttribute("anno",anno);
%>
<form id="anno" method="post">
    <table  style="width:100%;background:#fafafa;padding:5px;margin-top:5px;">
        <tr>
            <td>编号：</td>
            <td><input name="id" class="easyui-textbox" required="true" data-options="readonly:true" value="${anno.id}" style="width: 30%"></td>
        </tr>
        <tr>
            <td>公告标题：</td>
            <td><input name="title" class="easyui-textbox" required="true" value="${anno.title}" style="width: 30%"></td>
        </tr>
        <tr>
            <td>公告内容：</td>
            <td><input name="content" class="easyui-textbox" multiline="true" value="${anno.content}" style="width: 80%;height: 400px"></td>
        </tr>
        <tr>
            <td>发布日期：</td>
            <td><input name="date" class="easyui-datetimebox" value="${anno.date}" required="true" style="width: 30%"></td>
        </tr>
    </table>
    <div style="padding:5px 0;text-align:right;padding-right:30px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveItem()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="cancelItem()">取消</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="deleteItem()">删除</a>
    </div>
</form>
<script>
    function cancelItem(){
        $.messager.confirm('确认', '确定要取消编辑吗？', function (flag){
            if(flag){
                $('#win').window('close');
            }
        });

    }
    function saveItem(){
        var url = "${pageContext.request.contextPath}/UpdateAnnoServlet";
        $('#anno').datagrid().form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(data){
                if(data==0){
                    $.messager.alert('更新失败', '职位更新数据失败', 'error');
                }else{
                    $.messager.alert('更新成功', '职位更新数据成功', 'info');
                    $("#win").window('close');
                    $("#dg").datagrid('reload');
                }
            }
        })
        
    }
    function deleteItem(){
        var url = "${pageContext.request.contextPath}/DeleteAnnoServlet";
        $.messager.confirm('确认', '确定要删除该公告吗？', function (flag){
            if(flag){
                $('#anno').datagrid().form('submit', {
                    url: url,
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        if (data == 0) {
                            $.messager.alert('删除失败', '公告删除失败', 'error');
                        }else {
                            $.messager.alert('更新成功', '公告删除成功', 'info');
                            $("#win").window('close');
                            $("#dg").datagrid('reload');
                        }
                    }
                });
            }
        });
    }
</script>
</body>

</html>
