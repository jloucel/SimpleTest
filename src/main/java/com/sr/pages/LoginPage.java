package com.sr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {


    // ****Elements**** //


    private WebDriver driver;

    @FindBy(id = "okta-signin-username")
    private WebElement usernameInput;

    @FindBy(id="okta-signin-password")
    private WebElement passwordInput;

    @FindBy(id="okta-signin-submit")
    private WebElement signInButton;

    @FindBy(css = "p.okta-form-input-error")
    private List<WebElement> loginValidationErrors;

    @FindBy(className = "infobox-error")
    private WebElement errorContianter;

    @FindBy(css = "a.link.help.js-help")
    private WebElement helpLink;

    @FindBy(id = "input55")
    private WebElement rememberMeCheckbox;

    @FindBy(id = "qtip-2")
    private WebElement firstVisitTip;

    @FindBy(className = "qtip-close")
    private WebElement tipClose;


    // ****Constructor**** //


    public LoginPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    // ****Methods**** //


    public void clickSignInButton() {
        signInButton.click();
    }

    public void clickHelpLink() {
        helpLink.click();
    }

    public void typeUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public String getUserErrorMessage() {
        String errMsg = "";

        for(WebElement e : loginValidationErrors) {
            String msg = e.getText();
            if(msg.contains("username")) {
                errMsg = msg;
            }
        }
        return errMsg;
    }

    public String getPasswordErrorMessage() {
        String errMsg = "";

        for(WebElement e : loginValidationErrors) {
            String msg = e.getText();
            if(msg.contains("password")) {
                errMsg = msg;
            }
        }
        return errMsg;
    }

    public String getErrorContainerMessage() {
        return errorContianter.findElement(By.tagName("P")).getText();
    }
}