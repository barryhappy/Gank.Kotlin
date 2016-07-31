package com.barryzhang.gankkotlin.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/14 15:19.
 */

inline fun <reified T : Activity> Activity.startPage(vararg extras: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    fillIntentArguments(intent, extras )
    this.startActivity(intent)
}

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any>>) {
    params.forEach {
        val (key,value) = it
        when (value) {
            is Int -> intent.putExtra(key, value)
            is Long -> intent.putExtra(key, value)
            is CharSequence -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Float -> intent.putExtra(key, value)
            is Double -> intent.putExtra(key, value)
            is Char -> intent.putExtra(key, value)
            is Short -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key, value)
            is Serializable -> intent.putExtra(key, value)
            is Bundle -> intent.putExtra(key, value)
            is Parcelable -> intent.putExtra(key, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(key, value)
                value.isArrayOf<String>() -> intent.putExtra(key, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(key, value)
                else -> throw RuntimeException("Intent extra $key has wrong type ${value.javaClass.name}")
            }
            is IntArray -> intent.putExtra(key, value)
            is LongArray -> intent.putExtra(key, value)
            is FloatArray -> intent.putExtra(key, value)
            is DoubleArray -> intent.putExtra(key, value)
            is CharArray -> intent.putExtra(key, value)
            is ShortArray -> intent.putExtra(key, value)
            is BooleanArray -> intent.putExtra(key, value)
            else -> throw RuntimeException("Intent extra $key has wrong type ${value.javaClass.name}")
        }
    }
}

inline fun filterIntentSupportParams(intent: Intent,
                                     extras: Array<out Pair<String, Any>>) {
    extras.forEach {
        val (key, value) = it
        when (value) {
            is Int -> intent.putExtra(key, value)
            is String -> intent.putExtra(key, value)
            is Boolean -> intent.putExtra(key,value)
            is Double -> intent.putExtra(key,value)
            is Float -> intent.putExtra(key,value)
            is Char -> intent.putExtra(key,value)
            is Bundle -> intent.putExtra(key,value)
            is Parcelable -> intent.putExtra(key,value)
            is Intent -> intent.putExtra(key,value)
            is Serializable -> intent.putExtra(key,value)
            else -> {

            }
        }
    }
}


fun checkValueType(value: Any): Boolean {
    return value is String ||
            value is Int ||
            value is Boolean ||
            value is Double ||
            value is Float ||
            value is Char ||
            value is Bundle ||
            value is Parcelable ||
            value is Intent ||
            value is Serializable
}

