/**
 * Created by dongguk on 2017-05-12.
 * 공통 다음맵 생성
 */
var initDaumMap = function(objId, option){

    this.clusterer;
    this.geocoder;
    this.leftEvent;
    this.rightEvent;
    this.dragend;
    this.dashboard = {};

    this.mapContainer = new daum.maps.Map(document.getElementById(objId),
        mapOption = {
            center: new daum.maps.LatLng( option.lat, option.lng), // 지도의 중심좌표
            level: option.level // 지도의 확대 레벨
        });

    if(option.isClusterer){
        this.clusterer = new daum.maps.MarkerClusterer({
            map: this.mapContainer, // 마커들을 클러스터로 관리하고 표시할 지도 객체
            averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
            minLevel: option.minlevel // 클러스터 할 최소 지도 레벨
        });
    }
    if(option.isGeocoder){
        this.geocoder = new daum.maps.services.Geocoder();
    }
    if(option.leftEvent){
        leftEvent.rightEvent = daum.maps.event.addListener(this.mapContainer, 'click', function(mouseEvent){
            option.leftEvent(mouseEvent);
        });
    }
    if(option.rightEvent){
        this.rightEvent = daum.maps.event.addListener(this.mapContainer, 'rightclick', function(mouseEvent){
            option.rightEvent(mouseEvent);
        });
    }
    if(option.dragend){
        this.dragend = daum.maps.event.addListener(this.mapContainer, 'dragend', function(){
            option.dragend();
        });
    }

};

initDaumMap.prototype.setLevel = function(flag) {
    var map = this.mapContainer;
    var level = map.getLevel();
    if(flag === '+'){
        map.setLevel(--level, {animate: true})
    }else {
        map.setLevel(++level, {animate: true})
    }
};
initDaumMap.prototype.setCenterView = function(lat, lon){
    var map = this.mapContainer;
    var moveLatLon = new daum.maps.LatLng(lat, lon);
    map.panTo(moveLatLon);
};

initDaumMap.prototype.displayMarker = function(markerList){
    this.clusterer.clear();
    this.clusterer.addMarkers(markerList);
};

initDaumMap.prototype.addMarker = function(obj) {
    var map = this.mapContainer;

    var markerImage = addImgMarker(obj.type);
    var marker = new daum.maps.Marker({
        map: map,
        title: obj.title,
        image: markerImage,
        position : new daum.maps.LatLng(obj.latitude, obj.longitude)
    });
    var infowindow = new daum.maps.InfoWindow({
        content: '<div style="padding:10px; white-space: nowrap;">' + obj.title + '</div>',
        removable: false
    });
    daum.maps.event.addListener(marker, 'mouseover', makeOverListener( map, marker, infowindow));
    daum.maps.event.addListener(marker, 'mouseout', function(){ infowindow.close(); });
    return marker;
};

function addImgMarker(type){
    var image = {
        imageSrc:'',
        imageSize:new daum.maps.Size(23, 32),
        imageOption:{offset: new daum.maps.Point(14, 42)}
    };
    switch (type){
        case 'on':
            image.imageSrc='/assets/images/marker/icon-on.png';
            breack;
        default:
            image.imageSrc='/assets/images/marker/icon-off.png';
            break;
    }
    var markerImage = new daum.maps.MarkerImage(image.imageSrc, image.imageSize, image.imageOption);
    return markerImage;
}

function makeOverListener(mapContainer, marker, infowindow) {
    infowindow.close();
    return function() {
        infowindow.open(mapContainer, marker);
    };
}