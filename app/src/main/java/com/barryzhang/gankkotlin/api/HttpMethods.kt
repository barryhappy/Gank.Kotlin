package com.barryzhang.gankkotlin.api

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

    var service: GankService
        internal set


    private object SingletonHolder {
        val INSTANCE = HttpMethods()
    }

    init {
        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        retrofit = Retrofit.Builder().client(httpClientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build()

        service = retrofit.create(GankService::class.java)
    }

    companion object {

        val BASE_URL = "http://gank.io/api/"
        val DEFAULT_TIMEOUT = 10

        //获取单例
        val instance: HttpMethods
            get() = SingletonHolder.INSTANCE
    }


}

