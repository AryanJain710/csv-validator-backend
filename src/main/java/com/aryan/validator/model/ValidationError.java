package com.aryan.validator.model;

public class ValidationError {

    private int row;
    private String message;

    public ValidationError(int row, String message) {
        this.row = row;
        this.message = message;
    }

    public int getRow() {
        return row;
    }

    public String getMessage() {
        return message;
    }
}