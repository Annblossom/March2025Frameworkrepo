package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
@Epic("EP-100:Design the  Open Cart App Login Page")
@Feature("F-101:design open cart login feature")

@Story("US-50: develop login core features: title,url.user is able to login")
public class LoginPageTest extends BaseTest {
@Description("login page title test...")
@Owner("Naveen Automation Labs")


	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page title :"+ actTitle);
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	@Test
	@Description("login page title test...")
	@Owner("Naveen Automation Labs")
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		ChainTestListener.log("login page url :"+ actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test
	@Description("forgot password link test...")
	@Owner("Naveen Automation Labs")
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	@Owner("Naveen Automation Labs")
	@Test
	public void isLoginPageHeaderExistTest() {
		Assert.assertTrue(loginPage.isheaderExist());
	}
	
	@Description("user is able to login to app with the correct credentials....")
	@Owner("Naveen Automation Labs")
	
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		
		accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
}
	
}
