package com.rohans.prime.entities;

import java.util.List;


/**
 * Simple class creating an object of the prime list with an associated id.
 * @author Rohan Sunder
 *
 */
public class PrimeList {
	
	private String id;
	private int start;
	private int end;
	private List<Long> primes;
	
	public PrimeList(List<Long> primes, int start, int end) {
		this.primes = primes;
		this.id = start + end + "";
		
		this.start = start;
		this.end = end;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setPrimes(List<Long> primes) {
		this.primes = primes;
	}
	
	public List<Long> getPrimes() {
		return this.primes;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public long getStart() {
		return this.start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public long getEnd() {
		return this.end;
	}

}
