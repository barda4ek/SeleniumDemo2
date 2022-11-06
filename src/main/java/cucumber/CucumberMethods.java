package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import pageobjects.OrderPage;

public class CucumberMethods extends CucumberRunner{
    @Given("I am on the Home Page")
    public void iAmOnTheHomePage(){
        new HomePage(driver, true).go();
    }

    @When("^I fill search field with ([^\"]*)$")
    public void iFillSearchFieldWith(String query) {
        new HomePage(driver, true).fillSearchField(query);
    }

    @And("I click on loop button")
    public void iClickOnLoopButton() {
        new HomePage(driver, true).clickLoopButton();
    }

    @And("^I click the product with index ([^\"]*)$")
    public void iClickOnTheProductWithIndex(int index) {
        new HomePage(driver, true).clickLoopButton();
    }

    @And("I click the add to cart button")
    public void iClickAddToCartButton() {
        new HomePage(driver, true).clickLoopButton();
    }

    @And("I click Proceed to checkout button")
    public void iClickProceedToCheckoutButton() {
        new OrderPage(driver, true).proceedToCheckoutClick();
    }

    @Then("^The product with the name ([^\"]*) is in the cart$")
    public void  checkProductInCart(String productName){
        new HomePage(driver, true).go();
    }
}
