package com.election.electionsystem.annotation;

import com.election.electionsystem.annotation.validator.EmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidateMail {
    String regex()default"{Invalid email}";
}
