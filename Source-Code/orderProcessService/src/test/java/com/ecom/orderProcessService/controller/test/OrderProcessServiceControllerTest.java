/**
 * 
 */
package com.ecom.orderProcessService.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ecom.orderProcessService.controller.OrderProcessServiceController;
import com.ecom.orderProcessService.exceptions.ErrorCode;
import com.ecom.orderProcessService.exceptions.OrderProcessServiceException;
import com.ecom.orderProcessService.model.AccountInfo;
import com.ecom.orderProcessService.request.OrderRequest;
import com.ecom.orderProcessService.request.UserInfo;
import com.ecom.orderProcessService.response.OrderPlaceResponse;
import com.ecom.orderProcessService.service.OrderProcessService;
import com.ecom.orderProcessService.testconstant.TestConstants;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ OrderProcessServiceController.class })
public class OrderProcessServiceControllerTest {

	private OrderProcessServiceController orderProcessServiceController;

	@Mock
	private OrderProcessService orderService;
	@Mock
	private AccountInfo account;

	@Test
	public void test_when_new_account_is_created() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		PowerMockito.when(orderService.isNewAccount(any(AccountInfo.class))).thenReturn(true);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.createAccount(TestConstants.USER_INFO);
		assertEquals(200, createAccount.getStatus());
		assertEquals(TestConstants.USER_INFO_EXPECTED, createAccount.getEntity());

	}

	@Test
	public void test_when_account_is_created_already() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		PowerMockito.when(orderService.isNewAccount(any(AccountInfo.class))).thenReturn(false);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.createAccount(TestConstants.USER_INFO);
		assertEquals(409, createAccount.getStatus());

	}

	@Test
	public void test_when_invoked_when_incorrect_payload() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.createAccount("incorrect");
		assertEquals(400, createAccount.getStatus());

	}

	@Test
	public void test_when_invoked_when_orderexceptionOccurs() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		orderProcessServiceController = new OrderProcessServiceController();
		PowerMockito.when(orderService.isNewAccount(any(AccountInfo.class)))
				.thenThrow(new OrderProcessServiceException(ErrorCode.CREATE_ACCOUNT));
		Response createAccount = orderProcessServiceController.createAccount(TestConstants.USER_INFO);
		assertEquals(500, createAccount.getStatus());

	}

	@Test
	public void test_when_checkAccount() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		AccountInfo acc = new AccountInfo();
		acc.setBilling_info("st laurent");
		List<AccountInfo> arrayList = new ArrayList<AccountInfo>();
		arrayList.add(acc);
		PowerMockito.when(orderService.checkIfAccountExists(any(UserInfo.class))).thenReturn(arrayList);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.checkAccount(TestConstants.USER_NAME);
		assertEquals(200, createAccount.getStatus());

	}
	
	@Test
	public void test_when_checkAccount_username_password_incorrect() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		List<AccountInfo> arrayList = new ArrayList<AccountInfo>();
		PowerMockito.when(orderService.checkIfAccountExists(any(UserInfo.class))).thenReturn(arrayList);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.checkAccount(TestConstants.USER_NAME);
		assertEquals(401, createAccount.getStatus());

	}
	
	@Test
	public void test_when_payload_incorrect() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		List<AccountInfo> arrayList = new ArrayList<AccountInfo>();
		PowerMockito.when(orderService.checkIfAccountExists(any(UserInfo.class))).thenReturn(arrayList);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.checkAccount("invalid");
		assertEquals(400, createAccount.getStatus());

	}

	@Test
	public void test_when_createOrder() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		OrderPlaceResponse orderPlaceResponse = new OrderPlaceResponse();
		orderPlaceResponse.setPurchase_id("1234");
		PowerMockito.when(orderService.authorizeOrder(any(OrderRequest.class))).thenReturn(orderPlaceResponse);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.createOrder(TestConstants.CREATE_ORDER);
		assertEquals(200, createAccount.getStatus());

	}

	@Test
	public void test_when_confirm_order() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		OrderPlaceResponse orderPlaceResponse = new OrderPlaceResponse();
		orderPlaceResponse.setPurchase_id("1234");
		PowerMockito.when(orderService.authorizeOrder(any(OrderRequest.class)))
				.thenThrow(new OrderProcessServiceException(ErrorCode.ORDER_AUTH_ERROR));
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.createOrder(TestConstants.CREATE_ORDER);
		assertEquals(500, createAccount.getStatus());

	}
	
	@Test
	public void test_when_confirmOrder() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		PowerMockito.when(orderService.checkTransactionsCount()).thenReturn(1);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.confirmOrder(TestConstants.CONFIRM_ORDER);
		assertEquals(200, createAccount.getStatus());

	}
	
	
	@Test
	public void test_when_confirmOrder_on_5_request_show_throw_403() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		PowerMockito.when(orderService.checkTransactionsCount()).thenReturn(5);
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.confirmOrder(TestConstants.CONFIRM_ORDER);
		assertEquals(403, createAccount.getStatus());

	}
	

	@Test
	public void test_when_confirmOrder_throws_order_expection() throws Exception {
		PowerMockito.whenNew(OrderProcessService.class).withNoArguments().thenReturn(orderService);
		PowerMockito.when(orderService.checkTransactionsCount()).thenThrow(new OrderProcessServiceException(ErrorCode.CONFIRM_ORDER));
		orderProcessServiceController = new OrderProcessServiceController();
		Response createAccount = orderProcessServiceController.confirmOrder(TestConstants.CONFIRM_ORDER);
		assertEquals(500, createAccount.getStatus());

	}


}
