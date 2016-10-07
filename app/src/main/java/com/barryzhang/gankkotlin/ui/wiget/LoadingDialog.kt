package com.barryzhang.gankkotlin.ui.wiget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import com.barryzhang.gankkotlin.R

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/31
 */

class LoadingDialog(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        fun createDialog(context: Context): LoadingDialog {
            val d = LoadingDialog(context, R.style.myprogressdialog)
            d.setContentView(R.layout.layout_progress)
            d.window?.attributes?.gravity = Gravity.CENTER
            d.setCancelable(true)
            return d
        }
    }

}
