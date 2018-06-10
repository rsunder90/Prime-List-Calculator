package com.rohans.prime.calc;

import com.rohans.prime.entities.PrimeList;

/**
 * Interface for defining the PrimeCalculator. Class implemented in the impl package.
 * @author Rohan Sunder
 *
 */
public interface PrimeCalculator {
	
	public PrimeList calculatePrimes(int start, int end);

}
