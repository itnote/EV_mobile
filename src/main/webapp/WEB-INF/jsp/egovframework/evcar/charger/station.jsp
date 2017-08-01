<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>충전기정보</title>
</head>
<body>

<h3 class="sr-only">충전기 목록입니다.</h3>
<%--
<div class="map-info">
    <h4>충전기ID <strong>453421</strong></h4>
    <ul>
        <li>충전속도 <strong class="high">고속</strong></li>
        <!-- class : high, middle, low -->
        <li>충전기상태 <strong class="stop">충전대기</strong></li>
        <!-- class : ing, stop -->
    </ul>
    <p class="time">진행시간 <strong>01:32:00</strong>
    <p class="price">요금 <strong>30,200원/kw</strong></p>
</div>
--%>

<div id="map" class="map-area" style="width: 100%;">
    <%--
    <ul class="tabs">
        <li><a href="">현재충전기</a></li>
        <li><a class="active" href="">근처충전기</a></li>
    </ul>
    --%>
    <ul class="zoom">
        <li><a href="javascript:;" onclick="map.setLevel('+')">지도확대<i class="fi icon-zoom_plus"></i></a></li>
        <li><a href="javascript:;" onclick="map.setLevel('-')">지도축소<i class="fi icon-zoom_minus"></i></a></li>
    </ul>
    <p class="location"><a href="javascript:;" onclick="setCenter()">현재위치<i class="fi icon-location"></i></a></p>
</div>

<script type="text/javascript">
var map, current, markers= [], circle;
getGeolocation(function(){
    map = new initDaumMap('map', {
        lat: config.geolocation.lat,
        lng: config.geolocation.lon,
        dragend: function(lat, lon) {
            locationMove(lat, lon)
        }
    });
    currentMarker();
    currentStationList();
});

/** 현재위치 마커생성*/
function currentMarker(){
    if(config.geolocation.use){
        current = map.addMarker({
            type: 'target',
            title: '현재위치',
            latitude: config.geolocation.lat,
            longitude: config.geolocation.lon
        });

    }

    circle = new daum.maps.Circle({
        center : new daum.maps.LatLng(config.geolocation.lat, config.geolocation.lon),  // 원의 중심좌표 입니다
        radius: 500, // 미터 단위의 원의 반지름입니다
        strokeWeight: 1, // 선의 두께입니다
        strokeColor: '#75B8FA', // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        fillColor: '#CFE7FF', // 채우기 색깔입니다
        fillOpacity: 0.2  // 채우기 불투명도 입니다
    });
    circle.setMap(map.mapContainer);

    //currentStationList();
}
/** 현재위치 충전소 조회*/
function currentStationList(){
    $.each(markers, function(e1,e2){
        e2.setMap(null);
    });
    markers = [];
    $.ajax({
        url: config.ajax+'/charger/station.mdo',
        method: 'POST',
        data: {
            lat: config.geolocation.lat,
            lon: config.geolocation.lon
        },
        success: function (data) {
            $.each(data, function(e1,e2){
                var marker = map.addMarker({
                    title: e2.snm,
                    latitude: e2.lat,
                    longitude: e2.lon
                });
                markers.push(marker);
            });
        }
    });
}
/** 중심좌표 충전소 조회*/
function locationMove() {
    var latlng = map.mapContainer.getCenter();
    $.each(markers, function(e1,e2){
        e2.setMap(null);
    });
    markers = [];
    circle.setPosition(new daum.maps.LatLng(latlng.getLat(), latlng.getLng()));
    $.ajax({
        url: config.ajax+'/charger/station.mdo',
        method: 'POST',
        data: {
            lat: latlng.getLat(),
            lon: latlng.getLng()
        },
        success: function (data) {
            $.each(data, function(e1,e2){
                //console.log(e2);
                var marker = map.addMarker({
                    title: e2.snm,
                    latitude: e2.lat,
                    longitude: e2.lon
                });
                daum.maps.event.addListener(marker, 'click', function() {
                    getStationInfoPopup({
                        sid: e2.sid
                    });
                });
                markers.push(marker);
            });
        }
    });
}
/** 현재위치 확인*/
function setCenter() {
    getGeolocation(function() {
        map.setCenterView(config.geolocation.lat, config.geolocation.lon);
        locationMove();
    });
}
</script>

</body>
</html>
