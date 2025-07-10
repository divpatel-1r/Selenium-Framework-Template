package org.example.Login;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.example.Base.Base;
import org.example.Pages.Login.Login;
import org.example.Utility.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginValidate extends Base {

    Login login;

    @Test(priority = 1,dataProvider = "TestData")
    public void ValidLogin(String Username, String Password) {       // Valid Test Case for Login
        login = new Login(driver);
        login.LoginFunc(Username, Password);// This is Sample Data
        Assert.assertEquals(login.getLoginErrorMessage(), "You logged into a secure area!\n" +
                "×", "You are failed in logged in!");
    }

    //        @Test
//        public void InvalidLogin(){     // Invalid testcase for Login
//            log.LoginFunc("InvalidUser","InvalidPass");     // This is Sample Data
//    }
    @Test(priority = 2,dataProvider = "InvalidTestData")
    public void InvalidLogin(String Username, String Password) {
        login = new Login(driver);// Validate testcase for Login
        login.LoginFunc(Username, Password);     // This is Sample Data
    }


    @DataProvider(name = "TestData")   //DataProvider
    public Object[][] getData(){
        return ExcelUtils.getTestData("src/test/resources/Test.xlsx","Sheet1");   //Calling Excel method
    }
    @DataProvider(name = "InvalidTestData")   //DataProvider
    public Object[][] getInvalidData(){
        return ExcelUtils.getTestData("src/test/resources/Test.xlsx","Sheet2");   //Calling Excel method
    }
}
