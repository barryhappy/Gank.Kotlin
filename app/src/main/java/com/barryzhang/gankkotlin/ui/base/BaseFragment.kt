package com.barryzhang.gankkotlin.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import rx.Subscriber
import java.util.*


/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 11:39.
 */
abstract class BaseFragment< T: FragmentActivity>  : Fragment(){
    lateinit var mParent: T
    val subscriberList: ArrayList<Subscriber<*>> by lazy { ArrayList<Subscriber<*>>() }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(getLayoutResID(),container,false)
        ButterKnife.bind(this,rootView)
        afterInject()
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParent = activity as T
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriberList.forEach { it.unsubscribe() }
    }


    abstract fun getLayoutResID() : Int

    abstract fun afterInject()


}