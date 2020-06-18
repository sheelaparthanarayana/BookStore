/*
|--------------------------------------------------------------------------
| Error code java class
|--------------------------------------------------------------------------
|
|Error code class is used for represent all error we would meet in web servlet
|
*/
package com.ecom.ecar.exception;

public enum ErrorCode {
	
	CLIENT_CREATION("Client creation error"), JSON_EXTRACTION_ERROR("Json extraction exception"),
	UNABLE_TO_START_SERVICE("Config file error"), CAN_NOT_GET_BOOK("can not get detail of book"),
	CAN_NOT_CREATE_ORDER("can not create order"), CAN_NOT_CONFIRM_ORDER("can not confirm your order"),
	CAN_NOT_PROCEED("can not proceed with your request"), CAN_NOT_GET_CATEGORY("can not get categories in home page"),
	CAN_NOT_GET_BOOKS("can not get books in home page");

	private String description;
	
	ErrorCode(String message){
		this.description = message;
		
	}
	public String getDescription() {
		return this.description;
	}


}
