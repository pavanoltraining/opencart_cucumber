package stepDefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;


public class RegistrationSteps {

	 WebDriver driver;
     HomePage hp;
     LoginPage lp;
     AccountRegistrationPage regpage;
     
	@Given("the user navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
	
		hp=new HomePage(BaseClass.getDriver());
    	hp.clickMyAccount();
        hp.clickRegister();
                   
	}

	@When("the user enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable) {
		
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
	    
		regpage=new AccountRegistrationPage(BaseClass.getDriver());
		regpage.setFirstName(dataMap.get("firstName"));
		regpage.setLastName(dataMap.get("lastName"));
		regpage.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		regpage.setTelephone(dataMap.get("telephone"));
		regpage.setPassword(dataMap.get("password"));
		regpage.setConfirmPassword(dataMap.get("password"));
		
	}

	@When("the user selects Privacy Policy")
	public void user_selects_privacy_policy() {
		regpage.setPrivacyPolicy();
	}

	@When("the user clicks on Continue button")
	public void user_clicks_on_continue_button() {
		regpage.clickContinue();
	}

	@Then("the user account should get created successfully")
	public void user_account_should_get_created_successfully() {
		
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
 }
