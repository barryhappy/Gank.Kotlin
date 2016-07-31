package com.barryzhang.gankkotlin

import android.app.Application
import android.content.Context

import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/13 18:54.
 */
class App : Application() {
    companion object{
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LeakCanary.install(this)
        Fresco.initialize(this)
    }
}
