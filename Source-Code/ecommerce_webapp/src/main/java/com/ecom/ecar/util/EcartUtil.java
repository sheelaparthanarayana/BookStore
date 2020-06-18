package com.ecom.ecar.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EcartUtil {
	private Properties properties = null;
	private static EcartUtil ecarUtil = null;

	public static String buildPath(String contextPath, String dest) {
		return new StringBuilder().append(contextPath).append(dest).toString();
	}

	public String readProperty(String key) throws EcartException {
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
			throw new EcartException(ErrorCode.UNABLE_TO_START_SERVICE);
		}

	}

	public static <T> String getStringFromJson(T josn) throws EcartException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(josn);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new EcartException(ErrorCode.JSON_EXTRACTION_ERROR, e);
		}
		return jsonInString;

	}

	public static <T> Object extractJsonObject(String jsonInput, Class<T> classType) throws EcartException {
		ObjectMapper mapper = new ObjectMapper();
		T readValue = null;
		try {
			readValue = mapper.readValue(jsonInput, classType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EcartException(ErrorCode.JSON_EXTRACTION_ERROR, e);
		}
		return readValue;

	}

	public static Response doGet(String URI) throws EcartException {
		Builder request = getHttpClient().target(URI).request();
		Response invoke = request.buildGet().invoke();
		return invoke;
	}

	public static Response doPost(String URI, String entity) throws EcartException {
		Builder request = getHttpClient().target(URI).request();
		Response invoke = request.buildPost(Entity.entity(entity, MediaType.APPLICATION_JSON)).invoke();
		return invoke;

	}

	public static String constructURI(String service, String resource) throws EcartException {
		EcartUtil util = EcartUtil.getUtil();
		return new StringBuilder().append(util.readProperty(service)).append(resource).toString().replaceAll(" ", "%20");
	}

	private static Client getHttpClient() throws EcartException {

		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("TLS");

			sslcontext.init(null, new TrustManager[] { new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, new SecureRandom());
		} catch (Exception e) {
			throw new EcartException(ErrorCode.CLIENT_CREATION, e);
		}
		return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
	}

	public static EcartUtil getUtil() {
		if (ecarUtil == null) {
			ecarUtil = new EcartUtil();
			return ecarUtil;
		}
		return ecarUtil;
	}


}
