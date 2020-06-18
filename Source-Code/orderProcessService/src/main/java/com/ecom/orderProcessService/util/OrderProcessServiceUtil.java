package com.ecom.orderProcessService.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ecom.orderProcessService.exceptions.ErrorCode;
import com.ecom.orderProcessService.exceptions.OrderProcessServiceException;
import com.ecom.orderProcessService.model.AccountInfo;
import com.ecom.orderProcessService.request.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/** OrderProcessServiceUtil The Util class **/
public class OrderProcessServiceUtil {

	private Properties properties = null;
	private static OrderProcessServiceUtil orderProcessServiceUtil = null;

	/**
	 * This method reads property
	 * 
	 * @param key
	 * @return
	 * @throws OrderProcessServiceException
	 */
	public String readProperty(String key) throws OrderProcessServiceException {
		try {
			if (properties == null) {
				properties = new Properties();
				InputStream inputStream = this.getClass().getClassLoader()
						.getResourceAsStream("application.properties");
				properties.load(inputStream);
				inputStream.close();
			}
			return (String) properties.get(key);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OrderProcessServiceException(ErrorCode.UNABLE_TO_START_SERVICE);
		}

	}

	/**
	 * This method converts java object to string
	 * 
	 * @param Object
	 * @return
	 * @throws OrderProcessServiceException
	 */
	/**
	 * 
	 * refernce 
	 * https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
	 */
	public static <E> String responseString(E Object) throws OrderProcessServiceException {
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString;
		try {
			writeValueAsString = mapper.writeValueAsString(Object);
		} catch (IOException e) {
			e.printStackTrace();
			throw new OrderProcessServiceException(ErrorCode.BUSINESS_VALIDATION_ERROR);
		}
		return writeValueAsString;
	}

	/**
	 * Converts string json object to respective Java object .
	 * 
	 * @param jsonInput
	 * @param classType
	 * @return
	 * @throws OrderProcessServiceException
	 */
	/**
	 * refernce :
	 * https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
	 */
	public static <T> Object extractJsonObject(String jsonInput, Class<T> classType)
			throws OrderProcessServiceException {
		ObjectMapper mapper = new ObjectMapper();
		T readValue = null;
		try {
			readValue = mapper.readValue(jsonInput, classType);
		} catch (Exception e) {
			throw new OrderProcessServiceException(ErrorCode.VALIDATION_ERROR);
		}
		return readValue;

	}

	/**
	 * this method return instance of orderProcessServiceUtil
	 * 
	 * @return OrderProcessServiceUtil
	 */

	public static OrderProcessServiceUtil getUtil() {
		if (orderProcessServiceUtil == null) {
			orderProcessServiceUtil = new OrderProcessServiceUtil();
			return orderProcessServiceUtil;
		}
		return orderProcessServiceUtil;
	}

	/**
	 * This method validate the Account Info
	 * 
	 * @param acc
	 * @return
	 * @throws OrderProcessServiceException
	 */
	public static boolean validateRequest(AccountInfo acc) throws OrderProcessServiceException {
		if (checkStringHasText(acc.getBilling_info()) && checkStringHasText(acc.getEmail())
				&& checkStringHasText(acc.getPassword()) && checkStringHasText(acc.getPhone())
				&& checkStringHasText(acc.getShipping_info())) {
			return true;
		}
		throw new OrderProcessServiceException(ErrorCode.VALIDATION_ERROR);

	}

	/**
	 * This method validates OrderRequest
	 * 
	 * @param ordReq
	 * @return
	 * @throws OrderProcessServiceException
	 */
	public static boolean validateOrderRequest(OrderRequest ordReq) throws OrderProcessServiceException {
		if (checkStringHasText(ordReq.getAccountId()) && checkStringHasText(ordReq.getShipping_info())
				&& (ordReq.getBookIds().size()) > 0 && (ordReq.getTotal_price() > 0)) {
			return true;
		}
		throw new OrderProcessServiceException(ErrorCode.VALIDATION_ERROR);

	}

	private static boolean checkStringHasText(String val) {
		if (val == null || val.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
}
