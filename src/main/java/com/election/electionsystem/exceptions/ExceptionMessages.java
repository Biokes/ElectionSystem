package com.election.electionsystem.exceptions;

import lombok.Getter;
import org.aspectj.bridge.Message;
@Getter
public enum ExceptionMessages {
    UNDER_AGED("TOO YOUNG TO VOTE"),
    INVALID_EMAIL("Invalid email provided"),
    SOMETHING_WENT_WRONG("Something went wrong"),
    VOTER_NOT_FOUND("voter not found"),
    ALREADY_VOTED("vote already saved before now"),
    INVALID_DETAILS("Invalid details provided."),
    INVALID_NUMBER("Invalid Number provided");
    final String message;
    ExceptionMessages(String message){
        this.message= message;
    }

}
