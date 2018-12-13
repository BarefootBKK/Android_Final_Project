package com.example.as_final_project.interfaces;

public interface HttpListener {
    void onSuccess(String jsonData);
    void onFailure(int failureCode, String failureData);
}
