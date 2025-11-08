package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;





public class RegisterPageTest extends BaseTest {

	
	
	//BT
	
	
	@BeforeClass
	public void goToRegisterPage()
	{
		registerPage=loginPage.navigateToRegisterPage();
	}
@DataProvider


public Object[][] getRegData() {
	return new Object[][]
			{
		{"harpreet","automation","9876452336","harpreet@123","yes"},
		{"ratul","shaha","9876452136","ratul@123","no"},
		{"hellan","automation","9876412336","hellan@123","yes"}
			};
}


@DataProvider
public Object[][] getRegSheetData()
{
	return ExcelUtil.getTestData("register");
}
@DataProvider
public Object[][] getRegCSVData()

{
	
	return CSVUtil.csvData("register");
}

	
	

	@Test(dataProvider ="getRegSheetData")
	public void registerTest(String firstName,String lastName,String telephone,String password,String subscribe)
	{
		Assert.assertTrue
		(registerPage.userRegister(firstName,lastName,StringUtils.getRandomEMail(),telephone,password,subscribe));
	}
}


