package com.ecom.orderProcessService.model.test;

import org.junit.Assert;
import org.junit.Test;

import com.ecom.orderProcessService.model.AccountInfo;

public class AccountInfoTest {

	private AccountInfo accountTest;

	@Test
	public void testModel_AccountInfoTest() {
		accountTest = new AccountInfo();

		// Test AccountID
		accountTest.setAccountId("01");
		Assert.assertNotNull(accountTest.getAccountId());
		Assert.assertEquals("01", accountTest.getAccountId());

		// Test Billing Information
		accountTest.setBilling_info("riverside");
		Assert.assertNotNull(accountTest.getBilling_info());
		Assert.assertEquals("riverside", accountTest.getBilling_info());

		// Test Email ID
		accountTest.setEmail("she@gmail.com");
		Assert.assertNotNull(accountTest.getEmail());
		Assert.assertEquals("she@gmail.com", accountTest.getEmail());

		// Test FirstName
		accountTest.setFirst_name("sheela");
		Assert.assertNotNull(accountTest.getFirst_name());
		Assert.assertEquals("sheela", accountTest.getFirst_name());

		// Test LastName
		accountTest.setLast_name("acharya");
		Assert.assertNotNull(accountTest.getLast_name());
		Assert.assertEquals("acharya", accountTest.getLast_name());

		// Test Password
		accountTest.setPassword("root");
		Assert.assertNotNull(accountTest.getPassword());
		Assert.assertEquals("root", accountTest.getPassword());

		// Test Phone Number
		accountTest.setPhone("2123432");
		Assert.assertNotNull(accountTest.getPhone());
		Assert.assertEquals("2123432", accountTest.getPhone());

		// Test Shipping Information
		accountTest.setShipping_info("riverside");
		Assert.assertNotNull(accountTest.getShipping_info());
		Assert.assertEquals("riverside", accountTest.getShipping_info());

	}

}
