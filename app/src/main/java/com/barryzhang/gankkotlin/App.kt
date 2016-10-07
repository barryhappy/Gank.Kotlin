package com.barryzhang.gankkotlin

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import com.barryzhang.gankkotlin.ext.d

import com.facebook.drawee.backends.pipeline.Fresco
import com.orm.SugarApp
import com.squareup.leakcanary.LeakCanary
import java.lang.ref.WeakReference
import java.util.*

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/13 18:54.
 */
class App : SugarApp() {
    companion object{
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LeakCanary.install(this)
        Fresco.initialize(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity) {
                d("======= onActivityStarted : ${activity.localClassName}")
            }

            override fun onActivityDestroyed(activity: Activity) {
                d("======= onActivityDestroyed : ${activity.localClassName}")
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }

            override fun onActivityResumed(activity: Activity?) {
            }

        })
    }
}
