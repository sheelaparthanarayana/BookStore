package com.ecom.productCatalog.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.productCatalog.exceptions.ProductCatalogException;
import com.ecom.productCatalog.response.ProductCatalogResponse;
import com.ecom.productCatalog.response.ResponseCategory;
import com.ecom.productCatalog.service.ProductCatalogService;
import com.ecom.productCatalog.util.ProductCatalogConstant;
import com.ecom.productCatalog.util.ProductCatalogUtil;

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
@Path("/")
public class ProductCatalogController {

	private static Logger logger = LoggerFactory.getLogger(ProductCatalogController.class);

	private ProductCatalogService productCatalogService = new ProductCatalogService();

	/**
	 * This method returns all the categories
	 * 
	 * @return Response
	 */
	@GET
	@Path("/allCategories")
	public Response getCategory() {
		List<String> allCatagories;
		try {
			allCatagories = productCatalogService.getAllCategories();
			ResponseCategory responseCategory = new ResponseCategory();
			responseCategory.setCategories(allCatagories);
			return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogUtil.responseString(responseCategory)).build();
		} catch (ProductCatalogException e) {
			logger.error("Exception Occured: " + e.getErrorCode().getDescription());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogConstant.INTERNAL_ERROR).build();
		}

	}

	/**
	 * This method returns the book details of a book
	 * 
	 * @param productID
	 * @return Response
	 */
	@GET
	@Path("/product/{productID}")
	public Response getProductInfo(@PathParam("productID") String productID) {
		ProductCatalogResponse responseObject = null;
		try {
			responseObject = productCatalogService.getProductInfo(productID);
			if (responseObject.getBooks().size() == 0) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON)
						.entity(ProductCatalogUtil.responseString(responseObject)).build();
			}
			return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogUtil.responseString(responseObject)).build();
		} catch (ProductCatalogException e) {
			logger.error("Exception Occured: " + e.getErrorCode().getDescription());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogConstant.INTERNAL_ERROR).build();
		}

	}

	/**
	 * This method returns all books under a category
	 * 
	 * @param category
	 * @return
	 */
	@GET
	@Path("/category/{categoryID}")
	public Response getProduct(@PathParam("categoryID") String category) {
		try {
			ProductCatalogResponse responseObject = productCatalogService.getAllProducts(category);
			if (responseObject.getBooks().size() == 0) {
				return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON)
						.entity(ProductCatalogUtil.responseString(responseObject)).build();
			}
			return Response.status(Status.OK).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogUtil.responseString(responseObject)).build();
		} catch (ProductCatalogException e) {
			logger.error("Exception Occured: " + e.getErrorCode().getDescription());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
					.entity(ProductCatalogConstant.INTERNAL_ERROR).build();
		}

	}

}
