package com.ecom.orderProcessService.request.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ecom.orderProcessService.request.OrderRequest;


public class OrderRequestTest {

	private OrderRequest oRequestTest;

	@Test
	public void testRequest_OrderRequestTest() {
		oRequestTest = new OrderRequest();
		
		//Test Account ID
		oRequestTest.setAccountId("001");
		assertNotNull(oRequestTest.getAccountId());
		assertEquals("001",oRequestTest.getAccountId());
		
		//Test Shipping Info
		oRequestTest.setShipping_info("Riverside"); 
		assertNotNull(oRequestTest.getShipping_info());
		assertEquals("Riverside",oRequestTest.getShipping_info());
		
		//Test Status
		oRequestTest.setStatus("Processed"); 
		assertNotNull(oRequestTest.getStatus());
		assertEquals("Processed",oRequestTest.getStatus());
		
		//Test Total Price
		oRequestTest.setTotal_price(100); 
		assertNotNull(oRequestTest.getTotal_price());
		assertEquals(100,oRequestTest.getTotal_price());
	
		//Test BookIds
		List<String> asList = Arrays.asList("11","19", "12", "13");
		oRequestTest.setBookIds(asList);
		Assert.assertNotNull(oRequestTest.getBookIds());
		Iterator<String> iterator = asList.iterator();
		for (String str : oRequestTest.getBookIds()) {
			System.out.println(str);
			Assert.assertEquals(iterator.next(), str);

		}

	}

}
