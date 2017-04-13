<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
$(function(){
	Highcharts.chart('container', {
	    chart: {
	        type: 'pie',
	        options3d: {
	            enabled: true,
	            alpha: 45,
	            beta: 0
	        }
	    },
	    title: {
	        text: '감성분석 현황'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            depth: 35,
	            dataLabels: {
	                enabled: true,
	                format: '{point.name}'
	            }
	        }
	    },
	    series: [{
	        type: 'pie',
	        name: '감성',
	        data: <%=request.getAttribute("json")%>
	    }]
	});
});
</script>
</head>
<body>
   <div id="content_bg">
					<div id="content">
						<div class="main_box">
							<h2>${vo.title }</h2>
							
						</div>
						<div id="column_box">
							 <div class="column1_content">
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
							 <div class="column4_content">
								
								  <div class="img_bg1">
									<div id="container" style="width:450px;height: 280px"></div>
								  </div>
								
								<!-- <div class="column_content_bot"></div> -->
							 </div>
							 
							<div class="clear"></div>
							
						</div>
					
					</div>
				</div>
				
</body>
</html>