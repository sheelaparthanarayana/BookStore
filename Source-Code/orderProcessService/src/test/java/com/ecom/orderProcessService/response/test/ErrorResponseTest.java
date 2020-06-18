package com.ecom.orderProcessService.response.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ecom.orderProcessService.response.ErrorResponse;

public class ErrorResponseTest {


	private ErrorResponse eResponseTest;
	@Test
	public void testModel_ErrorResponse() {
		eResponseTest = new ErrorResponse();
		
		//Test ErrorCode
		eResponseTest.setErrorCode(404);
		assertNotNull(eResponseTest.getErrorCode());
		assertEquals(404,eResponseTest.getErrorCode());
		
		//Test Error Message
		eResponseTest.setErrorMessage("File Not Found");
		assertNotNull(eResponseTest.getErrorMessage());
		assertEquals("File Not Found",eResponseTest.getErrorMessage());
	}


}
