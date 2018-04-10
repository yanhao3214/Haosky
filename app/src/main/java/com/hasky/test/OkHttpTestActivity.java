package com.hasky.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.hasky.sdk.okhttp.CommonOkHttpClient;
import com.hasky.sdk.okhttp.call.OkHttpCallBack;
import com.hasky.sdk.okhttp.request.CommonRequest;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GPT-2964 on 2018/4/7.
 */

public class OkHttpTestActivity extends AppCompatActivity {
    private OkHttpClient client = CommonOkHttpClient.getOkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void test() {
        CommonOkHttpClient.getCall(CommonRequest.createGetRequest("http://www.taobao.com", null), new OkHttpCallBack() {
            @Override
            public void onFailure(Object object) {

            }

            @Override
            public void onSuccess(Object object) {

            }
        });
        /*
        * 发送请求，只需要调用一个方法，传入两个参数（一个请求，一个回调）
        * */
        CommonOkHttpClient.postCall(CommonRequest.createPostRequest("http://www.lego.com", null), new OkHttpCallBack() {
            @Override
            public void onFailure(Object object) {
                
            }

            @Override
            public void onSuccess(Object object) {
                // TODO: 2018/4/7 异常、数据解析、数据转发 （JsonCallback中处理）
            }
        });
//        Request postRequest = CommonRequest.createPostRequest("http://www.lego.com", null);
//       CommonOkHttpClient.postCall(postRequest, new OkHttpCallBack() {
//           @Override
//           public void onFailure(Object object) {
//
//           }
//
//           @Override
//           public void onSuccess(Object object) {
//
//           }
//       });
    }

    private void sendRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取OkHttpClient对象

                    //创建Request对象
                    Request.Builder requestBuilder = new Request.Builder();
                    Request request = requestBuilder
                            .url(url)
                            .get()
                            .build();
                    //创建Call对象
                    Call call = client.newCall(request);
                    /*
                    * 同步发送请求
                    * */
                    call.execute();
                    /*
                    * 异步发送请求
                    * */
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String result = response.body().string();
                        }
                    });
                    Response response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FormBody.Builder formBodyBuilder = new FormBody.Builder();
                    FormBody formBody = formBodyBuilder
                            .add("username", "lego")
                            .add("password", "333214")
                            .build();
                    Request.Builder requestBuilder = new Request.Builder();
                    Request request = requestBuilder
                            .url("http://www.lego.com")
                            .post(formBody)
                            .build();
                    Call call = client.newCall(request);
                    call.execute();
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
