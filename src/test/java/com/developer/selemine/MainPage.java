package com.developer.selemine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public static final String siteUrl = "https://hueviebin1.livejournal.com/";
    public static final String login = "Test4tests";
    public static final String password = "Qwaser12";
    @FindBy(css = ".s-header-item__link--login")
    private WebElement loginButton;
    @FindBy(css = "#user")
    private WebElement nameField;
    @FindBy(css = "#lj_loginwidget_password")
    private WebElement passwordField;
    @FindBy(css = "[name='action:login']")
    private WebElement submitButton;
    @FindBy(css = ".s-nav-item__name")
    private WebElement loggedInNameField;
    @FindBy(xpath = "//*[contains(text(), 'Личное сообщение')]")
    private WebElement sendPMButton;
    @FindBy(css = "[name='msg_to']")
    private WebElement msgToSendField;
    @FindBy(css = ".s-header-sub-list-item__link--logout")
    private WebElement logoutField;
    @FindBy(css = ".s-logo-title")
    private WebElement mainPageLink;
    @FindBy(css = "[href='https://www.livejournal.com/ratings/category/choice']")
    private WebElement textSection;

    private WebDriver webDriver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public String checkLoggedName() {
        return loggedInNameField.getText();
    }

    public String getMsgToSendName() {
        return msgToSendField.getAttribute("value");
    }

    public void clickSendPMButton() {
        sendPMButton.click();
    }

    public void loginMethod() {
        clickLoginButton();
        enterName(login);
        enterPassword(password);
        clickSubmitButton();
    }

    public void logOutMethod() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(loggedInNameField, 10, 10).perform();
        logoutField.click();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public void clickMainPageButton() {
        mainPageLink.click();
    }

    public String getTextFromMainPage() {
        return textSection.getText();
    }
}
