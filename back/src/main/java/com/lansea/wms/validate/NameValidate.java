package com.lansea.wms.validate;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidate.Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidate {
    String message() default "名称长度必须为 2 - 40";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NameValidate, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.equals("")) {
                return false;
            }
            return value.length() >= 2 && value.length() <= 40;
        }

        @Override
        public void initialize(NameValidate constraintAnnotation) {

        }
    }
}
