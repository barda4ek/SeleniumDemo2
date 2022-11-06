package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class ProductPage extends BasePage{

    @FindBy(css = "#add_to_cart > button")
    private WebElement addToCartButton;

    @FindBy(css = ".title=\"Proceed to checkout\"")
    private WebElement proceedToCheckoutButton;

    public ProductPage(WebDriver driver) {this.driver = driver; }

    public ProductPage(WebDriver driver, boolean withPageFactory) {

        this.driver = driver;
        if(withPageFactory) {
            initElements(driver, this);
        }

    }

    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }
    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    @Override
    public void checkTitle() {
        //do nothing, because title is unique for every product
    }

}
