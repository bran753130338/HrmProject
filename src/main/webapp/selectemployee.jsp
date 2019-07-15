<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工查询</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
</head>
<body>
<div title="查询员工" style="padding: 20px">
    <table id="tb" class="easyui-datagrid" title="用户信息表"
           style="width: 100%; height: 500px"
           data-options="singleSelect:true,collapsible:true">
        <thead>
        <tr>
            <th data-options="field:'employId',align:'center',resizable:false">
                员工编号
            </th>
            <th data-options="field:'deptId',align:'center',resizable:false">
                部门编号
            </th>
            <th data-options="field:'jobId',align:'center',resizable:false">
                职位编号
            </th>
            <th data-options="field:'employName',align:'center',resizable:false">
                员工姓名
            </th>
            <th data-options="field:'cartId',align:'center',resizable:false">
                卡号
            </th>
            <th data-options="field:'address',align:'center',resizable:false">
                地址
            </th>
            <th data-options="field:'postCode',align:'center',resizable:false">
                邮政编码
            </th>
            <th data-options="field:'tel',align:'center',resizable:false">
                联络电话
            </th>
            <th data-options="field:'phone',align:'center',resizable:false">
                手机号码
            </th>
            <th data-options="field:'qqNum',align:'center',resizable:false">
                QQ号码
            </th>
            <th data-options="field:'email',align:'center',resizable:false">
                电子邮件地址
            </th>
            <th data-options="field:'sex',align:'center',resizable:false">
                性别
            </th>
            <th data-options="field:'party',align:'center',resizable:false">
                政治面貌
            </th>
            <th data-options="field:'birthday',align:'center',resizable:false">
                出生年月
            </th>
            <th data-options="field:'race',align:'center',resizable:false">
                民族
            </th>
            <th data-options="field:'education',align:'center',resizable:false">
                学历
            </th>
            <th data-options="field:'speciality',align:'center',resizable:false">
                专业
            </th>
            <th data-options="field:'hobby',align:'center',resizable:false">
                爱好
            </th>
            <th data-options="field:'remark',align:'center',resizable:false">
                备注
            </th>
            <th data-options="field:'createDate',align:'center',resizable:false">
                创建日期
            </th>
        </tr>
        </thead>

        <c:forEach var="c" items="${requestScope.pagebean.list}"
                   varStatus="status">
            <tr>
                <td></td>
                <td>${c.employId}</td>
                <td>${c.deptId}</td>
                <td>${c.jobId}</td>
                <td>${c.employName}</td>
                <td>${c.cartId}</td>
                <td>${c.address}</td>
                <td>${c.postCode}</td>
                <td>${c.tel}</td>
                <td>${c.phone}</td>
                <td>${c.qqNum}</td>
                <td>${c.email}</td>
                <td>${c.sex}</td>
                <td>${c.party}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${c.birthday}" /></td>
                <td>${c.race}</td>
                <td>${c.education}</td>
                <td>${c.speciality}</td>
                <td>${c.hobby}</td>
                <td>${c.remark}</td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${c.createDate}" /></td>
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
            window.location.href = '${pageContext.request.contextPath}/SelectEmployeeServlet?currentpage=' + currentpage + '&pagesize=' + pagesize;
        }
    }

    function changesize(pagesize, oldvalue) {
        if (pagesize < 0 || pagesize != parseInt(pagesize)) {
            alert("请输入合法值！！");
            document.getElementById("pagesize").value = oldvalue;
        } else {
            window.location.href = '${pageContext.request.contextPath}/SelectEmployeeServlet?pagesize=' + pagesize;
        }
    }

    $('#tb').datagrid({
        view: detailview,
        detailFormatter: function (index, row) {
            return '<div class="ddv"></div>';
        },
        onExpandRow: function (index, row) {
            var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
            ddv.panel({
                border: false,
                cache: true,
                href: 'show/show_employee.jsp?index=' + index,
                onLoad: function () {
                    $('#tb').datagrid('fixDetailRowHeight', index);
                    $('#tb').datagrid('selectRow', index);
                    $('#tb').datagrid('getRowDetail', index).find('form').form('load', row);
                }
            });
            $('#tb').datagrid('fixDetailRowHeight', index);
        }
    });

    function cancelItem(index){
        var row = $('#tb').datagrid('getRows')[index];
        if (row.isNewRecord){
            $('#tb').datagrid('deleteRow',index);
        } else {
            $('#tb').datagrid('collapseRow',index);
        }
    }
    function saveItem(index){
        var row = $('#tb').datagrid('getRows')[index];
        var url = "${pageContext.request.contextPath}/UpdateEmployeeServlet";
        $('#tb').datagrid('getRowDetail',index).find('form').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(data){
                if(data==null){
                    $.messager.alert('更新失败', '员工更新数据失败', 'error');
                }else{
                    data = eval('('+data+')');
                    data.isNewRecord = false;
                    $('#tb').datagrid('collapseRow',index);
                    $('#tb').datagrid('updateRow',{
                        index: index,
                        row: data
                    });
                    $.messager.alert('更新成功', '员工更新数据成功', 'info');
                }
            }
        });
    }
    function deleteItem(index){
        var row = $('#tb').datagrid('getRows')[index];
        var url = "${pageContext.request.contextPath}/DeleteEmployeeServlet";
        $.messager.confirm('确认', '确定要删除 员工编号：'+row.employId+'   员工姓名：' + row.employName + '吗？', function (flag){
            if(flag){
                $('#tb').datagrid('getRowDetail',index).find('form').form('submit', {
                    url: url,
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (data) {
                        if (data == 0) {
                            $.messager.alert('删除失败', '员工删除失败', 'error');
                        } else {
                            $('#tb').datagrid('deleteRow', index);
                            $.messager.alert('更新成功', '员工删除成功', 'info');
                        }
                    }
                });
            }
        });
    }
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
    $.fn.datebox.defaults.parser = function(date){
        var t = Date.parse(date);
        if (!isNaN(t)){
            return new Date(t);
        } else {
            return new Date();
        }
    }
</script>
</html>
