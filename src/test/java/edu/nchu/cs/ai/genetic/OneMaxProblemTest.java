package edu.nchu.cs.ai.genetic;

import static org.junit.Assert.*;

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
		//0.003 seconds
		String result = OneMaxProblem.findOneMaxValue(10);
		Assert.assertEquals("1111111111", result);
	}

	@Test
	public void test20Bit() {
		//0.008 seconds
		String result = OneMaxProblem.findOneMaxValue(20);
		Assert.assertEquals("11111111111111111111", result);
	}

	@Test
	public void test30Bit() {
		//1.223 seconds
		String result = OneMaxProblem.findOneMaxValue(30);
		Assert.assertEquals("111111111111111111111111111111", result);
	}

	@Test
	public void test40Bit() {
		//743.641 seconds
		String result = OneMaxProblem.findOneMaxValue(40);
		Assert.assertEquals("1111111111111111111111111111111111111111", result);
	}
	
//	@Test
	public void test50Bit() {
		//~= 8 days
		String result = OneMaxProblem.findOneMaxValue(50);
		Assert.assertEquals("11111111111111111111111111111111111111111111111111", result);
	}
}
