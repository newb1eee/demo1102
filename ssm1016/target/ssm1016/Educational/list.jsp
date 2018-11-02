<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../css/mine.css" type="text/css" rel="stylesheet">
<script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../Script/Common.js" type="text/javascript"></script>
<script src="../Script/Data.js" type="text/javascript"></script>

<script>
	function del(){
		confirm("确认删除？");
	}

</script>

   <script type="text/javascript">
        function submitMail() {
            var mtitle = "成绩添加或修改";
            var html = "<div style='padding:10px;'><div style='width:65px; height:120px; float:left;'>修改的地方：</div><div style='width:250px; height:120px; float:left;'><textarea id='objeCont' name='objeCont' style='width:250px; height:105px;'></textarea></div></div>";

            var submit = function (v, h, f) {
				alert(v + "-" + h + "-" + f);
                if (f.objeCont == '') {
                    $.jBox.tip("请您输入成绩，且不超过3个字！", 'error', { focusId: "objeCont" }); // 关闭设置 objeCont 为焦点
                    return false;
                }

                StudentCompain.insertCompain('', mtitle, 5, f.objeCont, function (data) {
                    var obj = $.parseJSON(data);
                    var resultObj = false;
                    if (obj.ok) {
                        $.jBox.tip("成功提交成绩！");
                    }
                });
            };

            $.jBox(html, { title: "成绩添加或修改", submit: submit });
        }
    </script>




</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》考试-》考试成绩</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> <a
				style="text-decoration: none" href="exam.html">【返回】</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<div class="seachform1">
				<form action="/Educational/studentmanage" method="post">
					<li>
						<label>考试科目</label>
						<input name="examsubject" type="text" class="scinput1" value="${examsubject}"/>&nbsp;&nbsp;
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
					<th scope="col">考生姓名</th>
					<th scope="col">考生分数</th>
				</tr>

                <c:forEach items="${pi.list}" var="stu">
				<tr align="center">
					<td align="center">${stu.studentno}</td>
					<td align="center">${stu.exam.examnum}</td>
					<td align="center">${stu.exam.examsubject}</td>
					<td align="center" id="timer">${stu.exam.examtime}</td>
					<td align="center">${stu.classes.classname}</td>
					<td align="center">${stu.stuname}</td>
					<td align="center">
                        <input type="text" value="84" size="5" />${stu.exam.examcount}</td>
					
				</tr>
                </c:forEach>
				 <tr>
                        <td colspan="7" style="text-align: center;">						
						<a style="text-decoration: none;" href="#">
                            <a href="/Educational/studentmanage?examsubject=${examsubject}&index=${pi.pageNum}&size=${size}">首页</a>
                            <a href="/Educational/studentmanage?examsubject=${examsubject}&index=${pi.prePage}&size=${size}">上一页</a>
                            <c:forEach var="i" begin="1" end="${pi.pages}">
                                <a href="/Educational/studentmanage?examsubject=${examsubject}&index=${i}&size=${size}">${i}</a>
                            </c:forEach>
                            <a href="/Educational/studentmanage?examsubject=${examsubject}&index=${pi.nextPage}&size=${size}">下一页</a>
                            <a href="/Educational/studentmanage?examsubject=${examsubject}&&index=${pi.pages}&size=${size}">尾页</a>
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
