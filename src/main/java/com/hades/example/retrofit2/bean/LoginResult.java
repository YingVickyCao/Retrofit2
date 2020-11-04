package com.hades.example.retrofit2.bean;

public class LoginResult {
    String status;
    long ts;

    public LoginResult(String status, long ts) {
        this.status = status;
        this.ts = ts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "status='" + status + '\'' +
                ", ts=" + ts +
                '}';
    }
}
