/**
 * 
 */
package com.ecom.productCatalog.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ecom.productCatalog.controller.ProductCatalogController;
import com.ecom.productCatalog.exceptions.ErrorCode;
import com.ecom.productCatalog.exceptions.ProductCatalogException;
import com.ecom.productCatalog.model.Book;
import com.ecom.productCatalog.response.ProductCatalogResponse;
import com.ecom.productCatalog.service.ProductCatalogService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ProductCatalogController.class })
public class ProductCatalogControllerTest {
	private ProductCatalogController productCatalogController;

	@Mock
	private ProductCatalogService productCatalogService;

	@Test
	public void test_when_get_all_categroies_is_invoked() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("computer");
		arrayList.add("entertainment");
		PowerMockito.when(productCatalogService.getAllCategories()).thenReturn(arrayList);
		Response category = productCatalogController.getCategory();
		assertEquals(200, category.getStatus());
	}

	@Test
	public void test_when_get_all_categroies_is_invoked_throws_exception() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("computer");
		arrayList.add("entertainment");
		PowerMockito.when(productCatalogService.getAllCategories())
				.thenThrow(new ProductCatalogException(ErrorCode.BUSINESS_VALIDATION_ERROR));
		Response category = productCatalogController.getCategory();
		assertEquals(500, category.getStatus());
	}

	@Test
	public void test_when_get_product_info() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		Book book = new Book();
		book.setAuthor("sheela");
		List<Book> arrayList = new ArrayList<Book>();
		arrayList.add(book);
		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getProductInfo("productId")).thenReturn(productCatalogResponse);
		Response category = productCatalogController.getProductInfo("productId");
		assertEquals(200, category.getStatus());
	}

	@Test
	public void test_when_get_product_info_throws_exception() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		Book book = new Book();
		book.setAuthor("sheela");
		List<Book> arrayList = new ArrayList<Book>();
		arrayList.add(book);
		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getProductInfo("productId"))
				.thenThrow(new ProductCatalogException(ErrorCode.BUSINESS_VALIDATION_ERROR));
		Response category = productCatalogController.getProductInfo("productId");
		assertEquals(500, category.getStatus());
	}

	@Test
	public void test_when_get_product_info_not_found() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		List<Book> arrayList = new ArrayList<Book>();

		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getProductInfo("productId")).thenReturn(productCatalogResponse);
		Response category = productCatalogController.getProductInfo("productId");
		assertEquals(404, category.getStatus());
	}

	@Test
	public void test_when_get_all_products_is_invoked() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		Book book = new Book();
		book.setAuthor("sheela");
		List<Book> arrayList = new ArrayList<Book>();
		arrayList.add(book);
		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getAllProducts("productId")).thenReturn(productCatalogResponse);
		Response category = productCatalogController.getProduct("productId");
		assertEquals(200, category.getStatus());
	}

	@Test
	public void test_when_get_all_products_is_invoked_throws_exception() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		Book book = new Book();
		book.setAuthor("sheela");
		List<Book> arrayList = new ArrayList<Book>();
		arrayList.add(book);
		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getAllProducts("productId"))
				.thenThrow(new ProductCatalogException(ErrorCode.BUSINESS_VALIDATION_ERROR));
		Response category = productCatalogController.getProduct("productId");
		assertEquals(500, category.getStatus());
	}

	@Test
	public void test_when_get_all_products_is_invoked_not_found() throws Exception {
		PowerMockito.whenNew(ProductCatalogService.class).withNoArguments().thenReturn(productCatalogService);
		productCatalogController = new ProductCatalogController();
		ProductCatalogResponse productCatalogResponse = new ProductCatalogResponse();
		List<Book> arrayList = new ArrayList<Book>();
		productCatalogResponse.setBooks(arrayList);
		PowerMockito.when(productCatalogService.getAllProducts("productId")).thenReturn(productCatalogResponse);
		Response category = productCatalogController.getProduct("productId");
		assertEquals(404, category.getStatus());
	}

}
