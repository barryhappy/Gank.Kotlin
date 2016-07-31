package com.barryzhang.gankkotlin.ui.wiget

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import com.barryzhang.gankkotlin.App
import com.barryzhang.gankkotlin.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/31
 */

class LoadingDialog(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        var mDialog: LoadingDialog? = null
        fun createDialog(context: Context): LoadingDialog {
            val d = LoadingDialog(context, R.style.myprogressdialog)
            d.setContentView(R.layout.layout_progress)
            d.window?.attributes?.gravity = Gravity.CENTER
            d.setCancelable(true)
            return d
        }

        fun showDialog(act : Activity) {
            showDialog(0L,act)
        }

        fun showDialog(delay: Long, act : Activity) {
            Observable.timer(delay, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if(mDialog?.context != act){
                            mDialog?.dismiss()
                            mDialog = null
                            mDialog = createDialog(act)
                        }
                        mDialog?.show()
                    })
        }

        fun hideDialog() {
            mDialog?.dismiss()
        }
    }

}
