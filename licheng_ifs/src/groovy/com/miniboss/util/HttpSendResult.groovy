package com.miniboss.util
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-10-28
 * Time: 13:53:04
 * To change this template use File | Settings | File Templates.
 */

public class HttpSendResult { 
    private int statusCode;
    private String response;
    private Exception exception;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return statusCode == 200;
    }

    public String toString() {
        if (exception != null) {
            return "RZ:ERROR " + exception.getMessage();
        } else if (statusCode == 200) {
            return "RZ:200";
        } else {
            return "RZ:" + statusCode + " " + response;
        }
    }
}