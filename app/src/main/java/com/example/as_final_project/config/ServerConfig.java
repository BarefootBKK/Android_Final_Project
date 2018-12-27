package com.example.as_final_project.config;

public class ServerConfig {
    public static final String IP = "192.168.43.252";
    public static final int PORT = 8000;

    public static String getAddress() {
        return "http://" + IP + ":" + PORT;
    }

    public static String getUrl(String destination) {
        String first = destination.substring(0, 1);
        if (first.equals("/")) {
            return getAddress() + destination;
        } else {
            return getAddress() + "/" + destination;
        }
    }
}
