package com.barryzhang.gankkotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import rx.Subscriber
import java.util.*

/**
 * Created by Barry on 16/7/10.
 */

abstract class BaseActivity : AppCompatActivity(){

    val isDebug = true

    val subscriberList: ArrayList<Subscriber<*>> by lazy { ArrayList<Subscriber<*>>()}

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResourceID())
        ButterKnife.bind(this)

        afterInject()

        start()

    }

    override fun onDestroy() {
        subscriberList.forEach { it.unsubscribe() }
        super.onDestroy()
    }

    fun back(){
        onBackPressed()
    }

    abstract fun getLayoutResourceID() : Int
    abstract fun afterInject()
    abstract fun start()
}
