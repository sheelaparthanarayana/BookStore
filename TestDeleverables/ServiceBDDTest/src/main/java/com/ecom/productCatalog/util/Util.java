package com.ecom.productCatalog.util;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static <T> String getStringFromJson(T josn) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;

		jsonInString = mapper.writeValueAsString(josn);
		return jsonInString;

	}

	public static <T> Object extractJsonObject(String jsonInput, Class<T> classType)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		T readValue = null;
		readValue = mapper.readValue(jsonInput, classType);
		return readValue;

	}

	public static Response doGet(String URI) throws KeyManagementException, NoSuchAlgorithmException {
		Builder request = getHttpClient().target(URI).request();
		Response invoke = request.buildGet().invoke();
		return invoke;
	}

	public static Response doPost(String URI, String entity) throws KeyManagementException, NoSuchAlgorithmException {
		Builder request = getHttpClient().target(URI).request();
		Response invoke = request.buildPost(Entity.entity(entity, MediaType.APPLICATION_JSON)).invoke();
		return invoke;

	}

	private static Client getHttpClient() throws KeyManagementException, NoSuchAlgorithmException {

		SSLContext sslcontext = null;

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

		return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
	}

}
