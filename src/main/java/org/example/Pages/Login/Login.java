package org.example.Pages.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;

    @FindBy(id = "username")                            // This is Sample Data
    private WebElement username;

    @FindBy(id = "password")                            // This is Sample Data
    private WebElement password;

    @FindBy(xpath = "//button[@type = 'submit']")       // This is Sample Data
    private WebElement Login;

    //Asserts

    @FindBy(id = "flash")
    private WebElement loginError;


    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LoginFunc(String name, String pass) {
        username.sendKeys(name);
        password.sendKeys(pass);
        Login.click();
    }


    public String getLoginErrorMessage() {
        return loginError.getText();
    }
}
