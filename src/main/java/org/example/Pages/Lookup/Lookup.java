package org.example.Pages.Lookup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lookup {

    WebDriver driver;

    @FindBy(id = "Search")                      // This is Sample Data
    private WebElement LookupSearchbar;

    @FindBy(id = "SearchButton")                // This is Sample Data
    private WebElement LookupSearchButton;

    @FindBy(id = "AdvanceSearchButton")         // This is Sample Data
    private WebElement AdvanceSearchButton;

    public Lookup(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void LookupFunc(String Search){
        LookupSearchbar.sendKeys(Search);
        LookupSearchButton.click();
    }
    public void AdvanceSearchNavi(){
        AdvanceSearchButton.click();
    }
}
