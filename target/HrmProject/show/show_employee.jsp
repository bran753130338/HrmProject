<%@ page import="cn.wuyi.dao.impl.JobDaoImpl" %>
<%@ page import="cn.wuyi.dao.impl.DeptDaoImpl" %>
<%@ page import="cn.wuyi.domain.Job" %>
<%@ page import="cn.wuyi.domain.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%
    request.setCharacterEncoding("UTF-8");
    List<Dept> deptList = new DeptDaoImpl().findAllDept();
    List<Job> jobList = new JobDaoImpl().findAllJob();
    request.setAttribute("deptlist", deptList);
    request.setAttribute("joblist", jobList);
%>
<form method="post">
    <table class="dv-table" style="width:100%;background:#fafafa;padding:5px;margin-top:5px;">
        <tr>
            <td>员工编号：</td>
            <td><input class="easyui-textbox" type="text" name="employId"
                       data-options="required:true,readonly:true"></td>
            <td>员工姓名：</td>
            <td><input class="easyui-textbox" type="text" name="employName"
                       data-options="required:true"></td>
            <td>员工性别：</td>
            <td><select class="easyui-combobox" name="sex"
                        style="width: 120px;">
                <option value="1">男</option>
                <option value="2">女</option>
            </select></td>

        </tr>
        <tr>
            <td>员工卡号：</td>
            <td><input class="easyui-textbox" type="text"
                       name="cartId" data-options="required:true"></td>
            <td>员工部门：</td>
            <td><select class="easyui-combobox" name="deptId"
                        style="width: 120px;">
                <c:forEach var="dept" items="${requestScope.deptlist}">
                    <option value="${dept.deptId}">${dept.deptName}</option>
                </c:forEach>
            </select></td>
            <td>员工职位：</td>
            <td><select class="easyui-combobox" name="jobId"
                        style="width: 120px;">
                <c:forEach var="job" items="${requestScope.joblist}">
                    <option value="${job.jobId}">${job.jobName}</option>
                </c:forEach>
            </select></td>


        </tr>
        <tr>
            <td>电子邮箱：</td>
            <td><input class="easyui-textbox" type="text"
                       name="email" data-options="required:true, validType:'email'"></td>
            <td>邮政编码：</td>
            <td><input class="easyui-textbox" type="text"
                       name="postCode"></td>
            <td>联络电话：</td>
            <td><input class="easyui-textbox" type="text"
                       name="tel" required="true"></td>

        </tr>
        <tr>
            <td>手机号码：</td>
            <td><input class="easyui-textbox" type="text"
                       name="phone" data-options="required:true"></td>
            <td>QQ号码：</td>
            <td><input class="easyui-textbox" type="text"
                       name="qqNum"></td>
            <td>政治面貌：</td>
            <td><input class="easyui-combobox" name="party"
                       data-options="valueField:'text',textField:'text',url:'data/party.json'"></td>

        </tr>
        <tr>
            <td>地址：</td>
            <td><input class="easyui-textbox" type="text"
                       name="address" data-options="required:true"></td>
            <td>出生年月：</td>
            <td><input class="easyui-datebox" type="text"
                       name="birthday" required="true"></td>
            <td>民族：</td>
            <td><input class="easyui-combobox" name="race"
                       data-options="valueField:'text',textField:'text',url:'data/race.json'"></td>

        </tr>
        <tr>
            <td>学历：</td>
            <td><input  class="easyui-combobox" name="education"
                        data-options="valueField:'text',textField:'text',url:'data/education.json'"></td>

            <td>专业：</td>
            <td><input class="easyui-textbox" type="text"
                       name="speciality" required="true"></td>
            <td>爱好：</td>
            <td><input class="easyui-textbox" type="text"
                       name="hobby"></td>

        </tr>
        <tr>
            <td>创建日期：</td>
            <td><input class="easyui-datetimebox" type="text"
                       name="createDate"></td>
            <td>备注：</td>
            <td><input class="easyui-textbox" type="text"
                       name="remark"></td>
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
