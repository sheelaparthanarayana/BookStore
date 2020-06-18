/**
 * 
 */
package com.ecom.productCatalogServiceTest.stepsdefinition;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.Response;

import com.ecom.productCatalog.util.Util;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
public class ServiceStepDefinition extends ScenarioSteps {
	private Response response;

	@Step
	public void checkService() {

	}

	@Step
	public void invokedWith(String URL) throws KeyManagementException, NoSuchAlgorithmException {
		response = Util.doGet(URL);

	}

	@Step
	public Response responseOfTheService() {
		return response;
	}

	@Step
	public void invokedWithOrder(String URL, String entity) throws KeyManagementException, NoSuchAlgorithmException {
		response = Util.doPost(URL, entity);

	}
	
}
