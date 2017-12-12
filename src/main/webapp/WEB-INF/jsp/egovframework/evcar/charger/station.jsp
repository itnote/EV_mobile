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
        <div class="filter">
            <a href="#">필터선택<i class="fi icon-filter"></i></a>
            <button>필터닫기<i class="fi icon-close"></i></button>

            <ul class="check">
                <li><input id="stat01" type="checkbox" checked><label class="stat01" for="stat01">충전중</label>
                <li><input id="stat02" type="checkbox" checked><label class="stat02" for="stat02">충전가능</label>
                <li><input id="stat03" type="checkbox"><label class="stat03" for="stat03">점검중/통신장애</label>
                <li><input id="stat04" type="checkbox"><label class="stat04" for="stat04">운영중지</label>
                <li><input id="stat05" type="checkbox"><label class="stat05" for="stat05">시범운영</label>
                <li><input id="stat06" type="checkbox"><label class="stat06" for="stat06">기타(상태미확인)</label>
            </ul>
        </div>
</div>
<script type="text/javascript">
    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new daum.maps.LatLng(33.3616666, 126.52916660000005), //지도의 중심좌표.
        level: 10 //지도의 레벨(확대, 축소 정도)
    };
    var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
    var markers = [], overrays=[], selectedMarker = null;
    var func = {
        chargerType: function(type) {
            var msg = '충전기';
            switch(type){
                case 1:
                    msg = '급속';
                    break;
                case 2:
                    msg = '완속';
                    break;
                default:
                    break;
            }
            return msg;
        },
        chargerStat: function(chargerStat) {
            var type = '4', msg = '오류';
            switch(chargerStat) {
                case '0'://알수없음
                    type = '3', msg = '알수없음';
                    break;
                case '1'://대기중
                    type = '2', msg = '대기중';
                    break;
                case '2'://충전중
                    type = '1', msg = '충전중';
                    break;
                default:
                    type = '4', msg = '오류';
                    break;
            }

            var $i = $('<i />', {class: 'stat'+type, text: msg});

            return $i;
        }
    }
    var ACTION = {
        loadMarker: function(){
            // /api/v1/stationinfo
            var result = [];
            $.ajax({
                async: false,
                type: 'GET',
                url: '/ajax/charger/station.mdo',
                data: {},
                beforeSend: function(){},
                success: function(args){
                    args.forEach(function(value, index){
                        result.push({
                            address: value.address,
                            csNm: value.csNm,
                            csId: value.csId,
                            tel: value.tel,
                            latlng: new daum.maps.LatLng(parseFloat(value.lat), parseFloat(value.lon)),
                            content: value.csId,
                            charging: value.charging
                        });
                    });
                },
                error: function(args){
                }
            });
            return result;
        },
        loadCharger: function(args, marker) {
            var param = args;
            $.ajax({
                async: false,
                type: 'GET',
                url: '/charger/'+param.csId+'/stat.mdo',
                //url: '/api/v1/charger/'+id,
                beforeSend: function(){},
                success: function(args){
                    if(args.length < 1) {
                        alert('충전기가 없습니다');
                        return;
                    }

                    var $info = $('<div />', {class: 'map-pops'}), infowindow, overlay;
                    $info.append(
                        $('<h2 />', {text: '충전소 운영 현황'}).append(
                            $('<small />', {text: param.csNm}).append(
                                $('<strong>', {class:'address', text: param.address})
                            )
                        )
                    );

                    $info.append($('<table />').append(
                        $('<thead />').append(
                            $('<tr />').append(
                                $('<th />', {text: '구분'}),
                                $('<th />', {text: '충전기 타입'}),
                                $('<th />', {text: '운전 상태'})
                            )
                        )
                        )
                    );

                    args.forEach(function(value, index){
                        $info.find('table').append(
                            $('<tr />')
                                .append($('<td style="text-align:center;"/>').text( value.chargerGrpId + ' / ' + value.chargerId ))
                                .append($('<td style="text-align:center;"/>').text(this.func.chargerType(value.ctp)))
                                .append($('<td style="text-align:center;"/>').append(
                                    this.func.chargerStat(value.sts)
                                ))
                        )
                    });
                    $info.append(
                        $('<button />', {text: 'X'}).click(function(){closeOverlay()})
                    );

                    var iwContent = $info.get(0), // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                        iwPosition = marker.getPosition(), //인포윈도우 표시 위치입니다
                        iwRemoveable = false; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

                    // 인포윈도우를 생성하고 지도에 표시합니다
                    overlay = new daum.maps.CustomOverlay({
                        map: map, // 인포윈도우가 표시될 지도
                        position : iwPosition,
                        content : iwContent,
                        xAnchor: 1,
                        yAnchor: 1.1,
                        removable : iwRemoveable
                    });
                    overrays.push(overlay);
                    function closeOverlay() {
                        overlay.setMap(null);
                        overrays.forEach(function(e1,e2){
                            e1.setMap(null);
                        });
                    }
                },
                error: function(args) {
                }
            });
        }
    };

    $(document).ready(function(){
        // marker 생성
        markers = ACTION.loadMarker();
        $.each(markers, function(e1, e2){
            //var pos = e2;

            var markerImage;


            console.log(e2);
            console.log(e1);
            if(e2.charging > 0)
                markerImage = new daum.maps.MarkerImage(
                    '/assets/images/marker_red.png',
                    new daum.maps.Size(32, 32),
                    {offset: new daum.maps.Point(16, 32)});
            else
                markerImage = new daum.maps.MarkerImage(
                    '/assets/images/marker_blue.png',
                    new daum.maps.Size(32, 32),
                    {offset: new daum.maps.Point(16, 32)});





            // 마커를 생성합니다
            var marker = new daum.maps.Marker({
                image: markerImage,
                map: map, // 마커를 표시할 지도
                position: e2.latlng, // 마커의 위치
                title: e2.content
            });

            daum.maps.event.addListener(marker, 'click', function(){
                overrays.forEach(function(e1,e2){
                    e1.setMap(null);
                });
                ACTION.loadCharger(e2, marker);
            });
        });
    });
</script>
<style type="text/css">
    div.map-pops {
        position: absolute;
        background: #fff;
        width: 380px;
        height: auto;
        border: 2px solid #303d49;
        text-align: center !important;
    }

    div.map-pops::after {
        content: "";
        display: block;
        position: absolute;
        bottom: -20px;
        left: 50%;
        margin-left: -20px;
        width: 0;
        height: 0;
        border-left: 20px solid transparent;
        border-right: 20px solid transparent;
        border-top: 20px solid #303d49;
    }

    div.map-pops h2 {
        background: #303d49;
        margin: 0;
        color: #fff;
        font-weight: normal;
        padding-top: 10px;
        font-size: 20px;
    }

    div.map-pops h2 small {
        display: block;
        position: relative;
        font-size: 26px;
        text-align: left;
        background: #456dde;
        margin-top: 10px;
        padding: 10px;
        color: #fff;
    }

    div.map-pops h2 strong {
        display: inline-block;
        font-weight: normal;
        font-size: 16px;
        position: absolute;
        right: 10px;
        bottom: 10px;

        background: #2f51b4;
        padding: 4px 10px;
        border-radius: 5px;
        color: #fff;
    }

    div.map-pops h2 strong.address {
        display: block;
        font-weight: normal;
        font-size: 12px;
        position: initial;

        background: #2f51b4;
        padding: 4px 10px;
        border-radius: 5px;
        margin-top: 5px;
        color: #fff;
    }

    div.map-pops table {
        width: 100%;
        border-spacing: 0;
    }

    div.map-pops table th,
    div.map-pops table td {
        border-right: 1px solid #999;
        border-bottom: 1px solid #999;
        padding: 10px 0;
    }

    div.map-pops table th:last-child,
    div.map-pops table td:last-child {
        border-right: none;
    }

    div.map-pops table th {
        background: #ccc;
    }

    div.map-pops table td i {
        display: inline-block;
        font-style: normal;
        background: #bbb;
        color: #fff;
        padding: 4px 0;
        width: 80px;
        border-radius: 7px;
    }

    div.map-pops table td i.stat1 {
        background: #456dde;
    }

    div.map-pops table td i.stat2 {
        background: #99cc33;
    }

    div.map-pops table td i.stat4 {
        background: #cc3333;
    }


    div.map-pops button {
        position: absolute;
        right: 10px;
        top: 10px;
        background: #000;
        border: none;
        color: #fff;
        width: 26px;
        height: 26px;
        border-radius: 50%;
    }
</style>
</body>
</html>
