package com.example.members.exceptions;

public class DateIncorrectException extends RuntimeException {
    public DateIncorrectException(String date) {

        super(String.format("Date %s incorrect", date));
    }
}
