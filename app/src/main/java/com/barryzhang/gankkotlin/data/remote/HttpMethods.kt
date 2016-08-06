package com.barryzhang.gankkotlin.data.remote

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Barry on 16/4/20.
 */
class HttpMethods private constructor() {

    internal var retrofit: Retrofit

    var service: com.barryzhang.gankkotlin.data.remote.GankService
        internal set


    private object SingletonHolder {
        val INSTANCE = com.barryzhang.gankkotlin.data.remote.HttpMethods()
    }

    init {
        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(com.barryzhang.gankkotlin.data.remote.HttpMethods.Companion.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        retrofit = Retrofit.Builder().client(httpClientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(com.barryzhang.gankkotlin.data.remote.HttpMethods.Companion.BASE_URL).build()

        service = retrofit.create(com.barryzhang.gankkotlin.data.remote.GankService::class.java)
    }

    companion object {

        val BASE_URL = "http://gank.io/api/"
        val DEFAULT_TIMEOUT = 10

        //获取单例
        val instance: com.barryzhang.gankkotlin.data.remote.HttpMethods
            get() = com.barryzhang.gankkotlin.data.remote.HttpMethods.SingletonHolder.INSTANCE
    }


}

