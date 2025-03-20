package com.mac.bTestNg;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class TestNg05Use {

	@Test(expectedExceptions = ArithmeticException.class)
	public void loginTest() {
		System.out.println("login test");
		int i = 9/0;
		System.out.println(i);
	}

	@Test(dependsOnMethods = "loginTest")
	public void HomePageTest() {
		System.out.println("Home Page test");
	}
	
	@Test(dependsOnMethods="loginTest")
	public void SearchPageTest() {
	System. out.println("SearchPageTest");
	}

	@Test(dependsOnMethods="loginTest")
	public void RegPageTest() {
	System.out.println("RegPageTest");
	}
	
	
	@Test(invocationCount = 10)
	public void sumTest() {
		int a = 9;
		int b = 9;
		int c = a+b;
		
		System.out.println("Sum is " + c);
	}
	
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void test() {
	String x = "100A";
	Integer .parseInt(x);
	}
	
	
	@Test(groups= "exceptionTimeOut", invocationTimeOut = 1000, enabled = false)
	public void infinteLoop() {
		int i = 1;
		while(i==1) {
			System.out.println(i);
		}
	}
	
	
	@AfterSuite
	public void tearDown() {
		System.out.println("Logout test");
	}

}
