package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;
import static org.openqa.selenium.support.PageFactory.initElements;

public class OrderPage extends BasePage{
    String URL = "http://automationpractice.com/index.php?controller=order";
    String EXPECTED_TITLE = "Order - My Store";

    @FindBy (css = "#cart_summary tbody tr")
    private List<WebElement> productsInCart;

    @FindBy (css = "#cart_summary tbody tr .cart_total .price")
    public List<WebElement> totalOfProduct;

    @FindBy (css = "#total_price_container")
    private WebElement totalField;

    @FindBy (css = "#total_shipping")
    private WebElement shippingField;

    @FindBy(css = "#center_column  a.button.btn.btn-default.standard-checkout.button-medium")
    private WebElement proceedToCheckout;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        super.URL = this.URL;
        super.EXPECTED_TITLE= this.EXPECTED_TITLE;
    }

    public OrderPage(WebDriver driver, boolean withPageFactory) {
        this.driver = driver;

        if(withPageFactory) {
            initElements(driver, this);
            super.URL = this.URL;
            super.EXPECTED_TITLE= this.EXPECTED_TITLE;
        }
    }

    public void proceedToCheckoutClick() {
        proceedToCheckout.click();
    }

    public float sumProductTotals() {
        float counter = 0;
        for (var productCost : totalOfProduct) {
            String costWith$ = productCost.getText();
            String costString = costWith$.substring(1);
            float cost = Float.parseFloat(costString);
            counter +=cost;
        }
        float shipping = Float.parseFloat(shippingField.getText().substring(1));
        return counter + shipping;
    }

    public void totalCheck() {
        String getSum = String.valueOf(sumProductTotals());
        String sum = "$" + getSum;

        if (sum.length() != totalField.getText().length()) {
            sum += "0";                 // дописываем "0", чтобы строка "$20.3" превратилась в "$20.30" (формат сайта)
        }

        Assert.assertEquals(sum, totalField.getText());
        System.out.printf
                ("Посчитано тестовым методом: %s \nПолучено Selenium из элемента \"total\": %s \n", sum, totalField.getText());
    }

    //класс вспомогательный тестовых методов
    /*public  class Instruments {

        public float sumProductTotals() {
            float counter = 0;
            for (var productCost : totalOfProduct) {
                String costWith$ = productCost.getText();
                String costString = costWith$.substring(1);
                float cost = Float.parseFloat(costString);
                counter +=cost;
            }
            return counter;
        }

    }*/


}
