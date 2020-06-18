/*
|--------------------------------------------------------------------------
| UserInfo java class
|--------------------------------------------------------------------------
|
|UserInfo is used to retreive the user status from services
|The web servlet will compose an UserInfo and send it to services for checkaccount
|
*/
package com.ecom.ecar.entity;


public class UserInfo {
	
	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
