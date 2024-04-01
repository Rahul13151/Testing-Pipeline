package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By searchBar = By.id("twotabsearchtextbox");
    By searchButton = By.id("nav-search-submit-text");
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    public String getTitle(){
        return driver.getTitle();
    }
    public void clickOnSearchBar(){
        driver.findElement(searchBar).click();
    }
    public void enterSearchItem(String item){
        driver.findElement(searchBar).sendKeys(item);
    }
    public void clickOnSearchButton(){
        driver.findElement(searchButton).click();
    }
}
