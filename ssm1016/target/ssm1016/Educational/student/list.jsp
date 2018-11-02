<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fy" uri="http://java.sun.com/jsp/femye/fy" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../../css/mine.css" type="text/css" rel="stylesheet">
<script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../../Script/Common.js" type="text/javascript"></script>
<script src="../../Script/Data.js" type="text/javascript"></script>

<script>
	function del(){
		confirm("确认操作？");
	}

</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span>
                <span style="float: left;">当前位置是：教务中心-》学生管理</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="/Educational/student/detDepts">【新增学生】</a>&emsp;&emsp;&emsp;&emsp;
                </span>
            </span>
	</div>

	<div class="cztable">
		<div>
				  <form action="/Educational/student/getstudentlist" method="post">
                    学生名称: 
					<input type="text" name="stuname" value="${sname}"/>
                     学生学号: 
					<input type="text" name="studentno" value="${sno}"/>
					性别: 
					<select name="stusex">
							<option value="-1" selected="selected">--请选择--</option>
							<option value="1">男</option>
							<option value="0">女</option>
                    </select>
					<input type="submit" value="查询" />

                </form>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
                        <th >学号</th>
						<th width="">姓名</th>
						<th width="">性别</th>
                        <th width="15%">联系电话</th>						
                        <th width="15%">专业</th>
						<th width="15%">登记时间</th>
						<th>操作</th>
                    </tr>

                <c:forEach items="${pi.list}" var="stu">
                    <tr id="product1">
                        <td align="center">${stu.studentno}</td>
						<td align="center">${stu.stuname}</td>
                        <td align="center">${stu.stusex==1?'女':'男'}</td>
                        <td align="center">${stu.phone}</td>
						<td >${stu.major.majorname}</td>
                        <td align="center">${stu.regdate}</td>
						<td align="center">
							<a href="add.jsp">修改</a>
							<a href="javascript:void(0)" onclick="del();return false" class="tablelink"> 退学</a>
							<a href="view.html">详细</a>
						</td> 				                    
                    </tr>
                </c:forEach>

					
				
					
                    <tr>
                        <td colspan="20" style="text-align: center;">						
						<a style="text-decoration: none;" href="#">

							<fy:fy url="/Educational/student/getstudentlist?stuname=${sname}&studentno=${sno}&stusex=${ssex}" pageInfo="${pi}"></fy:fy>

							共${pi.total}条
							每页显示
							<select name="num">
								<option value="5" <c:if test="${size==5}">selected</c:if> >5</option>
								<option value="10" <c:if test="${size==10}">selected</c:if>>10</option>
								<option value="15" <c:if test="${size==15}">selected</c:if>>15</option>
							</select>条 &nbsp;&nbsp;&nbsp;  当前页码 ${pi.pageNum}/${pi.pages} </a>
						</td>
						<script type="text/javascript">
                            $(function(){
                                $("[name=num]").change(function(){
                                    var size=$(this).val();
                                    location.href="/Educational/class/getclasslist?size="+size;
                                });
                            })
						</script>
                    </tr>
                </tbody>
            </table>
	</div>

	</div>
	</div>

	</div>
</body>
</html>
