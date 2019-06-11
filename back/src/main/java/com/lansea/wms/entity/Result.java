package com.lansea.wms.entity;

import com.github.pagehelper.PageInfo;
import org.springframework.validation.BindingResult;

import java.util.List;

public class Result<T> {

    private final static Integer SUCCESS_CODE = 200;
    private final static Integer NORMAL_ERROR_CODE = 100;

    private Integer code;
    private T data;
    private String message;

    /**
     * 快速返回成功结果
     *
     * @param <T>  泛类型
     * @param data 成功信息
     * @return Result
     */
    public static <T> Result success(T data) {
        return new Result<T>().createSuccess(data);
    }

    /**
     * 检查是否为成功返回
     *
     * @return
     */
    public Boolean checkSuccess() {
        return code.equals(SUCCESS_CODE);
    }

    /**
     * 返回成功分页信息
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result successPage(List<T> data) {
        return new Result<>().createSuccess(new PageInfo<>(data));
    }

    /**
     * 删除数据
     *
     * @param num
     * @param name
     * @param <T>
     * @return
     */
    public static <T> Result successDelete(Integer num, String name) {
        return success("成功删除" + num + "条" + name);
    }

    /**
     * 删除数据
     *
     * @param num
     * @param <T>
     * @return
     */
    public static <T> Result successDelete(Integer num) {
        return successDelete(num, "数据");
    }

    /**
     * 快速返回失败结果
     *
     * @param <T>  泛类型
     * @param data 错误信息
     * @param code 错误码
     * @return Result
     */
    public static <T> Result error(T data, Integer code) {
        return new Result<T>().createError(data, code);
    }

    /**
     * 快速返回常规错误结果
     *
     * @param <T>  泛类型
     * @param data 错误信息
     * @return Result
     */
    public static <T> Result error(T data) {
        return error(data, NORMAL_ERROR_CODE);
    }

    /**
     * 根据验证器结果放回错误结果
     *
     * @param bindingResult 验证器验证结果
     * @return Result
     */
    public static Result errorByBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return error("未知异常");
    }

    /**
     * 返回成功结果
     *
     * @param data 数据
     * @return Result
     */
    public Result createSuccess(T data) {
        if (data instanceof Result) {
            return (Result) data;
        }
        return setCode(SUCCESS_CODE).setData(data);
    }

    /**
     * 返回错误结果
     *
     * @param data 数据
     * @param code 错误码
     * @return Result
     */
    public Result createError(T data, Integer code) {
        if (data instanceof Result) {
            return (Result) data;
        }
        return setCode(code).setData(data);
    }

    /**
     * 返回常规错误
     *
     * @param data 数据
     * @return Result
     */
    public Result createError(T data) {
        return createError(data, NORMAL_ERROR_CODE);
    }

    /**
     * 设置 code 为成功 code
     *
     * @return Result
     */
    public Result<T> setSuccess() {
        return setCode(SUCCESS_CODE);
    }

    /**
     * 设置 code 为常规失败 code
     *
     * @return Result
     */
    public Result<T> setNormalError() {
        return setCode(NORMAL_ERROR_CODE);
    }


    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        if (data instanceof String) {
            setMessage((String) data);
        }
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
