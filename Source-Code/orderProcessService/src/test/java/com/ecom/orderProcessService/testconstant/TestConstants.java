/**
 * 
 */
package com.ecom.orderProcessService.testconstant;

/**
 * @author Varun Hanabe Muralidhara
 * UOttawa
 * ID : 300055628
 */
public class TestConstants {
	
	public static final String USER_INFO ="{\"first_name\":\"varun\",\"last_name\":\"hm\",\"email\":\"varunhm.293@gmail.com\",\"billing_info\":\"1991 st laurent blvd\",\"shipping_info\":\"1991 st alurent\",\"password\":\"varun\",\"phone\":\"9008228318\"}";
	public static final String USER_INFO_EXPECTED= "{\"accountId\":null,\"first_name\":\"varun\",\"last_name\":\"hm\",\"email\":\"varunhm.293@gmail.com\",\"password\":\"varun\",\"phone\":\"9008228318\",\"billing_info\":\"1991 st laurent blvd\",\"shipping_info\":\"1991 st alurent\"}";
	public static final String USER_NAME = "{\"username\":\"varunhm.293@gmail.com\",\"password\":\"password\"}";
	public static final String CREATE_ORDER ="{\"accountId\":\"19d6650e-5f01-4d05-adeb-eba2c3653aab\", \"shipping_info\":\"1991 st laurents blvd ontario\", \"total_price\":\"1234\", \"status\":\"PROCESSED\", \"bookIds\":[\"0132350882\"] }";

	public static final String CONFIRM_ORDER="{\"purchase_id\":\"22fd2ad1-ad52-437f-b4c6-a0a2e2d989ed\", \"status\":\"PROCESSED\" }";
}

