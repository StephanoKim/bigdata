<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div id="content_bg">
					<div id="content">
						<div class="main_box">
							<!-- <h2>Welcome to Our Website!</h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed enim ante, faucibus in pharetra et, condimentum non lorem. Vestibulum tempor ligula est. Nulla tellus mi, sodales tincidunt ultrices vehicula, mattis vel justo. Sed in nisl leo, sit amet ornare enim. Cum sociis natoque penatibus et magnis dis parturient montes.</p> -->
						</div>
						<div id="column_box">
						   <c:forEach var="vo" items="${list }" varStatus="s">
							<c:if test="${s.index<3 }">
							 <div class="column${s.index+1 }_content">
								<h3>${vo.title }</h3>
								<div class="column_text">
									<div class="img_bg">
										<img src="${vo.poster }" alt="" title="" width=230 height=110/>
									</div>
									<p><a href="detail.do?mno=${vo.mno }">${vo.story }</a></p>
								</div>
								<div class="column_content_bot"></div>
							 </div>
							</c:if>
						   </c:forEach>	
							<div class="clear"></div>
							<c:set var="i" value="1"/>
							<c:forEach var="vo" items="${list }" varStatus="s">
							<c:if test="${s.index>2 && s.index<6 }">
							 <div class="column${i }_content">
								<h3>${vo.title }</h3>
								<div class="column_text">
									<div class="img_bg">
										<img src="${vo.poster }" alt="" title="" width=230 height=110/>
									</div>
									<p><a href="detail.do?mno=${vo.mno }">${vo.story }</a></p>
								</div>
								<div class="column_content_bot"></div>
							 </div>
							 <c:set var="i" value="${i+1 }"/>
							</c:if>
						   </c:forEach>	
							<div class="clear"></div>
						</div>
					
					</div>
				</div>
				
</body>
</html>