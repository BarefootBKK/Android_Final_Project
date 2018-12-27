package com.example.as_final_project.entities;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetTask extends AsyncTask<RequestBody, Integer, NetTask.MyResponse> {

    public static final int GET = 0;
    public static final int POST = 1;

    private NetListener netListener;    // 网络监听器
    private String url;         // URL
    private int method;        // 网络访问方法

    /**
     * 构造器
     * @param url
     * @param method
     * @param netListener
     */
    public NetTask(String url, int method, NetListener netListener) {
        this.url = url;
        this.method = method;
        this.netListener = netListener;
    }

    /**
     * 执行前的准备工作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 执行完成后的工作
     * @param myResponse
     */
    @Override
    protected void onPostExecute(MyResponse myResponse) {
        if (myResponse.getCode() == HttpHolder.SUCCESS_CODE) {
            netListener.onNetSuccess(myResponse.getData(), myResponse.getMessage());
        } else {
            netListener.onNetError(myResponse.getCode(), myResponse.getMessage());
        }
        netListener.onNetJSON(myResponse.getOriginalJSON());
    }

    /**
     * 执行过程中一些工作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 要执行的任务
     * @param requestBodies
     * @return
     */
    @Override
    protected MyResponse doInBackground(RequestBody... requestBodies) {
        try {
            HttpHolder httpHolder = new HttpHolder();
            String jsonData;
            if (method == POST) {
                jsonData = httpHolder.sendOkHttpRequestByPOST(url, requestBodies[0]).body().string();
            } else {
                jsonData = httpHolder.sendOkHttpRequestByGET(url).body().string();
            }
            if (jsonData.isEmpty()) {
                return new MyResponse(HttpHolder.DATA_SEND_ERROR, null, "数据传输出错！");
            } else {
                return httpHolder.getResponseData(jsonData);
            }
        } catch (SocketTimeoutException e) {
            return new MyResponse(HttpHolder.SERVER_UNAVAILABLE, null, "无法连接服务器!");
        }
        catch (IOException e) {
            return new MyResponse(HttpHolder.UNKNOWN_ERROR, null, e.toString());
        }
    }

    /**
     * 获取监听器实例
     * @return
     */
    public NetListener getNetListener() {
        return netListener;
    }

    /**
     * 设置监听器
     * @param netListener
     */
    public void setNetListener(NetListener netListener) {
        this.netListener = netListener;
    }

    /**
     * 获取当前任务的URL
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置当前任务的URL
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取当前访问方法
     * @return
     */
    public int getMethod() {
        return method;
    }

    /**
     * 设置当前访问方法
     * @param method
     */
    public void setMethod(int method) {
        this.method = method;
    }


/*################## 内部interface【NetListener】###################*/
    /**
     * 网络监听类interface
     */
    public interface NetListener {
        void onNetSuccess(String jsonData, String message);
        void onNetError(int errorCode, String errorMessage);
        void onNetJSON(String originalJSON);
    }

/*################## 内部类【MyResponse】###################*/
    /**
     * MyResponse
     */
    public class MyResponse {
        private int code;            // 状态代码
        private String data;         // 数据
        private String message;
        private String originalJSON;

        public MyResponse() {
        }

        public MyResponse(int code, String data, String message) {
            this.code = code;
            this.data = data;
            this.message = message;
        }

        /**
         * 获取response code
         * @return
         */
        public int getCode() {
            return code;
        }

        /**
         * 设置response code
         * @param code
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * 获取response data
         * @return
         */
        public String getData() {
            return data;
        }

        /**
         * 设置response data
         * @param data
         */
        public void setData(String data) {
            this.data = data;
        }

        /**
         * 获取message
         * @return
         */
        public String getMessage() {
            return message;
        }

        /**
         * 设置message
         * @param message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        public String getOriginalJSON() {
            return originalJSON;
        }

        public void setOriginalJSON(String originalJSON) {
            this.originalJSON = originalJSON;
        }

        /**
         * response toString
         * @return
         */
        @Override
        public String toString() {
            String str = "code="
                    + this.code
                    + "\ndata(or message)=" + this.data;
            return str;
        }
    }


/*################## 内部类【HttpHolder】###################*/
    /**
     * 访问网络的类
     */
    public class HttpHolder {
        public static final int SUCCESS_CODE = 200;
        public static final int SERVER_ERROR = 201;
        public static final int DATA_SEND_ERROR = 202;
        public static final int SERVER_UNAVAILABLE = 101;
        public static final int UNKNOWN_ERROR = 100;

        /**
         * 通过POST方法访问
         * @param address
         * @param requestBody
         * @return
         * @throws IOException
         */
        public Response sendOkHttpRequestByPOST(String address, RequestBody requestBody) throws IOException {
            OkHttpClient client = new OkHttpClient();
            client.newBuilder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .readTimeout(1000, TimeUnit.MILLISECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(address)
                    .post(requestBody)
                    .build();
            return client.newCall(request).execute();
        }

        /**
         * 通过GET方法访问
         * @param address
         * @return
         * @throws IOException
         */
        public Response sendOkHttpRequestByGET(String address) throws IOException {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .readTimeout(1000, TimeUnit.MILLISECONDS)
                    .build();
            Request request = new Request.Builder()
                    .url(address)
                    .build();
            return client.newCall(request).execute();
        }

        /**
         *
         * @param jsonData
         * @return
         */
        public MyResponse getResponseData(String jsonData) {
            MyResponse myResponse = new MyResponse();
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                myResponse.setCode(jsonObject.getInt("code"));
                myResponse.setData(jsonObject.getString("data"));
                myResponse.setMessage(jsonObject.getString("message"));
                myResponse.setOriginalJSON(jsonData);
            } catch (Exception e) { }
            return myResponse;
        }
    }
}
