package com.zhacky.app.recime;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/", glue = {"com.zhacky.app.recime"})
public class CucumberIntegrationTest {
}
