// When the window has finished loading create our google map below
        google.maps.event.addDomListener(window, 'load', init);

        function init() {
            // Basic options for a simple Google Map
            // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
            var mapOptions = {
                // How zoomed in you want the map to start at (always required)
                zoom: 18,
                scrollwheel: false,

                // The latitude and longitude to center the map (always required)
                center: new google.maps.LatLng(23.780276,  90.41671199999996), // New York

                // How you would like to style the map.
                // This is where you would paste any style found on Snazzy Maps.
                styles: [{ "featureType": "administrative", "elementType": "labels.text.fill", "stylers": [{ "color": "#444444" }] }, { "featureType": "administrative.locality", "elementType": "labels.text", "stylers": [{ "visibility": "on" }] }, { "featureType": "administrative.neighborhood", "elementType": "labels.text", "stylers": [{ "visibility": "off" }] }, { "featureType": "landscape", "elementType": "all", "stylers": [{ "color": "#e1e1e1" }, { "saturation": "0" }] }, { "featureType": "poi", "elementType": "geometry.fill", "stylers": [{ "color": "#d1d1d1" }] }, { "featureType": "poi.attraction", "elementType": "geometry.fill", "stylers": [{ "visibility": "off" }, { "color": "#d1d1d1" }] }, { "featureType": "poi.attraction", "elementType": "labels.text", "stylers": [{ "visibility": "on" }] }, { "featureType": "poi.business", "elementType": "geometry.fill", "stylers": [{ "saturation": "-3" }, { "lightness": "-4" }, { "gamma": "4.82" }, { "weight": "1.39" }, { "visibility": "off" }] }, { "featureType": "poi.business", "elementType": "labels.text", "stylers": [{ "visibility": "off" }] }, { "featureType": "poi.business", "elementType": "labels.icon", "stylers": [{ "visibility": "off" }] }, { "featureType": "poi.government", "elementType": "geometry.fill", "stylers": [{ "color": "#d1d1d1" }, { "visibility": "off" }] }, { "featureType": "poi.medical", "elementType": "geometry.fill", "stylers": [{ "visibility": "off" }, { "color": "#d1d1d1" }] }, { "featureType": "poi.park", "elementType": "geometry.fill", "stylers": [{ "visibility": "on" }, { "color": "#ebebeb" }] }, { "featureType": "poi.park", "elementType": "labels", "stylers": [{ "visibility": "on" }] }, { "featureType": "poi.place_of_worship", "elementType": "geometry.fill", "stylers": [{ "visibility": "on" }, { "color": "#d1d1d1" }] }, { "featureType": "poi.school", "elementType": "geometry.fill", "stylers": [{ "color": "#d1d1d1" }, { "visibility": "off" }] }, { "featureType": "poi.sports_complex", "elementType": "geometry.fill", "stylers": [{ "visibility": "on" }, { "color": "#d1d1d1" }] }, { "featureType": "road", "elementType": "all", "stylers": [{ "saturation": -100 }, { "lightness": 45 }] }, { "featureType": "road", "elementType": "labels.text.fill", "stylers": [{ "color": "#333333" }] }, { "featureType": "road.highway", "elementType": "geometry.fill", "stylers": [{ "color": "#ffffff" }, { "visibility": "on" }] }, { "featureType": "road.highway", "elementType": "geometry.stroke", "stylers": [{ "visibility": "off" }] }, { "featureType": "road.highway", "elementType": "labels", "stylers": [{ "visibility": "off" }] }, { "featureType": "road.arterial", "elementType": "labels.icon", "stylers": [{ "visibility": "off" }] }, { "featureType": "road.local", "elementType": "geometry.fill", "stylers": [{ "saturation": "6" }, { "hue": "#ff0000" }, { "visibility": "on" }] }, { "featureType": "transit", "elementType": "all", "stylers": [{ "visibility": "off" }] }, { "featureType": "transit", "elementType": "labels", "stylers": [{ "visibility": "on" }] }, { "featureType": "transit", "elementType": "labels.text.fill", "stylers": [{ "color": "#333333" }] }, { "featureType": "water", "elementType": "all", "stylers": [{ "color": "#00667d" }, { "visibility": "on" }] }, { "featureType": "water", "elementType": "geometry.fill", "stylers": [{ "color": "#cecece" }] }, { "featureType": "water", "elementType": "labels.text.fill", "stylers": [{ "color": "#ffffff" }] }, { "featureType": "water", "elementType": "labels.text.stroke", "stylers": [{ "visibility": "off" }] }]
            };

            // Get the HTML DOM element that will contain your map
            // We are using a div with id="map" seen below in the <body>
            var mapElement = document.getElementById('map');

            // Create the Google Map using our element and options defined above
            var map = new google.maps.Map(mapElement, mapOptions);

            // Let's also add a marker while we're at it
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(23.780276, 90.41671199999996),
                map: map,
                title: 'Bite On Bite',
                icon: 'images/marker.png'
            });
        }