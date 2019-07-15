<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.wuyi.dao.impl.DeptDaoImpl" %>
<%@ page import="cn.wuyi.dao.impl.JobDaoImpl" %>
<%@ page import="cn.wuyi.domain.Dept" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.wuyi.domain.Job" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加员工</title>
    <link rel="stylesheet" type="text/css"
          href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    List<Dept> deptList = new DeptDaoImpl().findAllDept();
    List<Job> jobList = new JobDaoImpl().findAllJob();
    request.setAttribute("deptlist", deptList);
    request.setAttribute("joblist", jobList);
%>
<div style="margin: 20px 0;"></div>
<div class="easyui-panel" title="添加用户"
     style="width: 1000px; height: 700px;">
    <div style="padding: 40px 60px 20px 60px;">
        <form id="ff" class="easyui-form" method="post">
            <table cellpadding="10">
                <tr>
                    <td>姓名：</td>
                    <td><input class="easyui-textbox" type="text" name="employName"
                               data-options="required:true"></td>
                    <td>性别：</td>
                    <td><select class="easyui-combobox" name="sex"
                                style="width: 120px;">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select></td>
                    <td>卡号：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="cartId" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>部门：</td>
                    <td><select class="easyui-combobox" name="deptId"
                                style="width: 120px;">
                        <c:forEach var="dept" items="${requestScope.deptlist}">
                            <option value="${dept.deptId}">${dept.deptName}</option>
                        </c:forEach>
                    </select></td>
                    <td>职位：</td>
                    <td><select class="easyui-combobox" name="jobId"
                                style="width: 120px;">
                        <c:forEach var="job" items="${requestScope.joblist}">
                            <option value="${job.jobId}">${job.jobName}</option>
                        </c:forEach>
                    </select></td>

                    <td>电子邮箱：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="email" data-options="required:true, validType:'email'"></td>
                </tr>
                <tr>
                    <td>邮政编码：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="postCode"></td>
                    <td>联络电话：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="tel" required="true"></td>
                    <td>手机号码：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="phone" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>QQ号码：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="qqNum"></td>
                    <td>政治面貌：</td>
                    <td><input class="easyui-combobox" name="party"
                               data-options="valueField:'text',textField:'text',url:'data/party.json'"></td>
                    <td>地址：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="address" data-options="required:true"></td>
                </tr>
                <tr>

                    <td>出生年月：</td>
                    <td><input class="easyui-datebox" type="text"
                               name="birthday" required="true"></td>
                    <td>民族：</td>
                    <td><input class="easyui-combobox" name="race"
                               data-options="valueField:'text',textField:'text',url:'data/race.json'"></td>
                    <td>学历：</td>
                    <td><input  class="easyui-combobox" name="education"
                               data-options="valueField:'text',textField:'text',url:'data/education.json'"></td>
                </tr>
                <tr>

                    <td>专业：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="speciality" required="true"></td>
                    <td>爱好：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="hobby"></td>
                    <td>备注：</td>
                    <td><input class="easyui-textbox" type="text"
                               name="remark"></td>

                </tr>

            </table>
        </form>

        <div style="text-align: center; padding: 20px; margin-top: 50px">
            <a href="javascript:void(0)" class="easyui-linkbutton"
               onclick="submitForm()">注册</a> &nbsp;&nbsp;&nbsp; <a
                href="javascript:void(0)" class="easyui-linkbutton"
                onclick="clearForm()">重置</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    //easyui使用的是ajax做表单提交
    function submitForm() {
        $('#ff').form('submit', {
            url : "AddEmployeeServlet",
            onSubmit : function() {
                return $(this).form('enableValidation').form('validate');
            },
            success : function(data) {
                if (data == "1") {
                    //easyui的信息提示框：1.标题，2.提示信息，3.图标
                    $.messager.alert('注册成功', '恭喜你,注册成功', 'info');
                } else {
                    $.messager.alert('注册失败', '对不起,注册失败了', 'error');
                }
            }

        });
    }
    //置空的方法
    function clearForm() {
        $('#ff').form('clear');
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
