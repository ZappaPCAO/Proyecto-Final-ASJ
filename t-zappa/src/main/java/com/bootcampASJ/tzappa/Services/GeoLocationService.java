package com.bootcampASJ.tzappa.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcampASJ.tzappa.Models.Country;
import com.bootcampASJ.tzappa.Models.State;
import com.bootcampASJ.tzappa.Models.City;

import com.bootcampASJ.tzappa.Repositories.CityRepository;
import com.bootcampASJ.tzappa.Repositories.CountryRepository;
import com.bootcampASJ.tzappa.Repositories.StateRepository;

@Service
public class GeoLocationService {

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;	
	
	public List<Map<String, Object>> getGeoLocations() {
		List<Country> countries = this.countryRepository.findAll();
		
		List<Map<String, Object>> resultado = new ArrayList<>();

        for (Country country : countries) {
            Map<String, Object> countryMap = new HashMap<>();

            List<State> states = stateRepository.findByCountry(country);
            List<Map<String, Object>> statesMap = new ArrayList<>();

            for (State state : states) {
                Map<String, Object> stateMap = new HashMap<>();                

                List<City> cities = cityRepository.findByState(state);
                List<Map<String, Object>> localidadesMap = new ArrayList<>();

                for (City city : cities) {
                    Map<String, Object> cityMap = new HashMap<>();
                    cityMap.put("id", city.getId());
                    cityMap.put("name", city.getName());

                    localidadesMap.add(cityMap);
                }

                stateMap.put("id", state.getId());
                stateMap.put("name", state.getName());
                stateMap.put("cities", localidadesMap);
                statesMap.add(stateMap);
            }
            countryMap.put("id", country.getId());
            countryMap.put("name", country.getName());
            countryMap.put("states", statesMap);
            
            resultado.add(countryMap);
        }
        return resultado;
	}
}
