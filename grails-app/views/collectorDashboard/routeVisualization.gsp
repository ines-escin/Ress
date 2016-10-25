<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 18/10/2016
  Time: 15:19
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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOVREbzXvmpDVjFX7w5GXciMCWuBbnueM&callback=initMap"
        async defer></script>
    <script type="text/javascript">
        var map;
        var directionsService;
        var directionsDisplay;
        function initMap() {
			 directionsService = new google.maps.DirectionsService;
			directionsDisplay = new google.maps.DirectionsRenderer;
            var mapOptions = {
                center: new google.maps.LatLng(-8.1062988, -34.88777852),
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
            directionsDisplay.setMap(map)
            route();
            /*var onChangeHandler = function() {
                route(directionsService, directionsDisplay);
            }*/
        }
        function route() {
            var request = {
                origin: {lat: -8.1062988, lng:-34.88777852},
                destination:    {lat: -9.66928347, lng:-35.67879414},
                /*waypoints: [
                        {
                            location:'Maragogi, Alagoas, Brazil',
                            stopover: false
                        }
                ]*/
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };
            directionsService.route(request, function(result, status) {
                if(status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(result);
                }
            });
        }
        google.maps.event.addDomListener(window, 'load', initMap());
    </script>


</head>
<body>
<div id="map_canvas"></div>
<div id="panel"></div>
</body>
</body>
</html>