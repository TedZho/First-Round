package com.h2rd.refactoring.usermanagement.util;

public enum ErrorStatus {
	
	NO_ERROR(0, "No User Error."), 
	SAVE_USER_ERROR(1, "Failed to save user."), 
	UPDATE_USER_ERROR(2, "Failed to update user."), 
	DELETE_USER_ERROR(3, "Failed to delete user."), 
	GET_USER_ERROR(4, "Failed to get user."), 
	USER_ERROR(5, "Failed to process user data."),
	EMAIL_REQUIRED_ERROR(6, "The email is compulsory."), 
	EMAIL_UNIQUE_ERROR(7, "The email has been registered."),
	EMAIL_FORMAT_ERROR(8, "The email format is not correct."),
	USER_ROLE_MIN_ERROR(10, "A user should have at least 1 role.");

	private String message = null;
	private int status = 0;
	
	private ErrorStatus(int status, String message) {
		this.message = message;
		this.status = status;
	}

	public static String getMessage(int status) {
		for (ErrorStatus c: ErrorStatus.values()) {
			if (c.getStatus() == status) {
				return c.getMessage();
			}
		}
		return "";
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
		
	
}
