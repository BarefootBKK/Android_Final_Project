package com.example.as_final_project.managers;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestBodyManager {

    private FormBody.Builder builder;

    public RequestBodyManager() {
        this.builder = new FormBody.Builder();
    }

    public void addPostMap(String name, String value) {
        builder.add(name, value);
    }

    public RequestBody getRequestBody() {
        return builder.build();
    }
}
