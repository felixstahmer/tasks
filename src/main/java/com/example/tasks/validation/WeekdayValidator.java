package com.example.tasks.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeekdayValidator implements ConstraintValidator<IsWeekday, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime dueDate, ConstraintValidatorContext context) {
        if (dueDate == null) {
            return true;
        }

        DayOfWeek dayOfWeek = dueDate.getDayOfWeek();

        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }
}