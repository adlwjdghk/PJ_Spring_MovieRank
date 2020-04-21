<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/resources/css/common.css?v=1">
<title>Insert title here</title>
<style type="text/css">
	.card_list {
		width: 1200px;
		margin: 50px auto;
		text-align: center;
		position: relative;
	}
	.card_rank {
		position: absolute;
		text-align: center;
		font-size: 1.2em;
		font-weight: bold;
		padding: 5px 0px;
		top: -32px;
		width: 220px;
		background: #02b3e4;
		color: white;
		border-radius: 10px 10px 0 0;
	}
	h2 {
		padding-left: 15px;
	}
	.card_info {
		padding: 5px 0px;
		text-align: left;
	}
	.card_info > span {
		display: block;
		font-size: 1em;
	}
	.score {
		color: crimson;
		font-weight: bold;
	}
	.booking {
		color: DodgerBlue;
		font-weight: bold;
	}
	.opendate {
		color: darkorange;
		font-weight: bold;
	}
	h1 { font-size: 2.7em;}
	.movie_sort {
		padding-top: 10px;
		padding-right: 10px;
		position: absolute;
		top: 20px;
		right: 0px;
	}
	.movie_sort > span {
		font-size: 1.1em;
		margin: 0 3px;
	}
	
	
	/* Card 디자인 */
	.container {
		width: 1200px;
		margin: 0 auto;
		position: relative;
		padding: 15px 0px;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}
	.container .card {
		position: relative;
		margin: 5px;
	}
	.container .card .face1{
		width: 220px;
		height: 200px;
		transition: 0.5s;
		position: relative;
		border-radius: 0 0 10px 10px;
	}
	.container .card .face2{
		width: 220px;
		height: 150px;
		transition: 0.5s;
		border-radius: 0 0 10px 10px;
	}
	.container .card .face.face1{
		position: relative;
		background: #282c37;
		display: flex;
		justify-content: center;
		align-items: center;
		z-index: 1;
		transform: translateY(100px);
	}
	.container .card:hover .face.face1 {
		background: #02b3e4;
		transform: translateY(0);
		border-radius: 0;
	}
	.container .card .face.face1 .content img {
		max-width: 100px;
	}
	.container .card .face.face1 .content h3 {
		margin: 10px 0 0;
		padding: 0;
		color: #fff;
		text-align: center;
		font-size: 1.1em;	
		font-weight: bold;
	}
	.container .card .face.face2{
		position: relative;
		background: #fff;
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 20px;
		box-sizing: border-box;
		box-shadow: 0 20px 50px rgba(0,0,0,0.8);
		transform: translateY(-100px);
	}	
	.container .card:hover .face.face2 {
		transform: translateY(0px);
	}
	.container .card .face.face2 .content p {
		margin: 0;
		padding: 0;
	}
	.container .card .face.face2 .content a {
		margin: 15px 0 0;
		display: inline-block;
		text-decoration: none;
		font-weight: 900;
		color: #333;
		padding: 5px;
		border: 1px solid #333;
	}
	.container .card .face.face2 .content a:hover {
		background: #333;
		color: #fff;
	}
	
</style>
</head>
<body>
	<div class="card_list">
		<h1>실시간 순위</h1>
		<div class="movie_sort">
			<span><a href="${path}/sort?sort=booking">예매순</a></span>
			<span>|</span>
			<span><a href="${path}/sort?sort=score">평점순</a></span>
			<span>|</span>
			<span><a href="${path}/sort?sort=date">개봉일순</a></span>
		</div>
		<div class="container">
			<c:forEach items="${rankList}" var="one" varStatus="status">
				<div class="card">
					<div class="face face1">
						<div class="card_rank">${status.count}위</div>
						<div class="content">
							<img src="${one.imgsrc}">
							<h3 class="card_movie">${one.movie}</h3>	
						</div>
					</div>
					<div class="face face2">
						<div class="content">
							<div class="card_info">
								<span>예매율 <span class="score">${one.bookingrate}%</span> </span>
								<span>평  &nbsp;&nbsp;&nbsp;점 <span class="booking">${one.score}점</span></span>
								<span>개봉일 <span class="opendate">${one.opendate}</span></span>
							</div>
							<a href="${path}/movie/detail?code=${one.daumcode}">Read More</a>
						</div>
					</div>	
				</div>
			</c:forEach>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			var sort = "${sort}";
			
			if(sort == "booking") {
				$('.movie_sort > span:eq(0)').css('color', '#02b3e4')
											 .css('font-weight', 'bold');
			} else if(sort == "score") {
				$('.movie_sort > span:eq(2)').css('color', '#02b3e4')
				                             .css('font-weight', 'bold');
			} else if(sort == "date") {
				$('.movie_sort > span:eq(4)').css('color', '#02b3e4')
				                             .css('font-weight', 'bold');
			}	
		});
	</script>
</body>
</html>
