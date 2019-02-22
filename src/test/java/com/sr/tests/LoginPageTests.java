package com.sr.tests;

import com.sr.drivers.DriverFactory;
import com.sr.drivers.DriverType;
import com.sr.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class LoginPageTests {

private WebDriver driver = DriverFactory.getDriver(DriverType.CHROME);;
private String url = "https://login.solutionreach.com/";
private LoginPage loginPage = new LoginPage(driver);

// Error Message check strings
private final String expectedErrorMessage = "We found some errors. Please review the form and make corrections.";
private final String expectedUsernameAlertMessage = "Please enter a username";
private final String expectedPasswordAlertMessage = "Please enter a password";

    @Before
    public void beforeEach() {
        driver.get(url);
//        assertTrue("Expected " + url + " but found " + driver.getCurrentUrl(), driver.getCurrentUrl().equals(url));
        assertEquals(driver.getCurrentUrl(),url);
    }

    @Test
    public void usernameRequiredErrorDisplayed() {
        loginPage.typePassword("badPass");
        loginPage.clickSignInButton();

        assertEquals(loginPage.getErrorContainerMessage(), expectedErrorMessage);
        assertEquals(loginPage.getUserErrorMessage(), expectedUsernameAlertMessage);
    }

    @Test
    public void passwordReqiredErrorDisplayed() {
        loginPage.typeUsername("badUser");
        loginPage.clickSignInButton();

        assertEquals(loginPage.getErrorContainerMessage(), expectedErrorMessage);
        assertEquals(loginPage.getPasswordErrorMessage(), expectedPasswordAlertMessage);
    }

    @Test
    public void usernameAndPasswordReqiredErrorDisplayed() {
        loginPage.clickSignInButton();

        assertEquals(loginPage.getErrorContainerMessage(), expectedErrorMessage);
        assertEquals(loginPage.getUserErrorMessage(), expectedUsernameAlertMessage);
        assertEquals(loginPage.getPasswordErrorMessage(), expectedPasswordAlertMessage);
    }

    @After
    public void afterEach() {
        driver.close();
    }
}