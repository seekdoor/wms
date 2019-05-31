package com.lansea.wms.validate;

import com.lansea.wms.util.Regex;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RealName.Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RealName {
    String message() default "真实姓名格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<RealName, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.equals("")) {
                return true;
            }
            return value.matches(Regex.REAL_NAME);
        }

        @Override
        public void initialize(RealName constraintAnnotation) {

        }
    }
}
