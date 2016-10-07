package com.barryzhang.gankkotlin.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.barryzhang.gankkotlin.ui.wiget.LoadingDialog
import rx.Subscriber
import java.util.*

/**
* https://github.com/barryhappy
* Created by Barry on 16/7/10
*/

abstract class BaseActivity : AppCompatActivity(){

    val isDebug = true
    var mLoadingDialog : LoadingDialog? = null

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

    fun showLoadingDialog(){
        if(mLoadingDialog == null){
            mLoadingDialog = LoadingDialog.createDialog(this)
        }
        mLoadingDialog?.show()
    }

    fun hideLoadingDialog(){
        if(mLoadingDialog != null){
            mLoadingDialog?.dismiss()
        }
    }

    abstract fun getLayoutResourceID() : Int
    abstract fun afterInject()
    abstract fun start()
}
