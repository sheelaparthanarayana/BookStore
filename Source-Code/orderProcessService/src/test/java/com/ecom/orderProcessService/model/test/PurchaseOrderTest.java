package com.ecom.orderProcessService.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.ecom.orderProcessService.model.AccountInfo;
import com.ecom.orderProcessService.model.PurchaseOrder;

public class PurchaseOrderTest {

private PurchaseOrder pOrderTest;
	
	@Test
	public void testModel_PurchaseOrder() {
		pOrderTest = new PurchaseOrder();
		
		AccountInfo account = new AccountInfo();
		account.setAccountId("001");
		pOrderTest.setAccount(account);
		
		//Test account id
		String accountId = pOrderTest.getAccount().getAccountId();
		assertNotNull(accountId);
		assertEquals("001",accountId);	
		
		//Test Purchase Id
		pOrderTest.setPurchase_id("007");
		assertNotNull(pOrderTest.getPurchase_id());
		assertEquals("007",pOrderTest.getPurchase_id());
		
		//Test Shipping Information
		pOrderTest.setShipping_info("Riverside");
		assertNotNull(pOrderTest.getShipping_info());
		assertEquals("Riverside",pOrderTest.getShipping_info());
		
		//Test Status
		pOrderTest.setStatus("Processing");
		assertNotNull(pOrderTest.getStatus());
		assertEquals("Processing",pOrderTest.getStatus());
		
		//Test Total Price
		pOrderTest.setTotal_price(100);
		assertNotNull(pOrderTest.getTotal_price());
		assertEquals(100,pOrderTest.getTotal_price());
	}

}
