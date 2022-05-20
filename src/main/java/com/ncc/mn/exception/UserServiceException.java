package com.ncc.mn.exception;

public class UserServiceException extends RuntimeException{
    public UserServiceException(String mess){
        super(mess);
    }
}
