package com.example.members.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Integer id) {

        super(String.format("Member with id %d not found", id));
    }
}
