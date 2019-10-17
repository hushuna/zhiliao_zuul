package com.zhiliao.comment;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:16
 **/
public class Result {
    private Integer code;
    private String description;
    private BizResultVO detail;

    public Result(Integer code, String description, BizResultVO detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }

    public static Result createError() {
        return new Result(-500, "系统异常", (BizResultVO)null);
    }

    public static Result createError(Integer errorCode, String errorMessage) {
        return new Result(errorCode, errorMessage, (BizResultVO)null);
    }

    public static Result createSuccess() {
        return new Result(200, "SUCCESS", new BizResultVO(1, "SUCCESS", (Object)null));
    }

    public static Result createSuccess(String bizMessage) {
        return new Result(200, "SUCCESS", new BizResultVO(1, bizMessage, (Object)null));
    }

    public static Result createSuccess(String bizMessage, Object bizData) {
        return new Result(200, "SUCCESS", new BizResultVO(1, bizMessage, bizData));
    }

    public static Result createBizError(Integer bizCode, String bizMessage) {
        return new Result(200, "", new BizResultVO(bizCode, bizMessage, (Object)null));
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetail() {
        return this.detail;
    }

    public void setDetail(BizResultVO detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Result{code=" + this.code + ", description='" + this.description + '\'' + ", detail=" + this.detail + '}';
    }
}
