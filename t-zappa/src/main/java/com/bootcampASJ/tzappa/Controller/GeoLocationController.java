package com.bootcampASJ.tzappa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcampASJ.tzappa.Services.GeoLocationService;

@RestController
@RequestMapping("/geoLocations") // localhost:8080/geoLocations
public class GeoLocationController {

	@Autowired
	private GeoLocationService geoLocationService;
	
	@GetMapping // [GET] localhost:8080/geoLocations
	public ResponseEntity<Object> getProviders() {		
		return ResponseEntity.ok( this.geoLocationService.getGeoLocations() );
	}
}
