package com.barryzhang.gankkotlin.ui.base

import rx.Subscriber
import java.util.*

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 12:24.
 */
abstract class BasePresenter{
    val subscriberList: MutableList<Subscriber<*>> by lazy { ArrayList<Subscriber<*>>() }
    abstract fun start()
    open fun release(){
        subscriberList.forEach { it.unsubscribe() }
    }
}
