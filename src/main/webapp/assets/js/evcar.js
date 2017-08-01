/**
 * Created by dongguk on 2017-06-01.
 */
// 사이트 고유정보
var config = {
    origin: location.origin,
    context: '',
    ajax: '/ajax',
    geolocation: {
        use: false,
        lat: '33.36021',
        lon: '126.7256'
    }
};

var getGeolocation = function(callback){
    // 현재위치조회
    try{
        navigator.geolocation.getCurrentPosition(
            function(position){ // Success
                config.geolocation.lat = position.coords.latitude ;
                config.geolocation.lon = position.coords.longitude;
                config.geolocation.use = true;
                if(typeof callback === 'function') {
                    callback();
                }
            },
            function(error){ // error
                config.geolocation.use = false;
                if(typeof callback === 'function') {
                    callback();
                }
            }
        );
    } catch(e) {
        config.geolocation.use = false;
        if(typeof callback === 'function') {
            callback();
        }
    }

};

/**
 * 충전기정보 팝업생성
 * @param param
 */
var getStationInfoPopup = function(param) {

    var $article = $('body article#content');

    var $popup = $('<div />', {
        class: 'popup',
        id: 'pop01',
        style: 'display: block; z-index: 1000;'
    });

    var $list = $('<ul />', {
        class: 'pop-info'
    });

    var $btn = $('<p />')
        .append(
            $('<a />', {class: 'btn sub half', text: '저장'})
        )
        .append(
            $('<a />', {class: 'btn half', text: '닫기'}).click(function(){
                $popup.remove();
            })
        )
    ;

    $.getJSON(config.ajax + '/charger/stationInfo.mdo', {searchKeyword: param.sid}, function (data) {
        //console.log(data);
        $.each(data, function(e1, e2){
            var $li = $('<li />');

            $li.append($('<h7 />', {html: '채널 ID <strong>'+ e2.cid +' 충전기</strong>'}));
            $li.append($('<i />', {html: '최종 변경시간 <strong>' + e2.updatedAt + '</strong>'}));

            if(e2.sts == '99') {
                $li.append($('<p />', {class: 'stat complete', text: '완료'}));
            }else if(e2.sts == '1'){
                $li.append($('<p />', {class: 'stat ing', text: '충전중'}));
            }else{
                $li.append($('<p />', {class: 'stat', text: '대기중'}));
            }
            $list.append($li);
        });
    });

    $popup.append($('<h5 />', {text: '충전기 정보'}));
    $popup.append(
        $('<h6 />', {
            text: '충전기ID ',
            click: function(){
                $popup.remove();
            }
        }).append($('<strong />', {text: param.sid}))
    );
    $popup.append($list);
    $popup.append($btn);

    $article.append($popup);
};


// VALIDATE SETTINGS
$.validator.setDefaults({
    onkeyup:false,
    onclick:false,
    onfocusout:false,
    showErrors:function(errorMap, errorList){
        if(this.numberOfInvalids()) { // 에러가 있을 때만..
            //var caption = $(errorList[0].element).attr('caption') || $(errorList[0].element).attr('name');
            alert(errorList[0].message);
            $(errorList[0].element).focus();
// 				alert('[' + caption + ']' + errorList[0].message);
        }
    }
});