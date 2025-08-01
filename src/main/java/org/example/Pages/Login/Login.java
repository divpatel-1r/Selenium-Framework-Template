package org.example.Pages.Login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    /*
    * Login Page Object Model (POM) class for handling login functionality.
    * This class contains WebElements for username, password, and login button,
    * along with methods to perform login actions and retrieve error messages.
     */
    WebDriver driver;

    @FindBy(id = "username")                            // This is Sample Data
    private WebElement username;

    @FindBy(id = "password")                            // This is Sample Data
    private WebElement password;

    @FindBy(xpath = "//button[@type = 'submit']")       // This is Sample Data
    private WebElement Login;

    //Asserts

    @FindBy(id = "flash")// This is Sample Data
    private WebElement loginError;// Element to capture login error messages


    public Login(WebDriver driver) {// Constructor to initialize the WebDriver and PageFactory
        this.driver = driver;// Set the WebDriver instance
        PageFactory.initElements(driver, this);// Initialize the WebElements using PageFactory
    }

    public void LoginFunc(String name, String pass) {// Method to perform login action
        username.sendKeys(name);// Enter the username
        password.sendKeys(pass);// Enter the password
        Login.click();// Click the login button
    }


    public String getLoginErrorMessage() {// Method to retrieve the login error message
        return loginError.getText();// Get the text of the login error message element
    }
}
