<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function userDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/userDel.action?id="+id;
               }
           }
           
           function user_dongjie(id)
           {
               if(confirm('您确定冻结吗？'))
               {
                   window.location.href="<%=path %>/user_dongjie.action?id="+id;
               }
           }
           
           function user_jiedong(id)
           {
               if(confirm('您确定解冻吗？'))
               {
                   window.location.href="<%=path %>/user_jiedong.action?id="+id;
               }
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="121" background="<%=path %>/img/tbg.gif">&nbsp;储户管理&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="8%">姓名</td>
					<td width="4%">性别</td>
					<td width="4%">年龄</td>
					<td width="8%">住址</td>
					<td width="8%">联系方式</td>
					
					<td width="8%">E-mail</td>
					<td width="8%">卡号</td>
					<td width="8%">密码</td>
					<td width="8%">身份证号</td>
					<td width="8%">余额</td>
					<td width="8%">状态</td>
					<td width="13%">操作</td>
		        </tr>	
				<s:iterator value="#request.userList" id="user">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.realname"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.sex"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#user.age"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.address"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.tel"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#user.email"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.kahao"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#user.ps"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#user.shenfenzheng"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#user.yue"/>
					</td>
					<td  bgcolor="#FFFFFF" align="center">
						<s:property value="#user.tai"/>
					</td>
					<td  bgcolor="#FFFFFF" align="center">
					    <s:if test="#user.tai=='正常'">
					        <a style="color: red" href="#"  onclick="user_dongjie(<s:property value="#user.id"/>)">冻结</a>
					    </s:if>
					    <s:if test="#user.tai=='冻结'">
					        <a style="color: red" href="#"  onclick="user_jiedong(<s:property value="#user.id"/>)">解冻</a>
					    </s:if>
						<a style="color: red" href="#"  onclick="userDel(<s:property value="#user.id"/>)">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
	</body>
</html>
