package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "identifierId")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordInput;
    @FindBy(id = "identifierNext")
    private WebElement identifierNextButton;
    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;
    @FindBy(xpath = "//span[text()[contains(., \"Готово\")]]")
    private WebElement readyButton;

    private  String url = "https://www.gmail.com";

    public LoginPage() {
        PageFactory.initElements(WebDriverSingleton.getInstance(), this);
    }

    private WebDriver getDriver() { return WebDriverSingleton.getInstance(); }

    public void enterMainPage() { getDriver().get(url); }

    public void typeEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void typePassword(String pass) {
        passwordInput.clear();
        passwordInput.sendKeys(pass);
    }

    public void clickIdentifierNextButton() {
        identifierNextButton.click();
    }

    public MailPage clickPasswordNextButton() {
        passwordNextButton.click();
        return new MailPage();
    }
}
