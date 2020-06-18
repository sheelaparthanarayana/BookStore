/**
 * 
 */
package com.ecom.productCatalogServiceTest.steps;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.ecom.productCatalogServiceTest.stepsdefinition.ServiceStepDefinition;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
public class ServiceScenarioSteps {

	@Steps
	private ServiceStepDefinition serviceStepDefinition;

	@Given("product service is running")
	public void givenProductServiceIsRunning() {
		System.out.println("Assumption : product service is running");
	}

	@Given("order service is running")
	public void givenOrderServiceIsRunning() {
		System.out.println("Assumption : order service is running");
	}

	@When("the service api <providedUrl> is invoked")
	public void whenServiceIsInvoked(String providedUrl) throws KeyManagementException, NoSuchAlgorithmException {
		serviceStepDefinition.invokedWith(providedUrl);
	}

	@Then("check the statusCode <statusCode> and the response <response>")
	public void thenCheckTheResponse(int statusCode, String response) {
		Response responseOfTheService = serviceStepDefinition.responseOfTheService();
		Assert.assertEquals(statusCode, responseOfTheService.getStatus());
		Assert.assertEquals(response, responseOfTheService.readEntity(String.class));

	}

	@When("the service api <providedUrl> is invoked for order service with entity <entity>")
	public void whenTheServiceApiprovidedUrlIsInvokedForOrderServiceWithEntityentity(String providedUrl,String entity ) throws KeyManagementException, NoSuchAlgorithmException {
		entity =entity.replace("email1", UUID.randomUUID().toString()).toString();
		System.out.println("^%&&%&"+entity);
		serviceStepDefinition.invokedWithOrder(providedUrl, entity);
	}

	@Then("check the statusCode <statusCode> match response <response>")
	public void thenOrderCheckTheResponse(int statusCode, String response) {
		Response responseOfTheService = serviceStepDefinition.responseOfTheService();
		Assert.assertEquals(statusCode, responseOfTheService.getStatus());

	}

}
