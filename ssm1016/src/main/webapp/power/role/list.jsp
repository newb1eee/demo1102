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
        function del(rid){
            var rs=confirm("确认删除？");
            if(rs==true){
                location.href="/power/role/deleterole?rid="+rid;
            }
        }

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》角色管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
			<a style="text-decoration: none;" href="/power/role/getallmenus">【新增角色】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        角色名称
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col" width="300px">
                        操作
                    </th>
                </tr>
                
               
                <c:forEach items="${pi.list}" var="r">
                <tr align="center">
                    <th><input type="checkbox"/></th>
                    <td>
                        ${r.roleid}
                    </td>
                    <td>
                       ${r.rolename}
                    </td>                    
                    <td>&nbsp;
                        ${r.rolestate==1?"启动":"禁用"}
                    </td>
                    
                    <td>&nbsp;

                        <%--<c:if test="${r.rolestate==1}">
                            <a href="javascript:alert('操作成功！');">禁用</a>
                        </c:if>
                        <c:if test="${r.rolestate==2}">
                            <a href="javascript:alert('操作成功！');">禁用</a>

                        </c:if>--%>
                        <a href="javascript:alert('操作成功！');">
                                ${r.rolestate==1?"禁用":"启用"}
                        </a>
                        <a href="">详情</a>

                        <a href="/power/role/findbyrid?rid=${r.roleid}">修改</a>
                        <c:if test="${r.roleid!=u1.role.roleid}">
                        <a href="javascript:void(0)" onclick="del(${r.roleid});return false" class="tablelink"> 删除</a>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>

                <tr>
                    <td colspan="20" style="text-align: center;">
                        <a style="text-decoration: none;" href="#">

                            <fy:fy url="/power/role/getall" pageInfo="${pi}"></fy:fy>

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
                                location.href="/power/role/getall?size="+size;
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