package com.chenyang.ducumentmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@Data
@NoArgsConstructor //增加构造方法，否则后面直接return的Result构造方法并没有定义，就会报错
@AllArgsConstructor //
public class Result<T> {
    private Integer code;//业务状态码  0-成功  1-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Success", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(0, "Success", null);
    }
    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
