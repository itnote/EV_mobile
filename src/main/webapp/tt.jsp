
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>KT IoTMakers Test</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        var g_token;
        var g_result_date = 'undefined';
        var g_deviceID = "kevs00D1488436975670";

        $(document).ready(function() {  // KT IotMakers OAuth Request
            var appId = "B2DK13KPU5GXVIaw";
            var secret = "67VANS1qHl01wsGR";

            $.ajax('https://iotmakers.olleh.com/oauth/token', {
                method: 'POST',
                xhrFields: { withCredentials: true },
                headers: { 'Authorization': 'Basic ' + btoa(appId + ':' + secret)},
                data: { grant_type: 'password',
                    username: 'kevs001',
                    password: 'koreaevs1*'
                },
                success: function(result) {
                    g_token = result.access_token;
                },
                error: function(xhr,status,error){
                    g_token = null;
                    console.log(xhr);
                }
            });
        });

        setInterval(doUserAuth, 10000); // every 10secs, check User Auth request on charger

        function doUserAuth() {     // Get User Authentication Requests from Charger
            var API_URI = '/api/v1/streams/'+g_deviceID+'/log/last';
            var FULL_URL = 'https://iotmakers.olleh.com:443' + API_URI;

            $.ajax(FULL_URL, {
                method: 'GET',
                dataType: 'json',
                headers: { 'Authorization': 'Bearer ' + g_token},
                success: function (data, status, xhr) {
                    $.each(data.data, function(index, item) {
                        if (g_result_date == 'undefined') {
                            g_result_date = item.occDt;
                        }
                        else if (data.responseCode == 'OK' && item.occDt != 'undefined' && item.occDt != g_result_date) {
                            g_result_date = item.occDt;

                            // !! User code here
                            $('.authreq').append("<br> [Data]");
                            $('.authreq').append("<br> date : "+item.occDt);
                            $('.authreq').append("<br> auth : "+item.attributes.UserauthAPIauth);
                            $('.authreq').append("<br>  sid : "+item.attributes.UserauthAPIsid);
                            $('.authreq').append("<br>  cid : "+item.attributes.UserauthAPIcid);
                            $('.authreq').append("<br>  sts : "+item.attributes.UserauthAPIsts);
                        }
                    });
                },
                error: function(xhr,status,e){
                    console.log(xhr);
                }
            });
        }

        function doOpmode() {   // Change Charger Operation mode
            var API_URI = '/api/v1.1/devices/1/sensingTags';
            var FULL_URL = 'https://iotmakers.olleh.com:443' + API_URI;
            var TagID = "ChastateAPIopmo";

            // !! User code here : need to get 'opmode' value from UI
            var opmode = $('#opvalue').val();

            $.ajax(FULL_URL, {
                method: 'PUT',
                dataType: 'json',
                contentType: "application/json",
                headers: { 'Authorization': 'Bearer ' + g_token},
                data: JSON.stringify({ sensingTags : [{code : TagID, value : opmode}]}),
                success: function (data, status, xhr) {

                    // !! User code here : remove below
                    $('.opmode').append("<br>=> Change Opmode to "+opmode);
                },
                error: function(xhr,status,e){
                    console.log(xhr);
                }
            });
        }
    </script>
</head>
<body>
<h1> KT IoTMakers Test </h1>
<!-- Change Charger Operation Mode -->
<!-- 0 : Unknown, 1 : Operation, 2: No Operation, 3: Under Construction -->
<div class="opmode">[Change Charger Op mode]
    <input id="opvalue" type="text" />
    <input id="opbutton" type="button" value="change" onclick="doOpmode();" />
</div>
<br>
<!-- User Authentication Request List -->
<div class="authreq">[List of Auth Request] </div>
</body>
</html>