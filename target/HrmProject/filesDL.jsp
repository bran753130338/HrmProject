<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <style type="text/css">
        td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%--<h1>文件下载</h1>--%>
<div style="padding: 20px">
<table id="download" class="easyui-datagrid"
       title="文件下载" style="width:100%"
       data-options="singleSelect:true,collapsible:true">
    <thead>
    <tr>
        <th data-options="field:'fileName',align:'center',resizable:false"
            width="70%">文件名</th>
        <th data-options="field:'download',align:'center',resizable:false"
            width="30%">下载</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="li">

        <tr>


                <td>
                    ${li.filename }
                </td>
                <td> <form action="${ pageContext.request.contextPath }/DownloadlistServlet"  method="post">
                    <input type="hidden" name="path" value="${li.filepath }">
                    <input type="submit" value="下载">
                    </form></td>

        </tr>

    </c:forEach>

</table>
</div>
</body>
</html>