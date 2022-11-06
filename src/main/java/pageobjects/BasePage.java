package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class BasePage {

    protected WebDriver driver;

    protected String URL = "";
    protected String EXPECTED_TITLE = "";

    @FindBy(css = "#searchbox > button")
    protected WebElement loopButton;

    @FindBy(css = "#search_query_top")
    protected WebElement searchField;

    @FindBy(css = "#contact-link")
    protected WebElement contactUsButton;

    //метод для проверки перехода на нужную страницу (по заголовку)
    public void checkTitle() {
        var actualTitle = driver.getTitle();
        assertEquals(actualTitle, EXPECTED_TITLE);
    }

    public void go() {
        driver.get(URL);
        checkTitle();
    }

    public void putIntoSearch(String searchValue) {
        searchField.sendKeys(searchValue);
        loopButton.click();
    }

    public void contactUsButtonClick() {
        contactUsButton.click();
    }


}
