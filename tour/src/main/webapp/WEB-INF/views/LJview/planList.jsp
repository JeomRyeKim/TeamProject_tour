<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../bootStrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>플래너 목록</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/plannerStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plannerJs.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	/*  유효한 계정인지 검증  */
	$(document).ready(function(){
	            if(${m_active_kind != 1}){
	                alert("탈퇴 또는 비활성화된 계정입니다ㅠㅠ 유효한 계정만 마이플래너를 사용하실수 있습니다");
	                location.href = "/"
	            } 
	});
</script>
<script>
        $(document).ready(function () {
            $("#btn").click(function () {
                $("#popup").fadeIn();
            });

            $("#popdown").click(function () {
                $("#popup").fadeOut();
            });

        });

        /* 플래너 삭제시 존재여부 검증, 있으면 plannerDelete로 plannerNo와 session id 전달실행 */
        function deletePlanner(plannerNo){
            var result = confirm("플래너를 삭제하시겠습니까?");
            if(result == false){
                return false;
            }else if(result == true){
                window.location.href='${pageContext.request.contextPath}/plannerDelete?plannerNo='+plannerNo +'&id=${sessionScope.m_id}';
            }
        }
        
        
        /* 왼쪽 메뉴 스크롤 따라 움직이기 */
        $(document).ready(function(){
            var currentPosition = parseInt($(".planL-navbox").css("top"));
            $(window).scroll(function() {
                var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
                $(".planL-navbox").stop().animate({"top":position+currentPosition+"px"},700);
            });
        });
        
    </script>
   
</head>
<body>
<!--  header   -->
<header class="header" style="position: fixed; width: 100%; z-index: 10;">
	<%@include file="../header1.jsp" %>
</header>
<!-- // header  -->

<!-- 마이페이지 container -->
<div class="planL-container">

    <!-- 마이페이지 navbar -->
    <div class="planL-navbox">
        <ul class="planL-navbox__ul--blue">
            <li class="planL-navbox__li--big"> PLanner </li>
            <li class="planL-navbox__li--small"><a href="${pageContext.request.contextPath}/myInfo?id=${sessionScope.m_id}" class="planL-navbox__a--blue"><img class="planL-navbox__img--small" src="image/myPlanner/person.png">&nbsp;  나의 정보</a></li>
            <li class="planL-navbox__li--small"><a href="${pageContext.request.contextPath}/planL?id=${sessionScope.m_id}" class="planL-navbox__a--blue"><img class="planL-navbox__img--small" src="image/myPlanner/planner.png">&nbsp;&nbsp;내 플래너</a></li>
        </ul>
    </div>
    <!-- // 마이페이지 네비바-->

    <!-- 플래너 목록 container -->
    <div class="planL-planbox">

        <!-- 플래너 목록 제목 -->
        <div class="planL-titlebox">
            <span class="planL-titlebox--span__big">My Planner</span>
            <button class="planL-titlebox--button__blue" id="btn">플래너 작성</button>
        </div>
        <!-- // 플래너 목록 제목-->
        
        <!-- 플래너 목록 시작 -->
        <div class="planL-listbox">

            <!-- varitems로 변경 후 데이터 가져와서 뿌릴 예정, 플래너 각 고유 id 필요하기에 플래너 번호 필수 ! -->
            <c:forEach items="${planners}" var="planner" varStatus="status">
                <div class="planL-detailbox">

                    <div class="planL-detailmap" >
                        <div class="planL-map" id="map${status.count}" onclick="location.href='${pageContext.request.contextPath}/planD?plannerNo=${planner.plannerNo}'" ></div> <!-- 플래너 상세 페이지로 이동하는 경로 -->
                    </div>

					<!--  지도 API  -->
                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e47a393473d9bf60bc248b17b6d8a8aa"></script>
                    <script>
                        /* 지도 생성 */
                        var container = document.getElementById('map${status.count}');

                        var options = {                     //용산공원쪽 서울 중심 기본 지도중심 좌표
                            center: new kakao.maps.LatLng(37.5216866, 126.9710424), 
                            level: 9
                        };

                        var map = new kakao.maps.Map(container, options);

                        /* 폴리라인 생성 */
                        var polyline = new kakao.maps.Polyline({
                            map: map,
                            path: [],
                            strokeWeight: 3,
                            strokeColor: '#ff0066',
                            strokeOpacity: 1,
                            strokeStyle: 'solid'
                        });

                        /*for문 생성 */
                        <c:forEach var="plan" items="${plans}" varStatus="plan_status">
                        	<c:if test="${planner.plannerNo eq plan.plannerNo}">  
                                /* 지도에 마커 생성 */
                                /* 마커 */
                                var markerPosition  = new kakao.maps.LatLng(${plan.y}, ${plan.x});
                                var marker = new kakao.maps.Marker({
                                    position: markerPosition
                                });
                                marker.setMap(map);

                                /* 경로 추가  */
                                var point =  new kakao.maps.LatLng(${plan.y}, ${plan.x});
                                var path = polyline.getPath();
                                path.push(point);
                                polyline.setPath(path);
                            </c:if>
                        </c:forEach>
                    </script>
                    
                    <div class="planL-detailinfo">
                        <span class="planL-detailinfo__span--date">
                                <fmt:formatDate value="${planner.getFDate()}" pattern="yyyy-MM-dd" />
                                </span>
                        <span class="planL-detailinfo__span--dday" title="${planner.title}">${planner.title}</span>
                        <fmt:parseNumber value="${planner.getFDate().getTime() / (1000*60*60*24)}" integerOnly="true" var="first"/>
                        <fmt:parseNumber value="${planner.getLDate().getTime() / (1000*60*60*24)}" integerOnly="true" var="last"/>
                        <span class="planL-detailinfo__span--days">${last - first + 1}DAYS</span>
                        <button onclick="deletePlanner(${planner.plannerNo})" class="planL-detailinfo__button--blue">삭제</button>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- // 플래너 목록 시작-->
    </div>
    <!-- // 플래너 목록 container-->
</div>
<!-- // 마이페이지 container-->




<!-- 플래너 작성 버튼 클릭시 팝업창 -->
<div class="planL-popupcontainer" id="popup">

    <div class="planL-popbox">

        <!-- 플래너 팝업창 header 부분 -->
        <div class="planL-popheadbox">
            <span class="planL-popheadbox__span--big">플래너 만들기</span>
            <button class="planL-popheadbox__button--big" id="popdown">닫기</button>
        </div>
        <!-- // 플래너 팝업창 header 부분 -->

        <!-- 플래너 팝업창 입력 부분 -->
        <div class="planL-popcontentbox">
            <form action="${pageContext.request.contextPath}/goPlanI?id=${sessionScope.m_id}" method="post" name="popupFrm" onsubmit="return popupCheck()">

                <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">여행제목</span>
                    <input type="text" name="title" placeholder="20자 내로 입력해주세요" maxlength="20" class="planL-popdetailbox__input--gray" required>
                </div>

                <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">여행기간</span>
                    <input type="date" name="fDate" class="planL-popdetailbox__input--date" required>
                    <span class="planL-popdetailbox__span--small">~</span>
                    <input type="date" name="lDate" class="planL-popdetailbox__input--date" required>
                </div>

                <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">설명</span>
                    <input type="text" name="intro" placeholder="30자 내로 입력해주세요" maxlength="30" class="planL-popdetailbox__input--gray" value="">
                </div>

                <div class="planL-popbtnbox">
                    <input type="submit" class="planL-popbtnbox__input--blue" value="플래너 만들기">
                </div>

            </form>
        </div>
        <!-- // 플래너 팝업창 입력 부분 -->

    </div>
</div>
<!-- // 플래너 작성 버튼 클릭시 팝업창 -->
	<%@include file="../footer.jsp" %>
</body>
</html>