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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOVREbzXvmpDVjFX7w5GXciMCWuBbnueM&libraries=places&callback=initMap"
        async defer></script>
    <script type="text/javascript">
        var map;
        var directionsService;
        var directionsDisplay;
        var waypts = [];
        /*waypts.push({
                            location:{lat: -8.05060572, lng: -34.95280063},
                            stopover: true
        });
        waypts.push( {
                            location:{lat: -8.05638996, lng: -34.95334244},
                            stopover: true
        });*/
        function initMap() {
            var ufpe = new google.maps.LatLng(-8.05233197, -34.94509196);
            //{lat: -8.05233197, lng: -34.94509196};
    		directionsService = new google.maps.DirectionsService;
			directionsDisplay = new google.maps.DirectionsRenderer;
            var mapOptions = {
                center: ufpe,
                zoom: 16,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
            directionsDisplay.setMap(map);
            <g:each in="${enderecos}" status="i" var="endereco">
                var address = '${endereco.street?.encodeAsJavaScript()} ${endereco.city?.encodeAsJavaScript()} ${endereco.cep?.encodeAsJavaScript()}';
                //alert(address);
                var nome  = '${endereco.user.name?.encodeAsJavaScript()}';
                //alert(nome);
                var service = new google.maps.places.PlacesService(map);
                //alert("depois do service");
                var request = {
                    location: ufpe,
                    radius: 5000,
                    query:  nome + " " + address
                };

                        waypts.push({
                            location: nome + " " + address,
                            stopover: true
                        });

            </g:each>
            //alert("saiud o each");
            route();
        }

        function callback(results, status) {

            if (status == google.maps.places.PlacesServiceStatus.OK) {
                //alert("oi do if");
                //createMarker(results[0]);
                //var lugar = new google.maps.LatLng(results[0].latitude, results[0].longitude);
                waypts.push({
                    location: {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()},

                    stopover: true
                });

                //alert("oi do for");
                //alert("depois do push");
            }
        }


        function route() {
            var collectorAddress = '${enderecoColetor.street?.encodeAsJavaScript()} ${enderecoColetor.city?.encodeAsJavaScript()} ${enderecoColetor.cep?.encodeAsJavaScript()}';
            var request = {
                origin: collectorAddress,
                destination: collectorAddress,
                waypoints: waypts,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };
            directionsService.route(request, function(result, status) {
                if(status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(result);
                }
            });
        }

        function createMarker(place) {
            var placeLoc = place.geometry.location;
            var marker = new google.maps.Marker({
                map: map,
                position: place.geometry.location
            });
            google.maps.event.addListener(marker, 'click', function() {
                infowindow.setContent(place.name);
                infowindow.open(map, this);
            });
            bounds.extend(marker.getPosition());
            map.fitBounds(bounds);
        }

        google.maps.event.addDomListener(window, 'load', initMap());
    </script>
</head>
<body>
<div id="map_canvas"></div>
</body>
</html>