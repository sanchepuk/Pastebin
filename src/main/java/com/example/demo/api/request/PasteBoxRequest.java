package com.example.demo.api.request;

public class PasteBoxRequest {
    public String data;
    public long expirationTimeSeconds;
    public PublicStatus publicStatus;

    public PasteBoxRequest() {
    }

    public PasteBoxRequest(String data, long expirationTimeSeconds, PublicStatus publicStatus) {
        this.data = data;
        this.expirationTimeSeconds = expirationTimeSeconds;
        this.publicStatus = publicStatus;
    }

    // геттеры и сеттеры
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public long getExpirationTimeSeconds() { return expirationTimeSeconds; }
    public void setExpirationTimeSeconds(long expirationTimeSeconds) { this.expirationTimeSeconds = expirationTimeSeconds; }

    public PublicStatus getPublicStatus() { return publicStatus; }
    public void setPublicStatus(PublicStatus publicStatus) { this.publicStatus = publicStatus; }
}
