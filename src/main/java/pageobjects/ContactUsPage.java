package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Properties;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utils.TestProperties.getProperty;

public class ContactUsPage extends BasePage{

    private final String URL = "http://automationpractice.com/index.php?controller=contact";
    private final String EXPECTED_TITLE = "Contact us - My Store";

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        super.URL = this.URL;
        super.EXPECTED_TITLE= this.EXPECTED_TITLE;

    }

    public ContactUsPage(WebDriver driver, boolean withPageFactory) {
        this.driver = driver;

        if(withPageFactory) {
            initElements(driver, this);
            super.URL = this.URL;
            super.EXPECTED_TITLE= this.EXPECTED_TITLE;
        }
    }

    @FindBy(css = "#fileUpload")
    private WebElement chooseFileButton;

    @FindBy(css = "#message")
    private WebElement messageField;

    @FindBy(css = "#submitMessage")
    private WebElement sendButton;

    public void addFile() {

        chooseFileButton.sendKeys("D:/utka.jpg");

    }

    public void addMessage() {
        messageField.sendKeys(getProperty("message"));
    }

    public void clickSendButton() {
        sendButton.click();
    }
}
