package org.GIT.tests.login;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import org.GIT.Utils.*;
import org.GIT.responsePOJO.StatusResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.GIT.base.APIHelper;
import com.GIT.base.BaseTest;



public class ValidateLoginAPI_Functionality extends BaseTest{
	ExtentReportsUtility report=ExtentReportsUtility.getInstance();
    APIHelper apiHelper;

    @BeforeClass
    public void beforeClass() {
        apiHelper = new APIHelper();
    }

    @Test(priority = 0, description = "validate login functionality with valid credentials")
    public void validateLoginWithValidCredentials() {
        Response login = apiHelper.login(EnvironmentDetails.getProperty("usertoken"));
    //    Response login = apiHelper.login(EnvironmentDetails.getProperty("username"), EnvironmentDetails.getProperty("password"));
        Assert.assertEquals(login.getStatusCode(), HttpStatus.SC_OK,"error occured with login");
        report.logTestInfo("successfull login with statuscode 200");
    //    JsonSchemaValidate.validateSchemaInClassPath(login, "ExpectedJsonSchema/LoginResponseSchema.json");
    //    report.logTestInfo("LoginResponse is validated against expected schema successfully");
         
    }

    

  /*  @Test(priority = 1, description = "validate login functionality with invalid credentials")
    public void validateLoginWithInValidCredentials() {
        Response login = apiHelper.login(EnvironmentDetails.getProperty("usertoken"));
        Assert.assertEquals(login.getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Login is not returning proper status code with invalid credentials.");
        StatusResponse statusResponse = login.as(StatusResponse.class);
        Assert.assertEquals(statusResponse.getStatus(), TestDataUtils.getProperty("invalidCredentialsMessage"), "Status message is not returning as expected");
    }   */


}
