package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.support.PageFactory.initElements;

public class SearchPage extends BasePage{

    @FindBy(css = "#ssd_th_eart > button")
    private WebElement addToCartButton;

    @FindBy(css = "#center_column > ul > li")
    private List<WebElement> foundProducts;

    @FindBy(css = ".button-container > a")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = "#selectProductSort")
    private WebElement sortByButton;

    @FindBy(css = " .right-block .product-price")
    private WebElement costField;


    public SearchPage(WebDriver driver) {this.driver = driver; }

    public SearchPage(WebDriver driver, boolean withPageFactory) {
        this.driver = driver;

        if(withPageFactory) {
            initElements(driver, this);
        }
    }

    public void clickOnProductByPosition (int position) {
        foundProducts.get(position - 1).click();
    }

    public void addProductByPosition(int position) {
        String num = Integer.toString(position);
        WebElement addToCart = driver.findElement
                (By.cssSelector("li:nth-child(" + num + ") a.button:nth-child(1) > span"));
        WebElement inStock = driver.findElement
                (By.cssSelector("#center_column > ul > li:nth-child(" + num + ") > div > div.right-block > span > span"));
        inStock.click();
        addToCart.click();
    }

    public void proceedToCheckoutButtonClick() {
        proceedToCheckoutButton.click();
    }

    public void chooseHighCostFirst() {
        sortByButton.click();
        WebElement highFirst = driver.findElement(By.cssSelector("#selectProductSort > option:nth-child(3)"));
        highFirst.click();
    }

    public void checkOrderByCost() {
        List<WebElement> result = driver.findElements
                (By.cssSelector("#center_column .right-block div.content_price")); //"#center_column .right-block div.content_price > span"

        List<String> resultCosts = new ArrayList<>();
        for (var webElement : result) {
            boolean isOldPrice;
            try {
                driver.findElement(By.cssSelector(".old-price.product-price"));
                isOldPrice = true;
            } catch (NoSuchElementException e) {
                isOldPrice = false;
            }

            if (isOldPrice) {
                resultCosts.add(driver.findElement(By.cssSelector(".old-price.product-price")).getText());
            } else {
                resultCosts.add(driver.findElement(By.cssSelector(".price.product-price")).getText());
            }
        }

        var resultCostsStream = resultCosts.stream()
                .sorted(Collections.reverseOrder()) // сортировка String: от большего к меньшему (reverse)
                .collect(Collectors.toList());

        Assert.assertEquals(resultCosts, resultCostsStream);
    }

}
