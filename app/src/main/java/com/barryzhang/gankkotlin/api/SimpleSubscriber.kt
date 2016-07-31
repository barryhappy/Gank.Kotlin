package com.barryzhang.gankkotlin.api

import rx.Subscriber

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/19 22:37.
 */

abstract class SimpleSubscriber<T> : Subscriber<T>(){

    override fun onStart() {
        if(show()){
            // TODO 显示进度框
        }

    }
    override fun onCompleted() {
        // TODO 隐藏进度框
        unsubscribe()
    }

    override fun onError(e: Throwable?) {
        // TODO 隐藏进度框
        onException(e)
        unsubscribe()
    }

    fun show() : Boolean{
        return shouldShowProgressbar() || getProgressTip() != null
    }

    open fun shouldShowProgressbar() : Boolean{
        return true
    }

    open fun getProgressTip() :String ? {
        return null
    }

    open fun onException(e : Throwable?){

    }

}