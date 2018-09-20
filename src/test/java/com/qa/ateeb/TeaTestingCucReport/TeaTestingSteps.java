package com.qa.ateeb.TeaTestingCucReport;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.ScreenshotMethod;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class TeaTestingSteps {
	
	WebDriver driver;
	
	public static ExtentReports report; //REPORTING
	public ExtentTest test; //REPORTING
	
	@Before
	public void setup() {

	System.setProperty("webdriver.chrome.driver", Constants.chromeDriverRoot);
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	}

	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		driver.get(Constants.websiteURL);
		
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		TeaTestingHome homePage = PageFactory.initElements(driver, TeaTestingHome.class);
		homePage.clickMenuButton();
		
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		ScreenshotMethod.screenshot(driver);
		
		test = TeaRunner.report.startTest("Menu Page Title Test"); //REPORTING
		
		test.log(LogStatus.INFO, "Testing Menu Page ..."); //REPORTING
		if (driver.getTitle().equals("Menu")) { //REPORTING
			
			test.log(LogStatus.PASS, "Successful Page Title Was Found");
			//System.out.println("true");
			
		}
		
		else { //REPORTING
			
			test.log(LogStatus.FAIL, "Unsuccessful! Page Title Was NOT Correct");
			//System.out.println("false");
			
		}
		
		TeaRunner.report.endTest(test); //REPORTING
		
		assertEquals("Navigated to incorrect page", "Menu", driver.getTitle());
		
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		TeaTestingHome homePage = PageFactory.initElements(driver, TeaTestingHome.class);
		TeaTestingMenu menuPage = PageFactory.initElements(driver, TeaTestingMenu.class);
		homePage.clickMenuButton();
		menuPage.clickCheckoutButton();
		
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		test = TeaRunner.report.startTest("Checkout Page Title Test"); //REPORTING
		
		test.log(LogStatus.INFO, "Testing Check Out Page ..."); //REPORTING
		if (driver.getTitle().equals("Check Out")) { //REPORTING
			
			test.log(LogStatus.PASS, "Successful Page Title Was Found");
			//System.out.println("true");
			
		}
		
		else { //REPORTING
			
			test.log(LogStatus.FAIL, "Unsuccessful! Page Title Was NOT Correct");
			//System.out.println("false");
			
		}
		
		TeaRunner.report.endTest(test); //REPORTING
		
		assertEquals("Navigated to incorrect page", "Check Out", driver.getTitle());
		
	}
	
	@After
	public void tearDown() throws InterruptedException {
		
		TeaRunner.report.flush();
		Thread.sleep(2000);
		driver.quit();
		
	}

}
