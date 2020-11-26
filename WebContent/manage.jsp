<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%//浏览器不缓存 
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Expires","0");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int total = (Integer)request.getAttribute("total");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<sx:head/>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>租房网 - 用户管理</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<script type="text/javascript" src="scripts/function.js"></script>
	<script type="text/javascript">
	
		function toUrl(url){
			window.location.href = url;
			console.log(url);
			return;
		}
		
		//更改信息
		function update(id){
			return;  //不进行任何操作
			document.location = '/HouseRent/page/modify.jsp?id=' + id;
		}
		
		//点击搜索按钮
		//将发送的参数：price street_id type_id floorage
		function doSearch(){
			//提交请求
			document.getElementById('sform').submit();
		}
	</script>
</head>
<body>
<s:property value="message"/>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="search">
		<label class="ui-green"><input onclick="toUrl('add.jsp');" type="button" name="search" value="发布房屋信息" /></label>	
		<label class="ui-green"><input type="button" name="search" value="退       出" onclick='document.location="index.jsp"'/></label>
	</div>
</div>
<div id="navbar" class="wrap">
	<dl class="search clearfix">
		<form method="post" action="house_search" id='sform'>
			<dt>
				<ul>
					<li class="bold">房屋信息</li>
					<li>
						标题：<input type="text" class="text" value='<s:property value="title"/>' name="title" />
						<label class="ui-blue"><input type="button" onclick='doSearch()' name="search" value="搜索房屋" /></label>
					</li>
				</ul>
			</dt>
			<dd>
				<ul>
					<li class="first">
						价格
					</li>
					<li>	
					    <ul class="items">
					        <li class="priceItem">
					          <input class="priceInput" name="lowPrice" placeholder="¥" type="text"/>
					        </li>
					        <li class="sep">-</li>
					        <li class="priceItem">
					          <input class="priceInput" name="highPrice" placeholder="¥" type="text"/>
					        </li>
					    </ul> 
					</li>
				</ul>
			</dd>
			<dd>
				<ul>
					<li class="first">房屋位置</li>
					<li>
							<select name='street_id'>
								<option value=''>不限</option>
								<option value='1000'>体育西路</option>
								<option value='1001'>神州路</option>
								<option value='1002'>惠福西路</option>
								<option value='1003'>机场路</option>
								<option value='1004'>先烈中路</option>
								<option value='1005'>沐陂大街</option>
								<option value='1006'>中山大道</option>
								<option value='1007'>石化路</option>
							</select>
					</li>
				</ul>
			</dd>
			<dd>
				<ul>
					<li class="first">房型</li>
					<li>
							<select name='type_id'>
								<option value=''>不限</option>
								<option value='1000'>单间</option>
								<option value='1001'>一室一厅</option>
								<option value='1002'>两室一厅</option>
								<option value='1003'>两室两厅</option>
								<option value='1004'>三室一厅</option>
								<option value='1005'>三室两厅</option>
								<option value='1006'>四室一厅</option>
								<option value='1007'>四室两厅</option>
								<option value='1008'>四室三厅</option>								
							</select>
					</li>
				</ul>
			</dd>
			<dd>
				<ul>
					<li class="first">
						面积
					</li>
					<li>
						<ul class="items">
					        <li class="priceItem">
					          <input class="priceInput" name="lowFloorage" placeholder="平米" type="text" value=""/>
					        </li>
					        <li class="sep">-</li>
					        <li class="priceItem">
					          <input class="priceInput" name="highFloorage" placeholder="平米" type="text" value=""/>
					        </li>
					    </ul> 
					</li>
				</ul>
			</dd>
		</form>
	</dl>
</div>
<div class="main wrap">
<div id="houseArea">
	<table class="house-list"><!-- table的每一行，显示一个房子的信息 -->
	<s:iterator value="houses" var="house" status="status">
		<!--如果是单行，给 添加属性  class="odd" (间色效果)-->
		<s:if test="#status.getCount()%2==0"><tr class="odd"></s:if>
		<s:else><tr></s:else>
			<td class="house-thumb"><span><a href="house_detail?id=9"><img src="images/thumb_house.gif" /></a></span></td>
			<td>
				<dl>
					<!--要求id=房屋的id-->
					<dt><a href=""><s:property value="#house.title"/><!-- 显示house.title --></a></dt>
					<dd>
						<s:property value="#house.street.district.name"/><!-- 显示house所在的区 -->区<s:property value="#house.street.name"/><!-- 显示house所在的street的名称 -->,<s:property value="#house.floorage"/><!--显示house的面积floorage-->平米<br />
						联系方式：<s:property value="#house.contact"/><!-- 显示house的联系人 -->
					</dd>
				</dl>
			</td>
			<td class="house-type"><s:property value="#house.type.name"/></td>
			<td class="house-price"><s:property value="#house.price"/></td>
			<td class="house-type"><label class="ui-green"><input type="button" onclick='' name="search" value="修    改" /></label></td>
			<td class="house-price"><label class="ui-green"><input type="button" name="search" value="删    除" /></label></td>
		</tr> 
	</s:iterator>
    	<!--如果用户未发布任何信息,则显示“该用户未发布任何房子信息”-->
    	<!--<tr></tr>-->
    	<s:if test="user.houses.size()==0"><tr><td><h3>该用户未发布任何房子信息</h3></td></tr></s:if>
	</table>
</div>
<%--<div class="pager">
		<ul>
		<s:iterator status="st" value="numbers">
		<li class="current"><sx:a targets="houseArea" href="/HouseRent/manage!ajaxList.action?number=%{#st.index+1}"><s:property/></sx:a></li>
		</s:iterator>
		</ul>
		<span class="total"><s:property value="number"/>/<s:property value="total"/>页</span>
	</div> --%>
</div>
<div id="footer" class="wrap">
	<dl>
        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
    </dl>
</div>
</body>
</html>
