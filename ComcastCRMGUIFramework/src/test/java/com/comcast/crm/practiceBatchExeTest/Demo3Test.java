package com.comcast.crm.practiceBatchExeTest;

import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;

public class Demo3Test extends BaseClass
{
	@Test(invocationCount = 2,threadPoolSize = 2)
	public void createG()
	{
		System.out.println("create G");
	}
//	@Test(groups = "regressionTest")
//	public void createH()
//	{
//		System.out.println("create H");
//	}
//	@Test(groups = "smokeTest")
//	public void createI()
//	{
//		System.out.println("create I");
//	}
	}

