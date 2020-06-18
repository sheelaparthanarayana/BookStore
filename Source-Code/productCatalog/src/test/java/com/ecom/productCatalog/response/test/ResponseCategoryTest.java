/**
 * 
 */
package com.ecom.productCatalog.response.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import com.ecom.productCatalog.response.ResponseCategory;

public class ResponseCategoryTest {

	private ResponseCategory rCategoryTest;

	@org.junit.Test
	public void testResponse_ResponseCategory() {
		rCategoryTest = new ResponseCategory();

		List<String> asList = Arrays.asList("Computers", "Entertainment");
		List<String> asList1 = Arrays.asList("11", "19", "12", "13");
		rCategoryTest.setCategories(asList1);
		Assert.assertNotNull(rCategoryTest.getCategories());
		Iterator<String> iterator = asList1.iterator();
		for (String str1 : rCategoryTest.getCategories()) {
			Assert.assertEquals(iterator.next(), str1);

		}

	}

}
