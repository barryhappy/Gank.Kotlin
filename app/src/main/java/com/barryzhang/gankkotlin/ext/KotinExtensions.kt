package com.barryzhang.gankkotlin.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.barryzhang.gankkotlin.entities.BaseHttpResponseEntity
import com.barryzhang.gankkotlin.entities.History
import com.google.gson.Gson

/**
 * Created by Barry on 16/7/8.
 */

fun <T : Activity> T.e2(msg: String): Unit {
    Log.e(this.javaClass.name, msg);
}

fun <T : Activity> T.w(msg: String): Unit {
    Log.w(this.javaClass.name, msg);
}

fun <T : Activity> T.d(msg: String): Unit {
    Log.d(this.javaClass.name, msg);
}

fun <T : Activity> T.i(msg: String): Unit {
    Log.i(this.javaClass.name, msg);
}

fun <T : Activity> T.v(msg: String): Unit {
    Log.v(this.javaClass.name, msg);
}

fun <T : Context> T.getColor(colorId : Int) :Int{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        this.getColor(colorId)  else{
        this.resources.getColor(colorId)
    }
}

fun <T : Activity> T.gotoActivity(nextActivity : Class<*>){
    val intent = Intent(this,nextActivity)
    startActivity(intent)
}

