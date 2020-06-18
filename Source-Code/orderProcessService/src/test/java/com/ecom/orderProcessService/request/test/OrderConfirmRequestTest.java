package com.ecom.orderProcessService.request.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.ecom.orderProcessService.request.OrderConfirmRequest;

public class OrderConfirmRequestTest {

	private OrderConfirmRequest oConfirmRequest;
	
	@Test
	public void testRequest_orderConfirmRequestTest() {
		oConfirmRequest = new OrderConfirmRequest();
		
		//Test Purchase ID
		oConfirmRequest.setPurchase_id("1");
		assertNotNull(oConfirmRequest.getPurchase_id());		
		assertEquals("1",oConfirmRequest.getPurchase_id());
		
		//Test Status
		oConfirmRequest.setStatus("Ordered");
		assertNotNull(oConfirmRequest.getStatus());		
		assertEquals("Ordered",oConfirmRequest.getStatus());
	}

}
