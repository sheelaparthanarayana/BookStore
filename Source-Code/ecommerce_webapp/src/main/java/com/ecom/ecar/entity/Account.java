/*
|--------------------------------------------------------------------------
| Account java class
|--------------------------------------------------------------------------
|
|Account class is user for handling all account object
|An account object is used for fetch the current user information from service
|
*/
package com.ecom.ecar.entity;


public class Account {


	private String accountId;
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	private String first_name;

	private String last_name;

	private String email;

	private String password;

	private String phone;

	private String billing_info;

	private String shipping_info;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBilling_info() {
		return billing_info;
	}

	public void setBilling_info(String billing_info) {
		this.billing_info = billing_info;
	}

	public String getShipping_info() {
		return shipping_info;
	}

	public void setShipping_info(String shipping_info) {
		this.shipping_info = shipping_info;
	}

}
