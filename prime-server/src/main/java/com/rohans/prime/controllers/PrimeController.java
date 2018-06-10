package com.rohans.prime.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rohans.prime.calc.PrimeCalculator;
import com.rohans.prime.entities.PrimeList;

/**
 * Rest Controller for the Prime object endpoints.
 * @author Rohan Sunder
 *
 */
@RestController
public class PrimeController {
	
	@Autowired
	private PrimeCalculator primeCalculator;
	
	@Autowired 
	private RedisTemplate< String, Object> template;

	
	/**
	 * Calculates primes requiring a start and end number. If start/end are the same, it returns the same primes.
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/calculatePrimes", method = RequestMethod.POST)
	public ResponseEntity<String> calculatePrimesFromTwoBounds(@RequestParam int start, @RequestParam int end) {
		
		String id = start + end + "";
		String primeList = this.getPrimeList(id);
		if (primeList != null) {
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} else if (start < 0 || end < 0) {
			return new ResponseEntity<String>("Invalid numbers", HttpStatus.NOT_ACCEPTABLE);
		} else if (end < start) {
			return new ResponseEntity<String>("End number cannot be smaller than start.", HttpStatus.NOT_ACCEPTABLE);
		}
		
		PrimeList newVal = this.primeCalculator.calculatePrimes(start, end);
		System.out.println(this.generateList(newVal.getPrimes()));
		this.setList(newVal);
		
		return new ResponseEntity<String>(newVal.getId(), HttpStatus.OK);
	}
	
	/**
	 * GET Method for getting the list of primes.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPrimes", method = RequestMethod.GET)
	public ResponseEntity<String> returnPrimeList(@RequestParam String id) {
		
		String result = this.getPrimeList(id);
		if (result == null) {
			return new ResponseEntity<String>("Invalid Id.", HttpStatus.OK);
		}
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * Utility method for adding id to the redis cache.
	 * @param list
	 */
	private void setList(PrimeList list) {
		
		String key = list.getId();
		Map<String, Object> properties = new HashMap<String, Object>();
		String listString = this.generateList(list.getPrimes());

		properties.put("primes", listString);

		template.opsForHash().putAll( key, properties);
	}
	
	/**
	 * Utility method for getting the prime list from the Redis Cache
	 * @param id
	 * @return
	 */
	private String getPrimeList(String id) {
		 
		 String primes = (String)template.opsForHash().get(id, "primes" );
		 return primes;
	}
	
	/**
	 * Converts an ArrayList to a string for serialization purposes.
	 * @param list
	 * @return
	 */
	private String generateList(List<Long> list) {
		StringBuilder sb = new StringBuilder();
		
		for (Long l : list) {
			sb.append(l + ", ");
		}
		return sb.toString();
	}

}
