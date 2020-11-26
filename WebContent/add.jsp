<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>租房网 -发布房屋信息</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="scripts/jquery.js"></script><!--jquery-->
<script type="text/javascript" src="scripts/jquery.validate.min.js"></script><!--validate插件，用于在前端进行表单验证-->
<script type="text/javascript">

function toUrl(url){	
	window.location.href = url;
	return;
}

	$().ready(function(){
		$("#houseForm").validate({
			rules:{
				title:"required",
				type_id:"required",
				price:{
					required:true,
					number:true
				},
				floorage:"number",
				district_id:"required",
				street_id:"required",
				contact:"required"
			},
			messages: {
				title:"请输入标题",
				type_id:"请输入房屋类型",
				price:"请设置房租,房租必须为数值",
				floorage:"面积必须为数值",
				district_id:"请选择所在区",
				street_id:"请选择所在街道",
				contact:"请输入联系电话"
			}
		})
	});
	
	//点击发布按钮
	//**验证， **提交
	function pubClick(){
		//验证
		
		//提交
		document.getElementById("houseForm").submit();
	}
</script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
</div>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<dl class="clearfix">
			<dt>新房屋信息发布</dt>
			<dd class="past">填写房屋信息</dd>
		</dl>
		<div class="box">
			<s:form id="houseForm" action="house_add">				
				<input type="hidden" value="<s:property value="#session.currentUser.id"/>" id="currentUser"/>
				<div class="infos">
					<table class="field">
						<tr>
							<td class="field">标　　题：</td>
							<td>
							<label for="title"></label>
							<s:textfield cssClass="text" key="title" name="title"/><!-- title -->
							</td>
						</tr>
						<tr>
							<td class="field">户　　型：</td>  <!-- type -->
							<td>
								<label for="type_id"></label>
								<select  class="text" name="type_id">
									<option value="1000">单间</option>
									<option value="1001">一室一厅</option>
									<option value="1002">两室一厅</option>
									<option value="1003">两室两厅</option>
									<option value="1004">三室一厅</option>
									<option value="1005">三室两厅</option>
									<option value="1006">四室一厅</option>
									<option value="1007">四室两厅</option>
									<option value="1008">四室三厅</option>
								</select>
							</td>
						</tr>
						<tr>						
							<td class="field">面　　积：</td> <!-- floorage -->
							<td>
								<label for="floorage"></label>
								<s:textfield cssClass="text" key="floorage" name="floorage"/>
							</td>
						</tr>
						<tr>
							<td class="field">价　　格：</td><!-- price -->
							<td>
								<label for="price"></label>
								<s:textfield cssClass="text" key="price" name="price"/>
							</td>
						</tr>
						 <!-- 
						<tr>							
							<td><input type="text" class="text" name="houseDate" /></td>
						</tr>
						 -->
                        <tr>
							<td class="field">位　　置：</td><!-- 地址 distritc, street -->
							<td>区： <select  class="text" name="district_id">
										<option value="1003">天河区</option>
									</select>
                            	
                            	街：<select  class="text" name="street_id">
                            			<option value="1000">体育西路</option>
                            			<option value="1005">沐陂大街</option>
                            			<option value="1006">中山大道</option>
                            	   </select>
                            </td>
						</tr>
						<!-- 
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						-->
						<!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
                        <tr>
							<td class="field">联系方式：</td><!-- contact -->
							<td>
								<label for="contact"></label>
								<s:textfield cssClass="text" key="contact" name="contact"/>
							</td>
						</tr>
                        <tr>
							<td class="field">详细信息：</td><!-- destription -->
							<td><textarea name="description"></textarea></td>
						</tr>
					</table>
					<div class="buttons">
					<input type="button" value="立即发布" onclick="pubClick()"/>
					<input type="button" value="返回房屋管理页" onclick="toUrl('house_show')"/><!--请求显示当前用户的所有房屋信息-->
					</div>
				</div>
			</s:form>
		<s:property value="message"/><!--显示结果-->
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	<dl>
        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
    </dl>
</div>
</body>
</html>