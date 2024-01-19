package com.chenyang.ducumentmanagement.anno;

import com.chenyang.ducumentmanagement.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //元注解
@Constraint(
        validatedBy = {StateValidation.class}
)
@Target({ElementType.FIELD}) //元注解
@Retention(RetentionPolicy.RUNTIME)

public @interface State {
    String message() default "state parameters must be 草稿 or 已发布";
    //指定分组
    Class<?>[] groups() default {};

    //获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
