package com.barryzhang.gankkotlin.data.remote

import com.barryzhang.gankkotlin.BuildConfig
import com.barryzhang.gankkotlin.entities.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Observer
import rx.SingleSubscriber
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/12 11:32.
 */

class GankAPI {
    lateinit var retrofit: Retrofit
    lateinit var service: com.barryzhang.gankkotlin.data.remote.GankService;

    constructor() {
        val httpClientBuilder = OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(interceptor)
        }

        retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        service = retrofit.create(com.barryzhang.gankkotlin.data.remote.GankService::class.java)
    }

    companion object {

        const val BASE_URL = "http://gank.io/api/"
        const val DEFAULT_TIMEOUT: Long = 10

        val instance: GankAPI = GankAPI()


        // 以下是实验室


        val aa: String ?
            get() = "5"

        val bb: String ?
            get() {
                return "3"
            }


        // 0
        val name0 = "Barry"
        // 1
        val name1: String = "Jerry"
        // 2
        val name2: String? = "Jack"
        // 3
        var name3: String
            get() = "哈哈哈哈"
            set(value) {
            }
        // 4
        var name4: String? = null
            get() {
                return "哟"
            }
            set(value) {
                if (value == null) {
                    field = "默认值"
                }
            }

    }


    fun <T : BaseHttpResponseEntity<*>> makeSubscribe(
            observable: Observable<T>, subscriber: Observer<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map { entity ->
                    if (entity.error) { /* TODO 统一的错误处理 */ }
                    entity
                }
                .subscribe(subscriber)
    }

    fun getDayContent(day: GankDate): Observable<DayContent> {
        return service.contentDay(day.year, day.month, day.day)
    }

    fun getHistory(observer: Observer<History>)  {
        makeSubscribe (service.history(),observer)
    }

    fun getHistory(): Observable<History> {
        return service.history() ;
    }

    fun getDailyGankEntity(day: GankDate) : Observable<DailyGankEntity> {
        return service.dayByYearMonthDay(day.year, day.month, day.day)
    }


}