package com.barryzhang.gankkotlin.ext

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AlertDialog
import android.widget.Toast

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/31
 */


fun Activity.toast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(this.activity, msg, Toast.LENGTH_SHORT).show()
}

fun Context.toast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.getResColor(colorId: Int) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) this.getColor(colorId)
        else this.resources.getColor(colorId)

fun Activity.showActionDialog(msg: String?,listener : DialogInterface.OnClickListener) {
    AlertDialog.Builder(this).setTitle(null)
            .setMessage(msg)
            .setPositiveButton("确定", listener)
            .setNegativeButton("取消", null)
            .setCancelable(false)
            .create()
            .show()
}
