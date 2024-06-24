package com.election.electionsystem.exceptions;

public class ElectionException extends RuntimeException{
    public ElectionException(String errorMessage){
        super(errorMessage);
    }
}
