var marker;

function initMap() {
  // The location 
  var ist = {lat: 41, lng: 29};
  // The map, centered at 
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 16, center: ist});
  
  // The marker, positioned at   
  //  marker = new google.maps.Marker({position: ist, map: map});
  
  var  infoWindow = new google.maps.InfoWindow;
  
  if (navigator.geolocation) {
 
      navigator.geolocation.getCurrentPosition(function(position) {
    	          
       var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
       
       document.getElementById("formId:lat").value = pos.lat; 
       document.getElementById("formId:lng").value = pos.lng; 
       
       infoWindow.setPosition(pos);
       infoWindow.setContent('You are here');
       infoWindow.open(map);
       
        map.setCenter(pos);
        marker = new google.maps.Marker({position: pos, map: map}); 
      
      }, function() {
        handleLocationError(true, infoWindow, map.getCenter());
      });
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
  
  google.maps.event.addListener(map, 'click', function(event) {                
	    marker.setPosition(event.latLng);    
	    document.getElementById('formId:lat').value = event.latLng.lat();
	    document.getElementById('formId:lng').value = event.latLng.lng();
	    });

  }


  function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                          'Error: The Geolocation service failed.' :
                          'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
  }
  

 google.maps.event.addDomListener(window, 'load', initMap);
  
  
  
  