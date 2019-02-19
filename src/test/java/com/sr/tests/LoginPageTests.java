package com.sr.tests;

import com.sr.drivers.DriverFactory;
import com.sr.drivers.DriverType;
import com.sr.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertTrue;

public class LoginPageTests {

private WebDriver driver = DriverFactory.getManager(DriverType.FIREFOX);;
private String url = "https://login.solutionreach.com/";
private LoginPage loginPage = new LoginPage(driver);

// Error Message check strings
private final String expectedErrorMessage = "We found some errors. Please review the form and make corrections.";
private final String expectedUsernameAlertMessage = "Please enter a username";
private final String expectedPasswordAlertMessage = "Please enter a password";

    @Before
    public void beforeEach() {
        driver.get(url);
        assertTrue("Expected " + url + " but found " + driver.getCurrentUrl(), driver.getCurrentUrl().equals(url));
    }

    @Test
    public void usernameRequiredErrorDisplayed() {
        loginPage.typePassword("badPass");
        loginPage.clickSignInButton();

        assertTrue("Expected " + expectedErrorMessage + " but found " + loginPage.getErrorContainerMessage(),
                loginPage.getErrorContainerMessage().equals(expectedErrorMessage));
        assertTrue("Expected " + expectedUsernameAlertMessage + " but found " + loginPage.getUserErrorMessage(),
                loginPage.getUserErrorMessage().equals(expectedUsernameAlertMessage));
    }

    @Test
    public void passwordReqiredErrorDisplayed() {
        loginPage.typeUsername("badUser");
        loginPage.clickSignInButton();

        assertTrue("Expected " + expectedErrorMessage + " but found " + loginPage.getErrorContainerMessage(),
                loginPage.getErrorContainerMessage().equals(expectedErrorMessage));
        assertTrue("Expected " + expectedPasswordAlertMessage + " but found " + loginPage.getPasswordErrorMessage(),
                loginPage.getPasswordErrorMessage().equals(expectedPasswordAlertMessage));
    }

    @Test
    public void usernameAndPasswordReqiredErrorDisplayed() {
        loginPage.clickSignInButton();

        assertTrue("Expected " + expectedErrorMessage + " but found " + loginPage.getErrorContainerMessage(),
                loginPage.getErrorContainerMessage().equals(expectedErrorMessage));
        assertTrue("Expected " + expectedUsernameAlertMessage + " but found " + loginPage.getUserErrorMessage(),
                loginPage.getUserErrorMessage().equals(expectedUsernameAlertMessage));
        assertTrue("Expected " + expectedPasswordAlertMessage + " but found " + loginPage.getPasswordErrorMessage(),
                loginPage.getPasswordErrorMessage().equals(expectedPasswordAlertMessage));
    }

    @After
    public void afterEach() {
        driver.close();
    }
}