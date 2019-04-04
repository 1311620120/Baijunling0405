package com.example.baijunling0405.model;


import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import android.os.DropBoxManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;



/**
 * @Auther: 白俊岭
 * @Date: 2019/4/4 08:45:03
 * @Description:
 */
public class HttpUitls<T>{
    private  OkHttpClient okHttpClient;
    private HttpUitls(){
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LogginIntecepter())
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }
    public  static  HttpUitls getInstance(){
        return  HttpUtilsInstansce.httpUitls;
    }
    private  static  class  HttpUtilsInstansce{
        private  static  HttpUitls httpUitls=new HttpUitls();
    }
    private  class  LogginIntecepter implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            RequestBody body = request.body();
            Log.e("lalala",""+body);
            Headers headers = request.headers();
            Response proceed = chain.proceed(request);
            Headers headers1 = proceed.headers();

            return proceed;
        }
    }
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T t = (T)msg.obj;
            mCallback.onResponse(t);
        }
    };
    private  CallBackData mCallback;
    public  void  getData(String url, final Class<T> tClass, CallBackData callBackData){
        this.mCallback=callBackData;
        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                T t =  gson.fromJson(string,tClass);
                Message message = handler.obtainMessage();
                message.obj=t;
                handler.sendMessage(message);
            }
        });
    }
    public  void  setData(String url, final Class<T> tClass, String name,String pwd,final CallBackData callBackData){
        this.mCallback=callBackData;

        FormBody build1 = new FormBody.Builder()
                .add("phone", name)
                .add("pwd", pwd)
                .build();
        Request build = new Request.Builder()
                .url(url)
                .post(build1)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                T t =  gson.fromJson(string,tClass);
                Message message = handler.obtainMessage();
                message.obj=t;
                handler.sendMessage(message);
            }
        });
    }
    public  interface  CallBackData<D>{
        public  void  onResponse(D d);
        public  void  onFail(String err);
    }
}