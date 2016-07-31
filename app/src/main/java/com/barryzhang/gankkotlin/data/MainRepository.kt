package com.barryzhang.gankkotlin.data

import com.barryzhang.gankkotlin.api.GankAPI
import com.barryzhang.gankkotlin.entities.DailyGankEntity
import com.barryzhang.gankkotlin.entities.DayContent
import com.barryzhang.gankkotlin.entities.GankDate
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 16:41.
 */
class MainRepository{

    companion object{
        var instance  = MainRepository()
//        var instance : MainRepository? = null
//            get() {
//                if(this == null){
//
//                }
//            }
    }

    fun getDailyGankEntity(day: GankDate,
                      subscriber: Subscriber<DailyGankEntity>){
        GankAPI.instance.makeSubscribe(
                GankAPI.instance.getDailyGankEntity(day),
                subscriber)
    }
    fun getDayContent(day: GankDate,
                      subscriber: Subscriber<DayContent>){
        GankAPI.instance.makeSubscribe(
                GankAPI.instance.getDayContent(day),
                subscriber)
    }
}
