package com.election.electionsystem.utils;

import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.dtos.requests.VoterRequest;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static com.election.electionsystem.exceptions.ExceptionMessages.*;

public class Validator {
    public static void validate(VoterRequest voterRequest){
        validate(voterRequest.getDOB());
    }
    private static void validate(LocalDate date){
        if(Period.between(date, LocalDate.now()).getYears()<18)
            throw new ElectionException(UNDER_AGED.getMessage());
    }
//    private static void validateMail(String email) {
//        String pattern = "";
//        if(Optional.ofNullable(email).isEmpty() || !email.matches(pattern))
//            throw new ElectionException(INVALID_EMAIL.getMessage());
//    }
//
//    private static void validateNumber(String phoneNumber) {
//        if(Optional.ofNullable(phoneNumber).isEmpty() || !phoneNumber.matches("\\d*"))
//            throw new ElectionException(INVALID_NUMBER.getMessage());
//    }
}
