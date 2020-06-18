/*
|--------------------------------------------------------------------------
| Exception java class
|--------------------------------------------------------------------------
|
|exception class is used for handling all exceptions may throw in servlet
|an exception represents the unhandled error meet in web servlet
|
*/
package com.ecom.ecar.exception;

public class EcartException extends Exception {
	private ErrorCode errorCode;

	public EcartException() {
		super();
	}

	public EcartException(ErrorCode errorCode) {
		this.errorCode = errorCode;

	}

	public EcartException(ErrorCode errorCode, Throwable e) {
		super(e);
		this.errorCode = errorCode;

	}
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}

}
