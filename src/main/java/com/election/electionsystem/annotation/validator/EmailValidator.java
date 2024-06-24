package com.election.electionsystem.annotation.validator;

import com.election.electionsystem.annotation.ValidateMail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidateMail, String> {
    private String regex;
    @Override
    public void initialize(ValidateMail constraintAnnotation) {
        this.regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String mailGiven, ConstraintValidatorContext constraintValidatorContext) {
        return mailGiven == null || mailGiven.matches(regex);
    }
}
