package com.barryzhang.gankkotlin.ext

import android.app.Activity
import android.app.Fragment
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


