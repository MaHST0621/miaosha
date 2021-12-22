package com.example.miaosha.miaosha002.validator;

import com.example.miaosha.miaosha002.util.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtils.isMobile(mobile);
        }else {
            if (StringUtils.isEmpty(mobile)) {
                return true;
            }else {
                return ValidatorUtils.isMobile(mobile);
            }
        }
    }
}
