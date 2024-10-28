package com.electronics.store.electronocs_Store.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
    public BadRequestException(){
        super("Bad Request");
    }
}
