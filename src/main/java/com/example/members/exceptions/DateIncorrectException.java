package com.example.members.exceptions;

public class DateIncorrectException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateIncorrectException(String date) {

        super(String.format("Date %s incorrect", date));
    }
}
