package pages;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailPage {
    @FindBy(xpath = "//div[text()[contains(., \"Написать\")]]")
    private WebElement writeButton;
    @FindBy(xpath = "//textarea[@name = 'to']")
    private WebElement recipientInput;
    @FindBy(xpath = "//input[@name = 'subjectbox']")
    private WebElement subjectInput;
    @FindBy(xpath = "//div[@role = 'textbox']")
    private WebElement letterInput;
    @FindBy(xpath = "//div[@class = \"dC\"]//div[text()[contains(., \"Отправить\")]]")
    private WebElement sendButton;
    @FindBy(xpath = "//table//td//span[text()[contains(., \"Hello! This message is test.\")]]")
    private WebElement newMessage;
    @FindBy(xpath = "//div[@dir='ltr'][text()[contains(., \"Hello! This message is test.\")]]")
    private WebElement newMessageText;
    @FindBy(xpath = "//span//span[text()[contains(., \"Ещё\")]]")
    private WebElement moreButton;
    @FindBy(xpath = "//span//a[@href = \"https://mail.google.com/mail/#trash\"]")
    private WebElement trashButton;
    @FindBy(xpath = "//div[@role = 'button'][@aria-label = 'Ещё']")
    private WebElement moreButton2;
    @FindBy(xpath = "//div[text()[contains(., \"Удалить это письмо\")]]")
    private WebElement deleteThisMessage;

    public MailPage() {
        PageFactory.initElements(WebDriverSingleton.getInstance(), this);
    }

    public void typeRecipient(String email) {
        recipientInput.clear();
        recipientInput.sendKeys(email);
    }

    public void typeSubject(String subjectText) {
        subjectInput.clear();
        subjectInput.sendKeys(subjectText);
    }

    public void typeLetterText(String textMsg) {
        letterInput.clear();
        letterInput.sendKeys(textMsg);
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public void clickWriteButton() {
        writeButton.click();
    }

    public String getMessageText() { return newMessageText.getText(); }

    public void clickOnNewMessage() { newMessage.click(); }

    public void goToTrash() {
        moreButton.click();
        trashButton.click();
    }

    public void clickOnDeleteThisMessage() {
        moreButton2.click();
        deleteThisMessage.click();
    }
}
