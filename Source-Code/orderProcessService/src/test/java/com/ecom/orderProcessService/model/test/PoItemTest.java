package com.ecom.orderProcessService.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ecom.orderProcessService.model.Book;
import com.ecom.orderProcessService.model.POItem;
import com.ecom.orderProcessService.model.PurchaseOrder;

public class PoItemTest {
	private POItem poItest;

	@Test
	public void testModel_POItem() {
		poItest = new POItem();
		
		Book book = new Book();
		book.setBookid("1234");
		poItest.setBookid(book);
		
		PurchaseOrder pOrder = new PurchaseOrder();
		pOrder.setPurchase_id("007");
		poItest.setPurchase_id(pOrder);

		//Test BookId
		String bookid = poItest.getBook().getBookid();
		assertNotNull(bookid);
		assertEquals("1234", bookid);
		
		//Test Purchase Id
		String purchaseId = poItest.getPurchase_id().getPurchase_id();
		assertNotNull(purchaseId);
		assertEquals("007",purchaseId);
		
		poItest.setId("1");
		assertNotNull(poItest.getId());
		assertEquals("1",poItest.getId());
		
	}
}
