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
		confirm("确认删除？");
	}

</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》考试</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> <a
				style="text-decoration: none" href="/Educational/exam/detDepts">【新增考试】</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/exam/getexamlist" method="post">
					<li>
						<label>考试科目</label>
						<input name="examsubject" type="text" class="scinput1" value="${esub}"/>&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
					<th scope="col">编号</th>
					<th scope="col">考试编号</th>
					<th scope="col">考试科目</th>
					<th scope="col">考试时间</th>
					<th scope="col">考试班级</th>
					<th scope="col">考试状态</th>
					<th scope="col">操作</th>
				</tr>

                <c:forEach items="${pi.list}" var="e">
				<tr align="center">
					<td>${e.examid}</td>
					<td>${e.examnum}</td>
					<td>${e.examsubject}</td>
					<td>${e.examtime}</td>
					<td>${e.classes.classname}</td>
					<td>${e.examstate}</td>
					<td>
                        <c:if test="${e.examstate=='已结束'}">
                            <a href="view.html">详细</a>
                            <a href="list.jsp">考试成绩</a>
                            <a href="reAdd.html">组织补考</a>
                        </c:if>
                        <c:if test="${e.examstate=='进行中'}">
                            <a href="view.html">详细</a>
                        </c:if>
                        <c:if test="${e.examstate=='准备中'}">
                            <a href="add.jsp">修改</a>
                            <a href="javascript:void(0)" onclick="del();return false" class="tablelink"> 删除</a>
                            <a href="view.html">详细</a>
                        </c:if>
					</td>
				</tr>
                </c:forEach>

				 <tr>
                        <td colspan="20" style="text-align: center;">						
						<a style="text-decoration: none;" href="#">

							<fy:fy url="/Educational/exam/getexamlist?examsubject=${esub}" pageInfo="${pi}"></fy:fy>


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
