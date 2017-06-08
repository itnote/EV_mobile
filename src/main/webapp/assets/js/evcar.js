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
        lat: '33.50033',
        lon: '126.5297'
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