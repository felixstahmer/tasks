package com.example.tasks.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WeekdayValidator.class) // Links to the new validator
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsWeekday {

    String message() default "Due date must be a weekday (Monday through Friday).";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}