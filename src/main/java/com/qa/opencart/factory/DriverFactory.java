package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;



public class DriverFactory {

	
	public WebDriver driver;
	public Properties prop;
	
	public static String highlightEle;
	
	public static ThreadLocal<WebDriver> tlDriver =new ThreadLocal<WebDriver>();
	
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	public OptionsManager optionsManager;

public WebDriver initDriver(Properties prop)
{
String browserName= prop.getProperty("browser");
	//System.out.println("browser name : "+browserName);
log.info("browser name : "+ browserName);
	ChainTestListener.log("Running Browser: "+ browserName);
highlightEle =prop.getProperty("highlight");
optionsManager = new OptionsManager(prop);

switch(browserName.trim().toLowerCase()) {
case "chrome":
	
	
	tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
	break;
case "firefox":
	
	
	tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
	break;
case "edge":
	
	
	tlDriver.set(new EdgeDriver());
	break;
case "safari":
	

	tlDriver.set(new SafariDriver());
	break;
	
	default:
		
		
		log.error(AppError.INVALID_BROWSER_MESG +":"+browserName);
		throw new FrameworkException(" ===INVALID BROWSER=== ");
	
}
getDriver().manage().deleteAllCookies();
getDriver().manage().window().maximize();

getDriver().get(prop.getProperty("url"));
return getDriver();
}

public static WebDriver getDriver()
{
	return tlDriver.get();
	
}

public Properties initProp()
{
	
	prop=new Properties();
	FileInputStream ip = null;

	String envName = System.getProperty("env");
	log.info("Env name =======>" + envName);

	try {
		if (envName == null) {
			log.warn("no env.. is passed, hence running tcs on QA environment...by default..");
			ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}

		else {
			switch (envName.trim().toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			default:
				log.error("Env value is invalid...plz pass the right env value..");
				throw new FrameworkException("====INVALID ENVIRONMENT====");
			}
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}

	return prop;
}
}

		//