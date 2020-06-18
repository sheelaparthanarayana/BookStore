/**
 * 
 */
package com.ecom.productCatalog.response.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ecom.productCatalog.model.Book;
import com.ecom.productCatalog.response.ProductCatalogResponse;

public class ProductCatalogResponseTest {

	private ProductCatalogResponse pCatalogTest;
	private Book book;

	@Test
	public void testResponse_ProductCatalogResponse() {

		pCatalogTest = new ProductCatalogResponse();
		book = new Book();

		book.setAuthor("jk rowling");
		book.setBookid("01");
		book.setCategory("Computers");
		book.setPrice(100);
		book.setTitle("Harry Potter");

		List<Book> books = new ArrayList<Book>();
		books.add(book);
		pCatalogTest.setBooks(books);

		Book actualBook = pCatalogTest.getBooks().get(0);
		assertNotNull(pCatalogTest.getBooks());

		// Test Author
		assertNotNull(actualBook.getAuthor());
		Assert.assertEquals("jk rowling", actualBook.getAuthor());

		// Test BookID
		assertNotNull(actualBook.getBookid());
		Assert.assertEquals("01", actualBook.getBookid());

		// Test Category
		assertNotNull(actualBook.getCategory());
		Assert.assertEquals("Computers", actualBook.getCategory());

		// Test Price
		assertNotNull(actualBook.getPrice());
		Assert.assertEquals(100, actualBook.getPrice());

		// Test Title
		assertNotNull(actualBook.getTitle());
		Assert.assertEquals("Harry Potter", actualBook.getTitle());

	}
}
