package com.barryzhang.gankkotlin.ui.base

import android.app.Activity

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 12:21.
 */
interface BaseView<in T>{
    fun setPresenter(presenter : T)
    fun getActivityInstance(): Activity
}