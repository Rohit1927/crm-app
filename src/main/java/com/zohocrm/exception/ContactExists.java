package com.zohocrm.exception;

public class ContactExists extends RuntimeException {

    public ContactExists(String message) {
        super(message);
    }
}
