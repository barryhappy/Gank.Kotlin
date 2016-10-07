package com.barryzhang.gankkotlin.data.remote

import android.app.Activity
import android.widget.Toast
import com.barryzhang.gankkotlin.ext.toast
import com.barryzhang.gankkotlin.ui.base.BaseActivity
import com.barryzhang.gankkotlin.ui.wiget.LoadingDialog
import rx.Subscriber

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/19 22:37.
 */

abstract class SimpleSubscriber<T> : Subscriber<T> {

    var act: BaseActivity? = null
    var dialog : LoadingDialog? = null

    constructor(act: Activity) : super() {
        if(act is BaseActivity) {
            this.act = act
        }
    }

    override fun onStart() {
        if (show()) {
            act?.showLoadingDialog()
        }
    }

    override fun onCompleted() {
        act?.hideLoadingDialog()
        this.unsubscribe()
    }

    override fun onError(e: Throwable) {
        onException(e)
        act?.hideLoadingDialog()
        act?.toast("异常\n: ${e.message}")
        this.unsubscribe()
    }

    fun show(): Boolean = shouldShowProgressbar() || getProgressTip() != null

    open fun shouldShowProgressbar(): Boolean = true

    open fun getProgressTip(): String ? = null

    open fun onException(e: Throwable?) {

    }

}