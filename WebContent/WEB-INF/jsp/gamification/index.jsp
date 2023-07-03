<%@page contentType="text/html" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="ja">
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="css/gamification.css" rel="stylesheet" type="text/css">

 <title>ゲーミフィケーション画面</title>
</head>
<body>
  <c:choose>
  <c:when test="${MyGamificationInfo.getLevel()==1}">
    <img src="css/assets/images/icon1.png" align="left" alt="ユーザアイコン" width="130" height="130" border="5">
  </c:when>
  <c:when test="${MyGamificationInfo.getLevel()==2}">
    <img src="css/assets/images/icon2.png" align="left" alt="ユーザアイコン" width="130" height="130" border="5">
  </c:when>
  <c:when test="${MyGamificationInfo.getLevel()==3}">
    <img src="css/assets/images/icon3.png" align="left" alt="ユーザアイコン" width="130" height="130" border="5">
  </c:when>
  <c:when test="${MyGamificationInfo.getLevel()==4}">
    <img src="css/assets/images/icon4.png" align="left" alt="ユーザアイコン" width="130" height="130" border="5">
  </c:when>
  <c:when test="${MyGamificationInfo.getLevel()==5}">
    <img src="css/assets/images/icon5.png" align="left" alt="ユーザアイコン" width="130" height="130" border="5">
  </c:when>
  </c:choose>

	 <p class="class1">　ID:${user.getId()}</p>
	 <p class="class2">　${user.getNickname()} さん</p>
	 <p class="class2">　Lv. ${MyGamificationInfo.getLevel()}
	 	<c:if test="${MyGamificationInfo.getLevel()!=5}">
	 		(次のレベルまで残り:${ToNextLevel} Point)
	 	</c:if></p>
<p class="class2">　${MyGamificationInfo.getTotalAllPoint()} Point</p>

    <table class="MyPoint">
    <tr>
    	<th class="tablehead">教科書ポイント</th>
    	<th class="tablehead">ハイライトポイント</th>
    	<th class="tablehead">メモポイント</th>
    	<th class="tablehead">赤シートポイント</th></tr>
    <tr>
    	<td class="Point">${MyGamificationInfo.getTotalEbookPoint()}P (+${MyGamificationInfo.getTodayEbookPoint()}P)</td>
    	<td class ="Point">${MyGamificationInfo.getTotalHighLightPoint()}P (+${MyGamificationInfo.getTodayHighLightPoint()}P)</td>
    	<td class ="Point">${MyGamificationInfo.getTotalMemoPoint()}P (+${MyGamificationInfo.getTodayMemoPoint()}P)</td>
    	<td class="Point">${MyGamificationInfo.getTotalRedSheetPoint()}P (+${MyGamificationInfo.getTodayRedSheetPoint()}P)</td>
    </table>
	<span>※括弧()内は今日加算されたポイント</span>
    <p>　</p>
	<table class="badge1" border="1">
		<tr>
			<th class="badgehead">スタートダッシュ</th>
			<th class="badgehead">まずは一周</th>
			<th class="badgehead">勤勉</th>
			<th class="badgehead">追い上げ</th>
		</tr>
		<tr>
			<td class="setumei">※最も早く学習を始める</td>
			<td class="setumei">※教科書をすべて閲覧する</td>
			<td class="setumei">※３日以上連続でポイントを獲得する</td>
			<td class="setumei">※１日で50P以上獲得する</td>
		</tr>
		<tr>
			<td class="check"><c:if test="${MyGamificationInfo.getStartDash()=='true'}">
								<img alt="スタートダッシュバッジ" src="css/assets/images/StartDash.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getStartDash()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getIssyuu()=='true'}">
								<img alt="まずは一周バッジ" src="css/assets/images/Issyuu.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getIssyuu()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getKinben()=='true'}">
								<img alt="勤勉バッジ" src="css/assets/images/Kinnbenn.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getKinben()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getOiage()=='true'}">
								<img alt="追い上げバッジ" src="css/assets/images/Oiage.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getOiage()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
		</tr>
	</table>
	<p></p>
	<table class="badge2" border="1">
		<tr>
			<th class="badgehead">教科書マスター</th>
			<th class="badgehead">ハイライトマスター</th>
			<th class="badgehead">メモマスター</th>
			<th class="badgehead">赤シートマスター</th>
		</tr>
		<tr>
			<td class="setumei">※累計教科書ポイント1位</td>
			<td class="setumei">※累計ハイライトポイント1位</td>
			<td class="setumei">※累計メモポイント1位</td>
			<td class="setumei">※累計赤シートポイント1位</td>
		</tr>
		<tr>
			<td class="check"><c:if test="${MyGamificationInfo.getEbookMaster()=='true'}">
								<img alt="教科書マスターバッジ" src="css/assets/images/EbookMaster.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getEbookMaster()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getHighLightMaster()=='true'}">
								<img alt="ハイライトマスターバッジ" src="css/assets/images/HighLightMaster.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getHighLightMaster()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getMemoMaster()=='true'}">
								<img alt="メモマスターバッジ" src="css/assets/images/MemoMaster.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getMemoMaster()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
			<td class="check"><c:if test="${MyGamificationInfo.getRedSheetMaster()=='true'}">
								<img alt="赤シートマスターバッジ" src="css/assets/images/RedSheetMaster.png" width="130" height="130"></c:if>
								<c:if test="${MyGamificationInfo.getRedSheetMaster()=='false'}">
								<img alt="未獲得" src="css/assets/images/Nothing.png" width="130" height="130"></c:if></td>
		</tr>
	</table>
	<p>　</p>

	<h1>|リーダーボード</h1>
	<div class="flex">
    <table class="today-ranking" border="1" >
        <caption>今日のランキング</caption>
        <tr>
            <th class="today-ranking">順位</th>
            <th class="today-ranking">ユーザ名</th>
            <th class="today-ranking">ポイント数</th>
        </tr>
		<tr>
			<td>1位</td>
			<td>${TodayRanking[0].getNickName()}さん</td>
			<td>${TodayRanking[0].getTodayAllPoint()}P</td>
		</tr>
		<tr>
			<td>2位</td>
			<td>${TodayRanking[1].getNickName()}さん</td>
			<td>${TodayRanking[1].getTodayAllPoint()}P</td>
		</tr>
		<tr>
			<td>3位</td>
			<td>${TodayRanking[2].getNickName()}さん</td>
			<td>${TodayRanking[2].getTodayAllPoint()}P</td>
		</tr>
		<tr>
			<td>4位</td>
			<td>${TodayRanking[3].getNickName()}さん</td>
			<td>${TodayRanking[3].getTodayAllPoint()}P</td>
		</tr>
		<tr>
			<td>5位</td>
			<td>${TodayRanking[4].getNickName()}さん</td>
			<td>${TodayRanking[4].getTodayAllPoint()}P</td>
		</tr>
      </table>

      <table class="total-ranking" border="1">
        <caption>累計ランキング</caption>
        <!--表の一番上の見出し作成-->
        <tr>
            <th class="total-ranking">順位</th>
            <th class="total-ranking">ユーザ名</th>
            <th class="total-ranking">ポイント数</th>
		</tr>
		<tr>
			<td>1位</td>
			<td>${TotalRanking[0].getNickName()}さん</td>
			<td>${TotalRanking[0].getTotalAllPoint()}P</td>
		</tr>
		<tr>
			<td>2位</td>
			<td>${TotalRanking[1].getNickName()}さん</td>
			<td>${TotalRanking[1].getTotalAllPoint()}P</td>
		</tr>
		<tr>
			<td>3位</td>
			<td>${TotalRanking[2].getNickName()}さん</td>
			<td>${TotalRanking[2].getTotalAllPoint()}P</td>
		</tr>
		<tr>
			<td>4位</td>
			<td>${TotalRanking[3].getNickName()}さん</td>
			<td>${TotalRanking[3].getTotalAllPoint()}P</td>
		</tr>
		<tr>
			<td>5位</td>
			<td>${TotalRanking[4].getNickName()}さん</td>
			<td>${TotalRanking[4].getTotalAllPoint()}P</td>
		</tr>
      </table>
    </div>
      <h1>|バッジ獲得者一覧</h1>
      <p><img alt="スタートダッシュバッジ" src="css/assets/images/StartDash.png" width="30" height="30">
      「スタートダッシュバッジ」獲得者：${StartDashList.get(0)}さん</p>

      <p><img alt="まずは一周バッジ" src="css/assets/images/Issyuu.png" width="30" height="30">
      「まずは一周バッジ」獲得者：
      	<c:forEach items="${IssyuuList}" var="IssyuuUser">
      		<c:out value="${IssyuuUser}"/>さん,
      	</c:forEach></p>

      <p><img alt="勤勉バッジ" src="css/assets/images/Kinnbenn.png" width="30" height="30">
      「勤勉バッジ」獲得者：
        <c:forEach items="${KinbenList}" var="KinbenUser">
      		<c:out value="${KinbenUser}"/>さん,
      	</c:forEach></p>

      <p><img alt="追い上げバッジ" src="css/assets/images/Oiage.png" width="30" height="30">
      「追い上げバッジ」獲得者：
      	<c:forEach items="${OiageList}" var="OiageUser">
      		<c:out value="${OiageUser}"/>さん,
      	</c:forEach></p>

      <p><img alt="教科書マスターバッジ" src="css/assets/images/EbookMaster.png" width="30" height="30">
      「教科書マスターバッジ」獲得者：
        <c:forEach items="${EbookMasterList}" var="EbookMasterUser">
      		<c:out value="${EbookMasterUser}"/>さん,
      	</c:forEach>

      <p><img alt="ハイライトマスターバッジ" src="css/assets/images/HighLightMaster.png" width="30" height="30">
      「ハイライトマスターバッジ」獲得者：
        <c:forEach items="${HighLightMasterList}" var="HighLightMasterUser">
      		<c:out value="${HighLightMasterUser}"/>さん,
      	</c:forEach>

      <p><img alt="メモマスターバッジ" src="css/assets/images/MemoMaster.png" width="30" height="30">
      「メモマスターバッジ」獲得者：
        <c:forEach items="${MemoMasterList}" var="MemoMasterUser">
      		<c:out value="${MemoMasterUser}"/>さん,
      	</c:forEach>

      <p><img alt="赤シートマスターバッジ" src="css/assets/images/RedSheetMaster.png" width="30" height="30">
      「赤シートマスターバッジ」獲得者：
         <c:forEach items="${RedSheetMasterList}" var="RedSheetMasterUser">
      		<c:out value="${RedSheetMasterUser}"/>さん,
      		</c:forEach></p>
      <p>　</p>
      <h2>|ヘルプ</h2>
      <p>・各種ポイントについて</p>
      <p>　・教科書ポイント：閲覧したスライド数×１P</p>
      <p>　・ハイライトポイント：ハイライト数×１P</p>
      <p>　・メモポイント：メモ数×１P</p>
      <p>　・赤シートポイント：赤シート機能使用回数×１P</p>
      <p>※明らかに学習していないと判断される場合には加算されません.</p>
      <p>・レベルについて</p>
      <p>　・50Pごとに１レベル上がります。（最大Lv.５）</p>
      <p>　・レベルが上がるとアイコンが進化します。</p>
    </body>
</html>

