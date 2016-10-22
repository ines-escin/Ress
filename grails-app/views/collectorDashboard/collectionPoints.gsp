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
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    #map {
        height: 100%;
    }
    </style>
    <script>
        var map;
        var infowindow;
        var bounds;

        function initMap() {
            var pyrmont = {lat: -8.051442, lng: -34.950867};

            map = new google.maps.Map(document.getElementById('map'), {
                center: pyrmont,
                zoom: 15
            });

            infowindow = new google.maps.InfoWindow();
            bounds = new google.maps.LatLngBounds();

            <g:each in="${enderecos}" status="i" var="endereco">

                alert("oi")
                var address = '${endereco.street?.encodeAsJavaScript()} ${endereco.city?.encodeAsJavaScript()}     ${endereco.cep?.encodeAsJavaScript()}';
                var nome  = '${endereco.user.name?.encodeAsJavaScript()}';

                var service = new google.maps.places.PlacesService(map);
                service.textSearch({
                    location: pyrmont,
                    radius: 1000,
                    query:  nome + " " + address
                }, function (results, status) {
                    if (status === google.maps.places.PlacesServiceStatus.OK) {
                        createMarker(results[0]);
                    } else{
                        geocoder.geocode({'address': address  }, function (results, status) {
                            alert('${endereco.user.name?.encodeAsJavaScript()}')
                            if (status == google.maps.GeocoderStatus.OK) {
                                reateMarker(results[0]);
                            }

                        });

                    }
                });

            </g:each>

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

    </script>
</head>
<body>
<div id="map"></div>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOVREbzXvmpDVjFX7w5GXciMCWuBbnueM&signed_in=true&libraries=places&callback=initMap" async defer></script>
</body>
</html>