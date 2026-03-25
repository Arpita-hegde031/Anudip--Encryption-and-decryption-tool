package com.crypto.encryption_system.model;

public class CryptoRequest {
    private String message;
    private String method;

    // getters and setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
