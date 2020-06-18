package com.ecom.orderProcessService.request.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ecom.orderProcessService.request.UserInfo;

public class UserInfoTest {
	
	 UserInfo uInfotest;
	@Test
	public void testRequest_userInfoTest() {
		uInfotest = new UserInfo();
		
		//Test Username 
		uInfotest.setUsername("barun");
		assertNotNull(uInfotest.getUsername());
		assertEquals("barun",uInfotest.getUsername());
		
		//Test Password
		uInfotest.setPassword("welcome");
		assertNotNull(uInfotest.getPassword());
		assertEquals("welcome",uInfotest.getPassword());
	}

}

