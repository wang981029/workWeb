package com.wangfuzeng.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    //是否成功
    private boolean success;

    //响应码
    private int code;

    //响应的数据
    private T data;

    //响应的信息
    private String message;

    /**
     * 默认响应成功的返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result(true, 0, (Object) null, null);
    }

    /**
     * 响应成功带返回数据
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result(true, 0, (Object) data, null);
    }

    /**
     * 默认响应失败的返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return new Result(false, 500, (Object) null, null);
    }

    /**
     * 响应失败的返回
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String message) {
        return new Result(false, 500, (Object) null, message);
    }

    /**
     * 响应失败的返回，带状态码
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(int code, String message) {
        return new Result(false, code, (Object) null, message);
    }
}
