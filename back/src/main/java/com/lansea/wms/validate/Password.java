package com.lansea.wms.validate;

import com.lansea.wms.util.Regex;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Password.Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "密码长度必须为 6-20";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<Password, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.equals("")) {
                return true;
            }
            return value.matches(Regex.PASSWORD);
        }

        @Override
        public void initialize(Password constraintAnnotation) {

        }
    }
}
