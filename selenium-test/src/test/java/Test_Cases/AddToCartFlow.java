package Test_Cases;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;

public class AddToCartFlow {
    WebDriver driver;

    HomePage objectHomePage;
    ProductListPage objectProductListPage;
    ProductPage objectProductPage;
    SignUpPage objectSignupPage;
    @BeforeTest
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
    }
    @Test(priority = 1)
    public void checkSiteLaunched(){
        objectHomePage = new HomePage(driver);
        String actualTitle = objectHomePage.getTitle();
        String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @Test(priority = 2)
    public void checkPageOnSearch() {
        objectHomePage = new HomePage(driver);
        objectHomePage.clickOnSearchBar();
        objectHomePage.enterSearchItem("boat headphone");
        objectHomePage.clickOnSearchButton();
        objectProductListPage = new ProductListPage(driver);
        String actualTitle = objectProductListPage.getTitle();
        String expectedTitle = "Amazon.in : boat headphone";
        Assert.assertEquals(actualTitle,expectedTitle);

    }
    @Test(priority = 3)
    public void checkOnClickColorPattern(){
        objectProductListPage = new ProductListPage(driver);
        String itemNameOnProductListPage = objectProductListPage.getFirstItemName();
        if(objectProductListPage.isColorButtonPresent()){
            objectProductListPage.clickColorPatterButton();
        }
        else{
            objectProductListPage.clickOnFirstItem();
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
            if (!availableWindows.isEmpty()) {
                driver.switchTo().window(availableWindows.get(1));
            }
        }

        objectProductPage = new ProductPage(driver);
        String itemNameOnProductPage = objectProductPage.getItemName();
        Assert.assertTrue(itemNameOnProductPage.contains(itemNameOnProductListPage));
    }
    @Test(priority=4)
    public void checkAddedToCart(){

        objectProductPage = new ProductPage(driver);
        String cartSize = objectProductPage.getCartSize();
        int initialCartSize=Integer.parseInt(cartSize);

        objectProductPage.clickAddToCart();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        String expectedStringValue= Integer.toString(initialCartSize+1);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(objectProductPage.cartItemCount,expectedStringValue));
        cartSize=objectProductPage.getCartSize();
        int finalCartSize=Integer.parseInt(cartSize);

        Assert.assertEquals(initialCartSize+1,finalCartSize);
    }
    @Test(priority=5)
    public void checkProceedToBuy(){
        objectProductPage = new ProductPage(driver);
        objectProductPage.clickProceedToBuy();
        objectSignupPage = new SignUpPage(driver);
        String actualTitle = objectSignupPage.getTitle();
        String expectedTitle = "Amazon Sign In";
        Assert.assertEquals(actualTitle,expectedTitle);

    }
    @AfterTest
    public void tearDown(){
        driver.quit();
        System.out.println("Test TearDown Succesfully");
    }

}
