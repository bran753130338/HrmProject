<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>职位查询</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
</head>
<body>
<div title="查询公告" style="padding: 20px">
    <table id="dg" class="easyui-datagrid" title="公告信息表"
           style="width: 100%; height: 500px" url="SelectAnnoServlet"
           data-options="singleSelect:true,collapsible:true,method:'post',pagination:true">
        <thead>
        <tr>
            <th data-options="field:'id',align:'center',resizable:false"
                width="10%">编号
            </th>
            <th data-options="field:'title',align:'center',resizable:false"
                width="20%">公告标题
            </th>
            <th data-options="field:'content',align:'center',resizable:false"
                width="50%">公告内容
            </th>
            <th data-options="field:'date',align:'center',resizable:false"
                width="20%">发布时间
            </th>
        </tr>
        </thead>
    </table>
</div>

<div id="win" style="padding:10px;">

</div>
</body>
<script type="text/javascript">
$("#dg").datagrid({
    onDblClickRow : function (rowIndex, rowData) {
        $('#win').window({
            title:'公告内容',
            modal:true,
            href : 'show/anno_content.jsp?index='+rowData.id,
        });
        $('#win').window('resize',{
            width:'70%',
            height:'100%'
        });
        $('#win').window('center');
    },

});



$ .fn.datebox.defaults.formatter = function(date){
    var y = date.getFullYear();
    var m = date.getMonth()+ 1;
    var d = date.getDate();
    if(m<10){
        m = '0'+m;
    }
    if(d<10){
        d = '0'+d;
    }
    return y +'-'+ m +'-'+ d;
}
$.fn.datetimebox.defaults.parser = function(date){
    var t = Date.parse(date);
    if (!isNaN(t)){
        return new Date(t);
    } else {
        return new Date();
    }
}
</script>

</html>
