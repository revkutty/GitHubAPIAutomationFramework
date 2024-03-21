package com.GIT.base;

import org.GIT.Utils.EnvironmentDetails;
import org.GIT.Utils.TestDataUtils;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void beforeSuite() {
        EnvironmentDetails.loadProperties();
        TestDataUtils.loadProperties();
    }
}
