package cucumber;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//print the results in readable format , hence monochrome=true
//junut is inbuilt in cucumber.By default, cucumber cannot scan testng assertions
//For testNG, abstarcttestng parent class should be extended for seamless integration

@CucumberOptions( 
		//dryRun=true,
		features ="src\\test\\java\\cucumber", 
		glue ="stepdefinitions",
        monochrome =true ,
        tags = "@Regression",
        plugin= {"html:target/cucumber.html"}
		)

@Test
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
	
	
}
