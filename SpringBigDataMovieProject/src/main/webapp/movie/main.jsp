<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>영화 빅데이터 추천사이트</title>
<link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="lib/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="lib/jquery.tools.js"></script>
<script src="http://s.codepen.io/assets/libs/modernizr.js"
	type="text/javascript"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<style>
/* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
.bargraph {
	list-style: none;
	width: 300px;
	position: relative;
}

.bargraph li {
	position: relative;
	height: 15px;
	margin-bottom: 6px;
	transition: width 2s;
	-webkit-transition: width 2s;
}

.bargraph li span {
	position: absolute;
	right: -40px;
	line-height: 15px;
}

.bargraph .top {
	background: #7ecd27;
	width: 12%;
}

.bargraph .midtop {
	background: #7ecd27;
	width: 35%;
}

.bargraph .neutral {
	width: 40%;
	background: #64cdf4;
}

.bargraph .midbottom {
	width: 35%;
	background: #edb2d6;
}

.bargraph .bottom {
	width: 22%;
	background: #edb2d6;
}
</style>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>
<body>
	<div id="bg_left">
		<div id="bg_right">
			<div class="pad_top"></div>
			<div id="menu_bg">
				<div id="menu_grad">
					<div id="menu">
						<div id="logo">
							<h1>
								<a href="#">SIST Movie Center</a>
							</h1>
							<a href="#"><small>빅데이터를 이용한 영화추천</small></a>
						</div>
						<ul>
							<li><a href="main.do" class="active">Home</a></li>
							<li><a href="total.do">전체</a></li>
							<li><a href="#">상영영화</a></li>
							<li><a href="#">상영예정</a></li>
							<li><a href="#">영화추천</a></li>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			<div id="prew_box">
				<div id="prew_bg">

					<div class="scrollable">
						<div class="items">
							<c:forEach var="vo" items="${mList }" varStatus="s">
								<c:if test="${s.index<5 }">
									<div class="item">
										<div class="header${s.index+1 }">

											<img src="${vo.poster }" alt="" title="${vo.title }" />

										</div>

									</div>
									<!-- item -->
								</c:if>
							</c:forEach>
						</div>
						<!-- items -->
					</div>
					<!-- scrollable -->
					<div style="height: 10px"></div>
					<div class="navi"></div>
					<!-- create automatically the point dor the navigation depending on the numbers of items -->

					<div style="clear: both"></div>

				</div>
			</div>
			<div id="prew_but_bg"></div>
			<jsp:include page="${main_jsp }"></jsp:include>
			<div id="footer_border_top"></div>
			<div id="footer">
				<div id="footer_top">
					<div class="foot_col1">
						<h5>Movie Ranking</h5>
						<ol class="ls" style="color: #fff; list-style-type: decimal;">
							<c:forEach var="vo" items="${rList }">
								<li>${vo.title }(<img alt="" src="${vo.image }">&nbsp;${vo.increment })
								</li>
							</c:forEach>
						</ol>
					</div>
					<div class="foot_col1">
						<h5>Reserve Ranking</h5>
						<ol class="ls" style="color: #fff; list-style-type: decimal;">
							<c:forEach var="vo" items="${reList }">
								<li>${vo.title }(${vo.score })</li>
							</c:forEach>
						</ol>
					</div>
					<div class="foot_col3">
						<div id="calendar">
							<h5>Reserve%</h5>
							<table summary="Calendar">
								<tr>
									<td>
										<ul class="bargraph">
											<c:forEach var="vo" items="${reList }" varStatus="s">
											<%-- <c:if test="${s.index<5 }"> --%>
											<li style="width: ${vo.score};background-color:#7ecd27;"><span>${vo.score }</span></li>
											<%-- </c:if> --%>
											</c:forEach>
										</ul>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="foot_col4">
						<h5>Today's Weather</h5>
						<div>
							<select name="loc">
								<option>서울</option>
								<option>경기</option>
								<option>부산</option>
								<option>강원</option>
								<option>인천</option>
							</select>
						</div>
						<div class="link2">
							날씨:
						</div>
						<div class="link3">
							강수량:
						</div>
						<div class="link4">
							습도:
						</div>
					</div>
					<div style="clear: both"></div>
				</div>
				<div id="footer_menu">
					<ul>
						<li><a href="main.do" class="active">Home</a></li>
						<li><a href="#">전체</a></li>
						<li><a href="#">상영영화</a></li>
						<li><a href="#">상영예정</a></li>
						<li><a href="#">영화추천</a></li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
			<div id="footer_bot">
				<p>
					<a href="http://localhost:8080/bigdata/movie/main.do">SIST
						Movie Center</a>
				</p>
				<p>
					<a href="#">강북쌍용교육센터</a> | <a href="http://cafe.naver.com/moyaid">서울시
						마포구 백범로18(노고산동) 미화빌딩 2층 A강의장</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>