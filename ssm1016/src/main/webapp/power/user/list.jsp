<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fy" uri="http://java.sun.com/jsp/femye/fy" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    <script>
		function del(){
			confirm("确定删除？");
		}
	</script>

</head>
<body>


<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》人员管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
            <a style="text-decoration: none;" href="javascript:alert('操作成功！');">【导出excel】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="javascript:alert('操作成功！');">【批量删除】</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="/power/user/add">【新增人员】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>
 <div class="cztable" style="width: 100%;">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px;" align="center">
                    
                    <th  width="8%">
						<input type="checkbox"/>
					</th>
					<th scope="col" width="15%">
                        序号
                    </th>
                    <th scope="col" width="15%">
                        账号
                    </th>
                    <th scope="col" width="15%">
                        姓名
                    </th>
                    <th scope="col" width="15%">
                        角色
                    </th>
                   
                    <th scope="col" >
                        操作
                    </th>
                </tr>
                
               <c:forEach items="${pi.list}" varStatus="sta" var="u">
                
                <tr align="center">
					<th><input type="checkbox"/></th>
                    <td>
                        ${sta.count}
                    </td>
                    
                    <td>
                      ${u.userName}
                    </td>
                    <td>
                       <a href="info.html">${u.userRealname}
                       </a>
                    </td>
                    
                    <td>&nbsp;
                       ${u.role.rolename}
                    </td>
                    
                    <td>&nbsp;
                        <a href="edit.html">修改</a>						   
						<a href="javascript:void(0)" onclick="del();return false" class="tablelink"> 删除</a>
                    </td>
                </tr>
               </c:forEach>
                <tr>
                    <td colspan="20" style="text-align: center;">
                        <a style="text-decoration: none;" href="#">

                            <fy:fy url="/power/user/getall" pageInfo="${pi}"></fy:fy>

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
                                location.href="/power/user/getall?size="+size;
                            });
                        })
                    </script>
                </tr>

            </tbody>
        </table>
        
        </div>
    </div>

        
        
      
    </div>
</body>
</html>