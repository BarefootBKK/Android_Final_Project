package com.example.as_final_project.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.as_final_project.config.NetConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtil {

    /**
     * 通过POST方法访问
     * @param address
     * @param requestBody
     * @return
     * @throws IOException
     */
    public static Response sendOkHttpRequestByPOST(String address, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        return getOkHttpClient().newCall(request).execute();
    }

    /**
     * 通过GET方法访问
     * @param address
     * @return
     * @throws IOException
     */
    public static Response sendOkHttpRequestByGET(String address) throws IOException {
        Request request = new Request.Builder()
                .url(address)
                .build();
        return getOkHttpClient().newCall(request).execute();
    }

    /**
     * 获取 OkHttpClient
     * @return OkHttpClient
     */
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

    /**
     * 获取网络状态
     * @param context
     * @return
     */
    public static int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NetConfig.NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NetConfig.NETWORK_MOBILE;
            }
        } else {
            return NetConfig.NETWORK_NONE;
        }
        return NetConfig.NETWORK_NONE;
    }

    /**
     * 是否已连接网络
     * @param context
     * @return
     */
    public static boolean isNetConnect(Context context) {
        if (getNetWorkState(context) < 0) {
            return false;
        } else {
            return true;
        }
    }

}
