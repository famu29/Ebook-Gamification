<%@page contentType="text/html" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--
    Document Title
    =============================================
    -->
<title>Smart E-textbook application</title>
<!--
    Favicons
    =============================================
    -->
<link rel="apple-touch-icon" sizes="57x57"
	href="css/assets/images/favicons/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="css/assets/images/favicons/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="css/assets/images/favicons/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="css/assets/images/favicons/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="css/assets/images/favicons/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="css/assets/images/favicons/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="css/assets/images/favicons/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="css/assets/images/favicons/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="css/assets/images/favicons/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="css/assets/images/favicons/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="css/assets/images/favicons/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="css/assets/images/favicons/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="css/assets/images/favicons/favicon-16x16.png">
<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="css/assets/images/favicons/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<!--
    Stylesheets
    =============================================

    -->
<!-- Default stylesheets-->
<link href="css/assets/lib/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Template specific stylesheets-->
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Volkhov:400i"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800"
	rel="stylesheet">
<link href="css/assets/lib/animate.css/animate.css" rel="stylesheet">
<link
	href="css/assets/lib/components-font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/assets/lib/et-line-font/et-line-font.css"
	rel="stylesheet">
<link href="css/assets/lib/flexslider/flexslider.css" rel="stylesheet">
<link
	href="css/assets/lib/owl.carousel/dist/assets/owl.carousel.min.css"
	rel="stylesheet">
<link
	href="css/assets/lib/owl.carousel/dist/assets/owl.theme.default.min.css"
	rel="stylesheet">
<link href="css/assets/lib/magnific-popup/dist/magnific-popup.css"
	rel="stylesheet">
<link href="css/assets/lib/simple-text-rotator/simpletextrotator.css"
	rel="stylesheet">
<!-- Main stylesheet and color file-->
<link href="css/assets/css/style.css" rel="stylesheet">
<link id="color-scheme" href="css/assets/css/colors/default.css"
	rel="stylesheet">
</head>
<body data-spy="scroll" data-target=".onpage-navigation"
	data-offset="60">
	<main>
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>

	<c:import url="include/seaheader2.jsp">
	</c:import>
	<div class="main showcase-page">

		<section class="module-medium" id="demos">
			<div class="container">
				<div class="row multi-columns-row">
					<!--  <div class="col-md-4 col-sm-6 col-xs-12">
						<a class="content-box" href="<c:url value = "/profile/user"/>">
							<div class="content-box-image">
								<img src="css/assets/images/screenshots/userimg.jpg"
									alt="ログインユーザー">
							</div>
							<h3 class="content-box-title font-serif">ユーザー情報</h3>
						</a>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<a class="content-box" href="<c:url value = "/profile/sea"/>">
							<div class="content-box-image">
								<img src="css/assets/images/screenshots/seaimg.jpg" alt="掲示板">
							</div>
							<h3 class="content-box-title font-serif">掲示板</h3>
						</a>
					</div>-->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<a class="content-box" href="<c:url value = "/ebook/show"/>">
							<div class="content-box-image">
								<img src="css/assets/images/digitaltextbook.jpg"
									alt="グループリスト">
							</div>
							<h3 class="content-box-title font-serif">教科書</h3>
						</a>
					</div>
					<!-- kns ここから追加-->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<a class="content-box" href="<c:url value = "/gamification"/>">
							<div class="content-box-image">
								<img src="css/assets/images/ranking.png"
									alt="グループリスト">
							</div>
							<h3 class="content-box-title font-serif">ゲーミフィケーション</h3>
						</a>
					</div>
					<!--kns　ここまで-->
				</div>
			</div>
		</section>
		<c:import url="include/seabottom.jsp">
		</c:import>
	</div>
	<div class="scroll-up">
		<a href="#totop"><i class="fa fa-angle-double-up"></i></a>
	</div>
	</main>
	<!--
    JavaScripts
    =============================================
    -->
	<script src="css/assets/lib/jquery/dist/jquery.js"></script>
	<script src="css/assets/lib/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="css/assets/lib/wow/dist/wow.js"></script>
	<script
		src="css/assets/lib/jquery.mb.ytplayer/dist/jquery.mb.YTPlayer.js"></script>
	<script src="css/assets/lib/isotope/dist/isotope.pkgd.js"></script>
	<script src="css/assets/lib/imagesloaded/imagesloaded.pkgd.js"></script>
	<script src="css/assets/lib/flexslider/jquery.flexslider.js"></script>
	<script src="css/assets/lib/owl.carousel/dist/owl.carousel.min.js"></script>
	<script src="css/assets/lib/smoothscroll.js"></script>
	<script
		src="css/assets/lib/magnific-popup/dist/jquery.magnific-popup.js"></script>
	<script
		src="css/assets/lib/simple-text-rotator/jquery.simple-text-rotator.min.js"></script>
	<script src="css/assets/js/plugins.js"></script>
	<script src="css/assets/js/main.js"></script>
</body>

</html>

