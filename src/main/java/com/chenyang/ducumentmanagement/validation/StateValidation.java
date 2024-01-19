package com.chenyang.ducumentmanagement.validation;

import com.chenyang.ducumentmanagement.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {

    /**
     * @param value 将来要校验的数据
     * @param constraintValidatorContext
     *
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (value == null){
            return false;
        }
        if (value.equals("已发布") ||value.equals("草稿")){
            return true;
        }
        return false;
    }
}
