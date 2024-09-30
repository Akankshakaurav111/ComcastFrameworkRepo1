package com.comcast.crm.practiceBatchExeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;

public class Demo1Test extends BaseClass {
	//@Parameters("abc")
	@Test(groups = "smokeTest")
	public void createA() //String v
	{
		//System.out.println("===="+v+"====");
		System.out.println("create A");
	}
	@Test
	public void createB() {
		System.out.println("create B");
	}
	@Test(groups = "regressionTest")
	public void createC() {
		System.out.println("create C");
	}
}
