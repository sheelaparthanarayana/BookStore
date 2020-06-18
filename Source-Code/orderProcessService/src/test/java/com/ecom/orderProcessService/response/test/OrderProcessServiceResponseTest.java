/**
 * 
 */
package com.ecom.orderProcessService.response.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ecom.orderProcessService.response.OrderProcessServiceResponse;

public class OrderProcessServiceResponseTest {

	private OrderProcessServiceResponse oPServiceResponse;

	@Test
	public void testModel_orderProcessServiceResponse() {
		oPServiceResponse = new OrderProcessServiceResponse();

		// Test HTTPCode
		oPServiceResponse.setHttpCode(100);
		assertNotNull(oPServiceResponse.getHttpCode());
		assertEquals(100, oPServiceResponse.getHttpCode());

		// Test Status
		oPServiceResponse.setStatus("Processed");
		assertNotNull(oPServiceResponse.getStatus());
		assertEquals("Processed", oPServiceResponse.getStatus());

	}

}