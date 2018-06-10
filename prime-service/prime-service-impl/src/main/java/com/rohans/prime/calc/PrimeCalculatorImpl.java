package com.rohans.prime.calc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rohans.prime.entities.PrimeList;

/**
 * PrimeCalculator implementation class.
 * @author Rohan Sunder
 *
 */
@Service
public class PrimeCalculatorImpl implements PrimeCalculator {

	/**
	 * List of Primes calculated using a variation of the Sieve of Eratosthenes algorithm for calculating primes. 
	 * @return a PrimeList
	 */
	public PrimeList calculatePrimes(int start, int end) {
		
		// Calculate primes for numbers to the end number.
		boolean[] primes = new boolean[end + 1];
		for (int i = 2; i < primes.length; i++) {
			primes[i] = true;
		}
		int num = 2;
		while (true) {
			// All multiples of numbers are not prime, disqualify immediately.
			for (int i = 2;; i++) {
				int multiple = num * i;
				if (multiple > end) {
					break;
				} else {
					primes[multiple] = false;
				}
			}
			boolean nextNumFound = false;
			for (int i = num + 1; i < end + 1; i++) {
				if (primes[i]) {
					num = i;
					nextNumFound = true;
					break;
				}
			}
			if (!nextNumFound) {
				break;
			}
		}
		
		// Take final boolean array and only return the list of primes within the set boundaries.
		List<Long> primeList = new ArrayList<Long>();
		for (int i = 0; i < primes.length; i++) {
			if (i >= start) {
				if (primes[i]) {
					primeList.add(new Long(i));
				}
			}
		}
		
		// Create object
		PrimeList returnValue = new PrimeList(primeList, start, end);
		return returnValue;
	}


}
