package com.test.assignment01.entry;

public class RestExceptionEntry {

    private String error;

    public RestExceptionEntry() {
    }

    public RestExceptionEntry(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
