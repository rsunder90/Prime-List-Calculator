package com.rohans.prime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;

import com.rohans.prime.calc.PrimeCalculator;
import com.rohans.prime.calc.PrimeCalculatorImpl;
import com.rohans.prime.entities.PrimeList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimeServerApplicationTests {
	
	@Autowired
	private PrimeCalculator primeCalculator;

	@Test
	public void contextLoads() {
		
		assertThat(primeCalculator, instanceOf(PrimeCalculatorImpl.class));
	}
	
	@Test
	public void testSize() {
		
		PrimeList testObj = this.primeCalculator.calculatePrimes(2, 10);
		List<Long> generatedList = testObj.getPrimes();
		
		ArrayList<Long> actual = new ArrayList<Long>();
		
		actual.add(new Long(2));
		actual.add(new Long(3));
		actual.add(new Long(5));
		actual.add(new Long(7));
		
		assertThat(generatedList.size(), is(actual.size()));
		
	}
	
	@Test
	public void testNumbers() {
		PrimeList testObj = this.primeCalculator.calculatePrimes(3, 11);
		List<Long> generatedList = testObj.getPrimes();
		Long[] generatedListArray = generatedList.toArray(new Long[generatedList.size()]);
		
		Long[] actual = new Long[4];
		
		actual[0] = new Long(3);
		actual[1] = new Long(5);
		actual[2] = new Long(7);
		actual[3] = new Long(11);
		
		assertArrayEquals(generatedListArray, actual);
		
	}

}
