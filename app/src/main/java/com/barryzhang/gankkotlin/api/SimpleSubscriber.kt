package com.barryzhang.gankkotlin.api

import android.app.Activity
import android.widget.Toast
import com.barryzhang.gankkotlin.ext.toast
import com.barryzhang.gankkotlin.ui.wiget.LoadingDialog
import rx.Subscriber

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/19 22:37.
 */

abstract class SimpleSubscriber<T> : Subscriber<T> {

    val act: Activity

    constructor(act: Activity) : super() {
        this.act = act
    }

    override fun onStart() {
        if (show()) {
            LoadingDialog.showDialog(act)
        }
    }

    override fun onCompleted() {
        LoadingDialog.hideDialog()
        this.unsubscribe()
    }

    override fun onError(e: Throwable) {
        onException(e)
        LoadingDialog.hideDialog()
        act.toast("异常\n: ${e.message}")
        this.unsubscribe()
    }

    fun show(): Boolean = shouldShowProgressbar() || getProgressTip() != null

    open fun shouldShowProgressbar(): Boolean = true

    open fun getProgressTip(): String ? = null

    open fun onException(e: Throwable?) {

    }

}