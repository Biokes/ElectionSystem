package com.election.electionsystem.annotation.annotation;

import com.election.electionsystem.annotation.validator.DateValidator;
import jakarta.validation.Constraint;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateValidator.class)
public @interface AfterDateValidator {
     String message () default"Date must be after {eventStartDate}";
     String eventStartStart() default"";
}
