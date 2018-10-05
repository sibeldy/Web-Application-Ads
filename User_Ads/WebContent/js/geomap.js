

function initMap() {
  // The location of Uluru
  var ist = {lat: 41, lng: 29};
  // The map, centered at Uluru
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 16, center: ist});
  // The marker, positioned at Uluru
//  marker = new google.maps.Marker({position: ist, map: map});
  
  var  infoWindow = new google.maps.InfoWindow;
  
  if (navigator.geolocation) {
	  navigator.geolocation.getCurrentPosition(showPosition);
      navigator.geolocation.getCurrentPosition(function(position) {
    	          
       var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
        infoWindow.setPosition(pos);
        infoWindow.setContent('You are here');
        infoWindow.open(map);
        map.setCenter(pos);
       var marker = new google.maps.Marker({position: pos, map: map});     
      }, function() {
        handleLocationError(true, infoWindow, map.getCenter());
      });
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
  }

  function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                          'Error: The Geolocation service failed.' :
                          'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
  }
  
  
  var lat;
  var lng;

  // users current location using HTML5 geolocation.

  if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
  }

  function showPosition(position) {
      var lat = position.coords.latitude; 
      var lng = position.coords.longitude;
      console.log("lat: " + lat + "lng: " + lng); // works fine prints current position
      document.getElementById("adlist:lat").value = lat; 
      document.getElementById("adlist:lng").value = lng;
  }

  
  