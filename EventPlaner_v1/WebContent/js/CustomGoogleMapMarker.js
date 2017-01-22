$(document).ready(function () { /** Here hte function will start when HTML is finish with loading*/
	
	var map;			/** map is initialize*/
    var myOptions = {								/** preproperties for the map*/
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map($('#map_canvas')[0], myOptions); /**define the map */

    var addresses = [' Hochschule Furtwangen, Robert-Gerwig-Platz, 78120 Furtwangen im Schwarzwald, Deutschland', /** Test the addresses*/
                     ' BaumannstraÃŸe 7, 78120 Furtwangen im Schwarzwald, Deutschland', 
                     ' LuisenstraÃŸe 20, 78120 Furtwangen im Schwarzwald, Deutschland',
                     ' HinterschÃ¼tzenbach 6, 78120 Furtwangen im Schwarzwald, Deutschland',
                     ' LochhofstraÃŸe 19, 78120 Furtwangen im Schwarzwald, Deutschland',
                     ' Am GroÃŸhausberg 9, 78120 Furtwangen im Schwarzwald, Deutschland',
                     ' Singen'];
    var bounds = new google.maps.LatLngBounds();
    for (var x = 0; x < addresses.length; x++) {
    if(x==0){ /** The first address is the one for the event */
    	$.getJSON('http://maps.googleapis.com/maps/api/geocode/json?address='+addresses[x]+'&sensor=false', null, function (data) {
            var p = data.results[0].geometry.location;
            var latlng = new google.maps.LatLng(p.lat, p.lng);
            new google.maps.Marker({ /** set the eventmarker*/
                position: latlng,
                map: map,
                icon : 'img/flag.png',
                title: 'Event'
            });
            map.setCenter(p);	/** center the map to the event */
            var loc = new google.maps.LatLng(p.lat,p.lng);
            bounds.extends(loc);
            
        });
    }else{
    	$.getJSON('http://maps.googleapis.com/maps/api/geocode/json?address='+addresses[x]+'&sensor=false', null, function (data) {
            var p = data.results[0].geometry.location;
            var latlng = new google.maps.LatLng(p.lat, p.lng);
            new google.maps.Marker({	/** set the markers for the eventmembers*/
                position: latlng,
                map: map,
                icon : 'img/person.png',
                title: 'Eventteilnehmer'
            });
            var loc = new google.maps.LatLng(p.lat,p.lng);
            bounds.extends(loc);

            
        });
    }
    
    
    }
    map.fitBounds(bounds);
});
