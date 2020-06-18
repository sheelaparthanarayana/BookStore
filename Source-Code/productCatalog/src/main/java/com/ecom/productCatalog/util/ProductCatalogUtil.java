package com.ecom.productCatalog.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ecom.productCatalog.exceptions.ErrorCode;
import com.ecom.productCatalog.exceptions.ProductCatalogException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
public class ProductCatalogUtil {

	private Properties properties = null;

	/**
	 * This method return a property
	 * 
	 * @param key
	 * @return String
	 * @throws ProductCatalogException
	 */
	public String readProperty(String key) throws ProductCatalogException {
		try {
			if (properties == null) {
				properties = new Properties();
				InputStream inputStream = this.getClass().getClassLoader()
						.getResourceAsStream(ProductCatalogConstant.CONFIG_FILE);
				properties.load(inputStream);
				inputStream.close();
			}
			return (String) properties.get(key);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ProductCatalogException(ErrorCode.UNABLE_TO_START_SERVICE);
		}

	}

	/**
	 * This method converts java object to String in json format
	 * @param allProducts
	 * @return String
	 * @throws ProductCatalogException
	 */
	/**
	 * refernce : https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
	 */
	public static <T> String responseString(T allProducts) throws ProductCatalogException {
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString;
		try {
			mapper.writeValueAsString(allProducts);
			writeValueAsString = mapper.writeValueAsString(allProducts);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ProductCatalogException(ErrorCode.BUSINESS_VALIDATION_ERROR);
		}
		return writeValueAsString;
	}

}
