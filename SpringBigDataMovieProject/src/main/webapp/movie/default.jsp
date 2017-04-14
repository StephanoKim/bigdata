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
						<c:forEach var="vo" items="${mList }" varStatus="s">
						<c:if test="${s.index<3 }">
							<div class="column${s.index+1 }_content">
								<h3>영화순위:${vo.mno}</h3>
								<div class="column_text">
									<div class="img_bg">
										<img src="${vo.poster }" alt="" title="" width=230 height=110/>
									</div>
									<p>감독:${vo.director }</p>
									<p>출연:${vo.actor }</p>
									<p>장르:${vo.genre }</p>
									<p>개봉일:${vo.regdate }</p>
									<p>예매율:${vo.reserve }</p>
								</div>
								<div class="column_content_bot"></div>
							</div>
						</c:if>
						</c:forEach>
							<div class="clear"></div>
						</div>
					
					</div>
				</div>
				<div id="content_bottom_bg">
					<div id="content_bottom">
						<div class="con_bot_left">
							<h4>오늘의 뉴스</h4>
							<p>
							<form method=post action="main.do">
							 <input type=text name=data size=10>
							 <input type=submit value="검색">
							</form>
						</div>
						<div class="con_bot_right">
						<c:forEach var="vo" items="${nList }">
						<p><a href="${vo.link }">${vo.title }</a></p>
						</c:forEach>
						</div>
						<div class="clear"></div>
					</div>
				</div>
</body>
</html>