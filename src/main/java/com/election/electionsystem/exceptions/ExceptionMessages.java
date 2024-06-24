package com.election.electionsystem.exceptions;

import lombok.Getter;
import org.aspectj.bridge.Message;
@Getter
public enum ExceptionMessages {
    INVALID_EMAIL("Invalid email provided"),
    INVALID_NUMBER("Invalid Number provided");
    final String message;
    ExceptionMessages(String message){
        this.message= message;
    }

}
