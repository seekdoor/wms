package com.lansea.wms.validate;

import com.lansea.wms.util.Regex;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserName.Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "用户名格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<UserName, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null || value.equals("")) {
                return true;
            }
            return value.matches(Regex.USER_NAME);
        }

        @Override
        public void initialize(UserName constraintAnnotation) {

        }
    }
}
