package com.zhiliao.comment;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:17
 **/
public class BizResultVO {
    private Integer bizCode;
    private String bizMessage;
    private Object bizData;

    public Integer getBizCode() {
        return this.bizCode;
    }

    public void setBizCode(Integer bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizMessage() {
        return this.bizMessage;
    }

    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage;
    }

    public Object getBizData() {
        return this.bizData;
    }

    public void setBizData(Object bizData) {
        this.bizData = bizData;
    }

    public BizResultVO() {
    }

    public BizResultVO(Integer bizCode, String bizMessage, Object bizData) {
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
        this.bizData = bizData;
    }

    public String toString() {
        return "BizResultVO{bizCode=" + this.bizCode + ", bizMessage='" + this.bizMessage + '\'' + ", bizData=" + this.bizData + '}';
    }
}
