package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ProductPage {
    WebDriver driver;
    public By cartItemCount = By.id("nav-cart-count");
    By addToCartButton = By.id("add-to-cart-button");
    By proceedToCheckout = By.cssSelector("input[value='Proceed to checkout']");
    By getProceedToCheckoutSlidingWindow = By.id("attach-accessory-proceed-to-checkout-text");
    By productName = By.id("productTitle");

    public ProductPage(WebDriver driver){

        this.driver = driver;
    }
    public String getItemName(){
        return driver.findElement(productName).getText();
    }
    public String getCartSize(){
        return driver.findElement(cartItemCount).getText();
    }
    public void clickAddToCart(){
        driver.findElement(addToCartButton).click();
    }
    public void clickProceedToBuy(){
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        try {
            driver.findElement(proceedToCheckout);
            driver.findElement(proceedToCheckout).click();

        }catch (NoSuchElementException e) {
            driver.findElement(getProceedToCheckoutSlidingWindow).click();
        }
    }

}