<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div title="查询职位" style="padding: 20px">
    <table id="dg" class="easyui-datagrid" title="职位信息表"
           style="width: 100%; height: 500px" url="SelectJobServlet"
           data-options="singleSelect:true,collapsible:true,method:'post'">
        <thead>
        <tr>
            <th data-options="field:'jobId',align:'center',resizable:false"
                width="10%">职位编号
            </th>
            <th data-options="field:'jobName',align:'center',resizable:false"
                width="20%">职位名称
            </th>
            <th data-options="field:'jobRemark',align:'center',resizable:false"
                width="70%">职位备注
            </th>
        </tr>
        </thead>
        <c:forEach var="c" items="${requestScope.pagebean.list}"
                   varStatus="status">
            <tr>
                <td></td>
                <td>${c.jobId}</td>
                <td>${c.jobName}</td>
                <td>${c.jobRemark}</td>
            </tr>
        </c:forEach>
    </table>
    <div style="text-align: center; margin-top: 20px">
        共[${pagebean.totalrecord }]条记录, 每页 <input type="text" id="pagesize"
                                                  value="${pagebean.pagesize }"
                                                  onchange="changesize(this.value,${pagebean.pagesize })"
                                                  style="width: 30px" maxlength="2">条, 共[${pagebean.totalpage }]页,
        当前[${pagebean.currentpage }]页 &nbsp; <a class="easyui-linkbutton"
                                                href="javascript:void(0)" onclick="gotopage(1)">首页</a> <a
            class="easyui-linkbutton" href="javascript:void(0)"
            onclick="gotopage(${pagebean.previouspage })">上一页</a>
        <c:forEach var="pagenum" items="${pagebean.pagebar}">
            <c:if test="${pagenum==pagebean.currentpage}">
                <font color='red'>${pagenum }</font>
            </c:if>

            <c:if test="${pagenum!=pagebean.currentpage}">
                <a class="easyui-linkbutton" href="javascript:void(0)"
                   onclick="gotopage(${pagenum })">${pagenum }</a>
            </c:if>
        </c:forEach>
        <a class="easyui-linkbutton" href="javascript:void(0)"
           onclick="gotopage(${pagebean.nextpage })">下一页</a> <a
            class="easyui-linkbutton" href="javascript:void(0)"
            onclick="gotopage(${pagebean.totalpage })">尾页</a> <input type="text"
                                                                     id="pagenum" style="width: 30px"/> <input
            type="button"
            value=" GO "
            onclick="gotopage(document.getElementById('pagenum').value)"/>
    </div>
</div>

</body>
<script type="text/javascript">
    function gotopage(currentpage) {
        if (currentpage < 1 || currentpage != parseInt(currentpage) || currentpage > '${pagebean.totalpage}') {
            alert("请输入有效值！！");
            document.getElementById("pagenum").value = '';
        } else {
            var pagesize = document.getElementById("pagesize").value;
            window.location.href = '${pageContext.request.contextPath}/SelectJobServlet?currentpage=' + currentpage + '&pagesize=' + pagesize;
        }
    }

    function changesize(pagesize, oldvalue) {
        if (pagesize < 0 || pagesize != parseInt(pagesize)) {
            alert("请输入合法值！！");
            document.getElementById("pagesize").value = oldvalue;
        } else {
            window.location.href = '${pageContext.request.contextPath}/SelectJobServlet?pagesize=' + pagesize;
        }
    }


    $('#dg').datagrid({
        view: detailview,
        detailFormatter: function (index, row) {
            return '<div class="ddv"></div>';
        },
        onExpandRow: function (index, row) {
            var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
            ddv.panel({
                border: false,
                cache: true,
                href: 'show/show_job.jsp?index=' + index,
                onLoad: function () {
                    $('#dg').datagrid('fixDetailRowHeight', index);
                    $('#dg').datagrid('selectRow', index);
                    $('#dg').datagrid('getRowDetail', index).find('form').form('load', row);
                }
            });
            $('#dg').datagrid('fixDetailRowHeight', index);
        }
    });

    function cancelItem(index) {
        var row = $('#dg').datagrid('getRows')[index];
        if (row.isNewRecord) {
            $('#dg').datagrid('deleteRow', index);
        } else {
            $('#dg').datagrid('collapseRow', index);
        }
    }

    function saveItem(index) {
        var row = $('#dg').datagrid('getRows')[index];
        var url = "${pageContext.request.contextPath}/UpdateJobServlet";
        $('#dg').datagrid('getRowDetail', index).find('form').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (data) {
                if (data == 0) {
                    $.messager.alert('更新失败', '职位更新数据失败', 'error');
                } else {
                    data = eval('(' + data + ')');
                    data.isNewRecord = false;
                    $('#dg').datagrid('collapseRow', index);
                    $('#dg').datagrid('updateRow', {
                        index: index,
                        row: data
                    });
                    $.messager.alert('更新成功', '职位更新数据成功', 'info');
                }
            }
        });
    }

    function deleteItem(index) {
        var row = $('#dg').datagrid('getRows')[index];
        var url = "${pageContext.request.contextPath}/DeleteJobServlet";
        $.messager.confirm('确认', '确定要删除：' + row.jobName + '吗？', function (flag) {
            if (flag) {
                $('#dg').datagrid('getRowDetail', index).find('form').form('submit', {
                    url: url,
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        if (data == 0) {
                            $.messager.alert('删除失败', '职位删除失败', 'error');
                        } else if (data == 2) {
                            $.messager.alert('删除失败', '该职位作为员工表的外键', 'error');
                        } else {
                            $('#dg').datagrid('deleteRow', index);
                            $.messager.alert('更新成功', '职位删除成功', 'info');
                        }
                    }
                });
            }
        });
    }

</script>


</html>
