package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.*;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;

public class SeleniumSimpleTest {

    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private OrderPage orderPage;
    private ContactUsPage contactUsPage;

    @BeforeMethod
    void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();

        homePage = initElements(driver, HomePage.class);
        searchPage = initElements(driver, SearchPage.class);
        productPage = initElements(driver, ProductPage.class);
        orderPage = initElements(driver, OrderPage.class);
        contactUsPage = initElements(driver, ContactUsPage.class);
    }

    @AfterMethod
    void afterMethod() {driver.close();}

    @Test
    void highCostOrderCheck() {
        homePage.go();
        homePage.putIntoSearch("Printed Dress");
        searchPage.chooseHighCostFirst();
        searchPage.checkOrderByCost(); //проверяем корректность через отсортированный Stream цен
    }

    @Test
    void buyItemCheck() {
        homePage.go(); //добавляем первый товар "Printed Chiffon Dress"
        homePage.putIntoSearch("Printed Chiffon Dress");
        searchPage.addProductByPosition(1);

        homePage.go(); //добавляем второй товар "Blouse"
        homePage.putIntoSearch("Blouse");
        searchPage.addProductByPosition(1);
        searchPage.proceedToCheckoutButtonClick();
        //productPage.clickProceedToCheckoutButton();

        orderPage.totalCheck(); //делаем проверку отображаемой суммы
    }


    //Тест с использованием .properties + отправка локального файла
    @Test
    void contactUsCheck() {
        homePage.go();
        homePage.contactUsButtonClick();
        contactUsPage.addFile();
        contactUsPage.addMessage();
        contactUsPage.clickSendButton();
    }

}
