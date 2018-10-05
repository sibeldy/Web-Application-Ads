var map; //Will contain map object.
var marker = false; ////Has the user plotted their location marker? 

        
//Function called to initialize / create the map.
//This is called when the page has loaded.
function initMap() {
	
	var lat1 = getElementById('formId:lat');
	var lng1 = getElementById('formId:lng');
	
    //The center location of our map.
    var centerOfMap = new google.maps.LatLng(lat1, lng1);
    
  /*  //Map options.
    var options = {
      center: centerOfMap, //Set center.
      zoom: 5 //The zoom value.
    };
    
    //Create the map object.
    map = new google.maps.Map(document.getElementById('map'), options);*/
  
    marker = new google.maps.Marker({
        position: centerOfMap,
        map: map,
        draggable: true //make it draggable
    });    
   
    //Listen for any clicks on the map.
    google.maps.event.addListener(map, 'click', function(event) {                
    marker.setPosition(event.latLng);    
    document.getElementById('formId:lat').value = event.latLng.lat();
    document.getElementById('formId:lng').value = event.latLng.lng();
    });
    
  
	}
        
//Load the map when the page has finished loading.
google.maps.event.addDomListener(window, 'load', initMap);


//https://developers.google.com/maps/documentation/javascript/examples/marker-simple

