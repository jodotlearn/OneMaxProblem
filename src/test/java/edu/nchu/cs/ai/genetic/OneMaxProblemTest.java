package edu.nchu.cs.ai.genetic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class OneMaxProblemTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test10Bit() {
		//0.006 seconds
		String result = OneMaxProblem.exhaustiveSearch(10);
		Assert.assertEquals("1111111111", result);
	}

	@Test
	public void test20Bit() {
		//0.024 seconds
		String result = OneMaxProblem.exhaustiveSearch(20);
		Assert.assertEquals("11111111111111111111", result);
	}

	@Test
	public void test30Bit() {
		//10.006 seconds
		String result = OneMaxProblem.exhaustiveSearch(30);
		Assert.assertEquals("111111111111111111111111111111", result);
	}
	
	@Test
	public void test40Bit() {
		//13931.293 seconds
		String result = OneMaxProblem.exhaustiveSearch(40);
		Assert.assertEquals("1111111111111111111111111111111111111111", result);
	}
	
//	@Test
	public void test50Bit() {
		//~= 8 days
		String result = OneMaxProblem.exhaustiveSearch(50);
		Assert.assertEquals("11111111111111111111111111111111111111111111111111", result);
	}
}
