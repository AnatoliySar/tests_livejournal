package com.developer.selemine;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

import static com.developer.selemine.MainPage.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(siteUrl);

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginTest() {
        mainPage.loginMethod();

        assertTrue(mainPage.checkLoggedName().equalsIgnoreCase(login),
                "Gotten logged in name field is empty or is different from expected!");
    }

    @Test
    public void msgReceiverNameTest() {
        mainPage.loginMethod();
        mainPage.clickSendPMButton();
        String gotReceiverName = mainPage.getMsgToSendName();
        String expectedReceiverName = siteUrl.substring(siteUrl.indexOf("//") + 2, siteUrl.indexOf("."));
        System.out.println("gotReceiverName: " + gotReceiverName + ", expectedReceiverName: " + expectedReceiverName);

        assertTrue(expectedReceiverName.equalsIgnoreCase(gotReceiverName),
                "Gotten message receiver name is different from expected!");
    }

    @Test
    public void logOutTest() {
        mainPage.loginMethod();
        mainPage.logOutMethod();

        assertTrue(mainPage.isLoginButtonDisplayed(), "Login button is not visible!");
    }

    @Test
    public void mainPageTest() {
        mainPage.clickMainPageButton();
        String gotStrMainPage = mainPage.getTextFromMainPage();
        String expectedStrMainPage = "ЖЖ РЕКОМЕНДУЕТ";
        assertTrue(expectedStrMainPage.equalsIgnoreCase(gotStrMainPage),
                "Have not found expected topic 'ЖЖ РЕКОМЕНДУЕТ'");
    }
}
