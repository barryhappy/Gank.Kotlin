package com.barryzhang.gankkotlin.data.remote

import com.barryzhang.gankkotlin.api.GankAPI
import com.barryzhang.gankkotlin.entities.DayContent
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/13 17:58.
 */
object LauncherRepository{
    fun getDayContent(subscriber: Subscriber<DayContent> ){
//        GankAPI.instance.contentDay()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(subscriber)
    }
}