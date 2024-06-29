package com.example.members.exceptions;

public class MemberNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberNotFoundException(Integer id) {

        super(String.format("Member with id %d not found", id));
    }
}
