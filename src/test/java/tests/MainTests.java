package tests;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MailPage;
import util.Helper;

import java.io.IOException;

public class MainTests {

    private final static String USER_NAME = "@gmail.com";
    private final static String USER_PASS = "";

    @Test
    public void loginTest() {
        WebDriver driver = WebDriverSingleton.getInstance();
        LoginPage loginPage = new LoginPage();
        loginPage.enterMainPage();
        loginPage.typeEmail(USER_NAME);
        loginPage.clickIdentifierNextButton();
        loginPage.typePassword(USER_PASS);
        Helper.waitForTime(1);
        MailPage mailPage = loginPage.clickPasswordNextButton();
        Helper.waitForTime(2);
        Assert.assertTrue(driver.getTitle().contains("Входящие"));
        Assert.assertTrue(driver.getTitle().contains(USER_NAME));
        Assert.assertTrue(driver.getTitle().contains("Gmail"));
    }

    @Test
    public void sendMail() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterMainPage();
        loginPage.typeEmail(USER_NAME);
        loginPage.clickIdentifierNextButton();
        loginPage.typePassword(USER_PASS);
        Helper.waitForTime(1);
        MailPage mailPage = loginPage.clickPasswordNextButton();
        Helper.waitForTime(2);
        mailPage.clickWriteButton();
        mailPage.typeRecipient(USER_NAME);
        mailPage.typeSubject("Test mail");
        mailPage.typeLetterText("Hello! This message is test.");
        mailPage.clickSendButton();
        Helper.waitForTime(3);
        mailPage.clickOnNewMessage();
        Helper.waitForTime(2);
        Assert.assertTrue(mailPage.getMessageText().equals("Hello! This message is test."));
    }

    @Test (dependsOnMethods={"sendMail"})
    public void deleteMail() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterMainPage();
        loginPage.typeEmail(USER_NAME);
        loginPage.clickIdentifierNextButton();
        loginPage.typePassword(USER_PASS);
        Helper.waitForTime(1);
        MailPage mailPage = loginPage.clickPasswordNextButton();
        Helper.waitForTime(2);
        mailPage.clickOnNewMessage();
        Helper.waitForTime(2);
        mailPage.clickOnDeleteThisMessage();
        Helper.waitForTime(1);
        mailPage.goToTrash();
        mailPage.clickOnNewMessage();
        Helper.waitForTime(2);
        Assert.assertTrue(mailPage.getMessageText().equals("Hello! This message is test."));
    }

    @AfterMethod
    public void shutDown() throws IOException {
        Helper.closeBrowser();
    }
}
