<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 16/10/2016
  Time: 16:05
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="mapsLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">

    <style>
    #map_canvas {
        width: 100%;
        height: 100%;
    }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOVREbzXvmpDVjFX7w5GXciMCWuBbnueM&callback=initMap" type="text/javascript"
            async defer></script>
    <script type="text/javascript">
        var map;
        function initMap() {
            var mapOptions = {
                center: new google.maps.LatLng(-8.051442, -34.950867),
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
            var geocoder = new google.maps.Geocoder();
            var i = 1;
            <g:each in="${ende}" status="i" var="aa">
            var address = '${aa.address.street?.encodeAsJavaScript()} ${aa.address.city?.encodeAsJavaScript()} ${aa.address.neighborhood?.encodeAsJavaScript()}     ${aa.address.cep?.encodeAsJavaScript()}';
            var nome  = '${aa.name?.encodeAsJavaScript()}';
            // alert(address);
            geocoder.geocode({'address': address  }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var marker = new google.maps.Marker({
                        position: results[0].geometry.location,
                        map: map
                    });
                    attachSecretMessage( marker,i,'${aa.name?.encodeAsJavaScript()}');
                    i = i+1;
                }
            });
            </g:each>
        }
        google.maps.event.addDomListener(window, 'load', initMap);
        function attachSecretMessage(marker, number, texto) {
            var infowindow = new google.maps.InfoWindow(
                    { content: texto,
                        zIndex: number
                    });
            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
            });
        }
    </script>


</head>
<body>
<div id="map_canvas"></div>
</body>
</html>