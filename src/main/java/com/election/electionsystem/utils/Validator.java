package com.election.electionsystem.utils;

import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.requests.VoterRequest;

import java.util.Optional;

import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_EMAIL;
import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_NUMBER;

public class Validator {
    public static void validate(VoterRequest voterRequest){
        validateMail(voterRequest.getEmail());
        validateNumber(voterRequest.getInfoRequest().getPhoneNumber());
    }

    private static void validateMail(String email) {
        String pattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$";
        if(Optional.ofNullable(email).isEmpty() || !pattern.matches(email))
            throw new ElectionException(INVALID_EMAIL.getMessage());
    }

    private static void validateNumber(String phoneNumber) {
        if(Optional.ofNullable(phoneNumber).isEmpty() || !phoneNumber.matches("\\d*"))
            throw new ElectionException(INVALID_NUMBER.getMessage());
    }
}
