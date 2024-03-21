package org.GIT.tests.CRUD;

import com.GIT.base.APIHelper;
import com.GIT.base.BaseTest;
import com.github.javafaker.Faker;



import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import org.GIT.Utils.*;
import org.GIT.requestPOJO.AddDataRequest;
import org.GIT.requestPOJO.DeleteDataRequest;
import org.GIT.requestPOJO.UpdateDataRequest;
import org.GIT.responsePOJO.AddDataResponse;
import org.GIT.responsePOJO.GetDataResponse;
import org.GIT.responsePOJO.LoginResponse;
import org.GIT.responsePOJO.StatusResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ValidateAddAndDeleteData_Functionality extends BaseTest {
    APIHelper apiHelper;
    String did, dnode_id, dname, dfull_name, dprivate1;
  //  private Faker faker;
    String dataId = "";

    @BeforeClass
    public void beforeClass() {
    //    faker = new Faker();
        apiHelper = new APIHelper();
        Response login = apiHelper.login(EnvironmentDetails.getProperty("usertoken"));
   //     Response login = apiHelper.login(EnvironmentDetails.getProperty("username"), EnvironmentDetails.getProperty("password"));
    //    userId = login.getBody().as(new TypeRef<List<LoginResponse>>() {}).get(0).getUserid();
    }

  /*  @Test(priority = 0, description = "validate add data functionality")
    public void validateAddDataFunctionality() {
        accountNo = "TA-" + faker.number().numberBetween(10000, 20000);
        departmentNo = "5";
        salary = faker.number().numberBetween(15000, 85000) + "";
        pincode = faker.address().zipCode();
        AddDataRequest addDataRequest = AddDataRequest.builder().accountNo(accountNo).departmentNo(departmentNo).salary(salary).pinCode(pincode).build();
        Response response = apiHelper.addData(addDataRequest);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Add data functionality is not working as expected.");
        Assert.assertEquals(response.as(AddDataResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
        JsonSchemaValidate.validateSchema(response.asPrettyString(), "StatusResponseSchema.json");

    }   */

    @Test(priority = 0, description = "validate single repo data in the get data object") //, dependsOnMethods = "validateAddDataFunctionality")
    public void validateLoginRepoGetData() {
        Response data = apiHelper.getData(EnvironmentDetails.getProperty("usertoken"));
  //           List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
  //      });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
        
        String actualResponse = data.jsonPath().prettyPrint();
        
   /*     GetDataResponse getDataResponse = null;
        try {
            getDataResponse = returnTheMatchingGetDataResponse(dfull_name, getDataResponseList);
        } catch (NullPointerException e) {
            Assert.fail("Added data is not available in the get data response");   
        }
        did = getDataResponse.getId();
        dnode_id = getDataResponse.getNode_id();
        dname = getDataResponse.getName();
        dfull_name = getDataResponse.getFull_name();
        dprivate1 = getDataResponse.getPrivate1();    */
       
    //    Assert.assertEquals(getDataResponse.getDepartmentNo(), departmentNo, "Add data functionality is not working as expected, Department number is not matching");
    //    Assert.assertEquals(getDataResponse.getSalary(), salary, "Add data functionality is not working as expected, Salary is not matching");
    //    Assert.assertEquals(getDataResponse.getPinCode(), pincode, "Add data functionality is not working as expected, Pincode is not matching");
    }
    
    
    @Test(priority = 1, description = "validate single repo data in the get data object") //, dependsOnMethods = "validateAddDataFunctionality")
    public void validateSingleRepoGetData() {
        Response data = apiHelper.getData(EnvironmentDetails.getProperty("reponame"));  
        List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
        GetDataResponse getDataResponse = null;
        try {
            getDataResponse = returnTheMatchingGetDataResponse(dfull_name, getDataResponseList);
        } catch (NullPointerException e) {
            Assert.fail("Added data is not available in the get data response");
        }
    }
    
    @Test(priority = 2, description = "validate single repo data in the get data object") //, dependsOnMethods = "validateAddDataFunctionality")
    public void validateNonExistingSingleRepoGetData() {
        Response data = apiHelper.getData(EnvironmentDetails.getProperty("nonexistentrepo"));  
        List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Response code is not matching for get data.");
        GetDataResponse getDataResponse = null;
        try {
            getDataResponse = returnTheMatchingGetDataResponse(dfull_name, getDataResponseList);
        } catch (NullPointerException e) {
            Assert.fail("Added data is not available in the get data response");
        }
    }
    
    
    @Test(priority = 3, description = "validate all repo data in the get data object") //, dependsOnMethods = "validateAddDataFunctionality")
    public void validateAllRepoGetData() {
        Response data = apiHelper.getData(EnvironmentDetails.getProperty("allrepo"));  
        List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
        GetDataResponse getDataResponse = null;
        try {
            getDataResponse = returnTheMatchingGetDataResponse(dfull_name, getDataResponseList);
        } catch (NullPointerException e) {
            Assert.fail("Added data is not available in the get data response");
        }
    }
    
  /*  @Test(priority = 2, description = "validate update data functionality")
    public void validateUpdateDataFunctionality() {
        id = "TA-" + faker.number().numberBetween(10000, 20000);
        departmentNo = "10";
        salary = faker.number().numberBetween(15000, 85000) + "";
        pincode = faker.address().zipCode();
        UpdateDataRequest updateDataRequest = UpdateDataRequest.builder().accountNo(id).departmentNo(departmentNo).salary(salary).pinCode(pincode).build();
        Response response = apiHelper.putData(updateDataRequest);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Add data functionality is not working as expected.");
        Assert.assertEquals(response.as(AddDataResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
        JsonSchemaValidate.validateSchema(response.asPrettyString(), "StatusResponseSchema.json");

    }
    
    @Test(priority = 1, description = "validate added data in the get data object", dependsOnMethods = "validateAddDataFunctionality")
    public void validateUpdateDataInGetData() {
        Response data = apiHelper.getData();
        List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
        GetDataResponse getDataResponse = null;
        try {
            getDataResponse = returnTheMatchingGetDataResponse(accountNo, userId, getDataResponseList);
        } catch (NullPointerException e) {
            Assert.fail("Added data is not available in the get data response");
        }
        dataId = getDataResponse.getId();
        Assert.assertEquals(getDataResponse.getDepartmentNo(), departmentNo, "Add data functionality is not working as expected, Department number is not matching");
        Assert.assertEquals(getDataResponse.getSalary(), salary, "Add data functionality is not working as expected, Salary is not matching");
        Assert.assertEquals(getDataResponse.getPinCode(), pincode, "Add data functionality is not working as expected, Pincode is not matching");
    }   

    @Test(priority = 3, description = "delete data functionality", dependsOnMethods = "validateAddDataFunctionality")
    public void validateDeleteData() {
        DeleteDataRequest deleteDataRequest = DeleteDataRequest.builder().userId(userId).id(dataId).build();
        Response data = apiHelper.deleteData(deleteDataRequest);
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Delete data functionality is not working as expected.");
        Assert.assertEquals(data.as(StatusResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
        String actualResponse = data.jsonPath().prettyPrint();
        JsonSchemaValidate.validateSchema(actualResponse, "StatusResponseSchema.json");
    }

    @Test(priority = 4, description = "validate deleted data in the get data object", dependsOnMethods = "validateDeleteData")
    public void validateDeletedDataInGetData() {
        Response data = apiHelper.getData();
        List<GetDataResponse> getDataResponseList = data.getBody().as(new TypeRef<List<GetDataResponse>>() {
        });
        Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Response code is not matching for get data.");
        if (returnTheMatchingGetDataResponse(id, userId, getDataResponseList) != null) {
            Assert.fail("Deleted data is still available in the get data response");
        }
    }    */
//id, node_id,name,full_name,private1
    public GetDataResponse returnTheMatchingGetDataResponse(String full_name, List<GetDataResponse> getDataResponseList) {
        for (GetDataResponse dataResponse : getDataResponseList) {
            if (dataResponse.getFull_name().equals(full_name))
                return dataResponse;
        }
        return null;
    }

}
