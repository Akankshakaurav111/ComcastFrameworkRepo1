package com.comcast.crm.generic.webdriverutility;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;

import io.opentelemetry.sdk.metrics.data.Data;

public class JavaUtility 
{
public int getRandomNumber()
{
	Random random = new Random();
	int randomNumber=random.nextInt(1000);
	return randomNumber;
}
public String getSystemDateYYYYDDMM()
{
	Date dateObj=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(dateObj);
	return date;
}
public String getRequriedDateYYYYDDMM(int days)
{
	Date dateObj=new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	sim.format(dateObj);
	Calendar cal=sim.getCalendar();
    cal.add(Calendar.DAY_OF_MONTH,days);
    String reqDate = sim.format(cal.getTime());
    return reqDate;
}

}
