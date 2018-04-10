package com.hasky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hasky.sdk.okhttp.request.CommonRequest;
import com.hasky.sdk.okhttp.request.RequestParams;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        RequestParams requestParams = new RequestParams(null,null);
        CommonRequest.createPostRequest("https://www.baidu.com",null);
    }
}
