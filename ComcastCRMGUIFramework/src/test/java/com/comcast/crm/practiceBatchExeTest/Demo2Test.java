package com.comcast.crm.practiceBatchExeTest;

import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;

public class Demo2Test extends BaseClass
{
	@Test(groups = "regressionTest")
	public void createD()
	{
		System.out.println("create D");
	}
	@Test(groups = "smokeTest")
	public void createE()
	{
		System.out.println("create E");
	}
	@Test(groups = {"smokeTest","regressionTest"})
	public void createF()
	{
		System.out.println("create F");
	}
	}

