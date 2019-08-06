package com.yarm.task.pojo;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/6/16
 * Time:15:01
 * Des:
 */
public class ResponseApi<T> {
    public ResponseApi(){
        this.status = true;
        this.erroMsg = "";
    }
    private boolean status;
    private T data;
    private String erroMsg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErroMsg() {
        return erroMsg;
    }

    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }

    @Override
    public String toString() {
        return "ResponseApi{" +
                "status=" + status +
                ", data=" + data +
                ", erroMsg='" + erroMsg + '\'' +
                '}';
    }
}