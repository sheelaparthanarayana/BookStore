/**
 * 
 */
package com.ecom.productCatalog.model.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;

import com.ecom.productCatalog.model.Book;

public class BookTest {

	private Book bookInfoTest;
	@org.junit.Test
	public void testModel_Book() {
		bookInfoTest = new Book();
		
		//Test Author
		bookInfoTest.setAuthor("JK Rowling");
		Assert.assertNotNull(bookInfoTest.getAuthor());
		assertEquals("JK Rowling",bookInfoTest.getAuthor());
		
		//Test BookId
		bookInfoTest.setBookid("01");
		assertNotNull(bookInfoTest.getBookid());
		assertEquals("01",bookInfoTest.getBookid());
		
		//Test Category
		bookInfoTest.setCategory("Computers");
		assertNotNull(bookInfoTest.getCategory());
		assertEquals("Computers",bookInfoTest.getCategory());
		
		//Test Price
		bookInfoTest.setPrice(101);
		assertNotNull(bookInfoTest.getPrice());
		assertEquals(101,bookInfoTest.getPrice());
		
		//Test Title
		bookInfoTest.setTitle("Harry Potter");
		assertNotNull(bookInfoTest.getTitle());
		assertEquals("Harry Potter",bookInfoTest.getTitle());
		
	}

}

