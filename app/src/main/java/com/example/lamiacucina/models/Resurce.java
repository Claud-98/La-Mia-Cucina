package com.example.lamiacucina.models;

public class Resurce<T> {

    private T data;
    private int totalResults;
    private  int statusCode;
    private String statusMessage;

    public  Resurce(){}

    public Resurce(T data, int totalResults, int statusCode, String statusMessage) {
        this.data = data;
        this.totalResults = totalResults;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Resurce{" +
                "data=" + data +
                ", totalResults=" + totalResults +
                ", statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
