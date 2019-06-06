package com.lansea.wms.validate;

import com.lansea.wms.util.Regex;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CodeValidate.Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeValidate {
    String message() default "编码长度必须为 2 - 40";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<CodeValidate, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.equals("")) {
                return false;
            }
            return value.length() >= 2 && value.length() <= 40;
        }

        @Override
        public void initialize(CodeValidate constraintAnnotation) {

        }
    }
}
