package com.automation.stepDefinitions;

import static org.testng.AssertJUnit.*;
import com.automation.pageObjects.*;
import com.automation.utils.SeleniumUtils;
import cucumber.api.java.en.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class LoginStepDefinition {

    @Given("I have logged into Automation Practice")
    public void loginAutomationPractice() {
        PageFactory.initElements(Hooks.driver, LoginPage.class);
        SeleniumUtils.navigateToURL(Hooks.url);
        SeleniumUtils.clickElement(LoginPage.loginElement);
        LoginPage.login(Hooks.email, Hooks.password);
        //assert log in was successful
        String actualTextaccount= SeleniumUtils.getTextWebElement(LoginPage.accountElement);
        assertEquals(actualTextaccount, "automation test");
    }

    @Then("I log out Automation Practice")
    public void i_log_out_Automation_Practice() {
        PageFactory.initElements(Hooks.driver, LoginPage.class);
        SeleniumUtils.clickElement(LoginPage.logoutElement);
        //assert log out was successful
        String actualTextalreadyRegistered = SeleniumUtils.getTextWebElement(LoginPage.alreadyRegistered);
        assertEquals(actualTextalreadyRegistered, "ALREADY REGISTERED?");
    }
}
