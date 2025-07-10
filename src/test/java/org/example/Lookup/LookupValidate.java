package org.example.Lookup;

import org.example.Base.Base;
import org.example.Pages.Lookup.Lookup;
import org.example.Utility.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LookupValidate extends Base {
    Lookup lookup;

    @Test(priority = 1,dataProvider = "TestData")       //Validate valid lookup
    public void ValidLookup(String Search){
        lookup = new Lookup(driver);
        lookup.LookupFunc(Search);
    }

    @Test       //Validate Empty field
    public void EmptyLookup(){
        lookup.LookupFunc("");
    }

    @Test       //Validate lookups
    public void ValidateLookup(){
        lookup.LookupFunc("Validate lookup");
    }

    @Test       //Navigate to Advance Search
    public void NavigateAdvanceLookup(){
        lookup.AdvanceSearchNavi();
    }


    @DataProvider(name = "TestData")   //DataProvider
    public Object[][] getData(){
        return ExcelUtils.getTestData("src/test/resources/Test.xlsx","Sheet1");   //Calling Excel method
    }
}
