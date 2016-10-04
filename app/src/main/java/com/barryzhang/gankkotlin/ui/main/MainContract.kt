package com.barryzhang.gankkotlin.ui.main

import com.barryzhang.gankkotlin.ui.base.BasePresenter
import com.barryzhang.gankkotlin.ui.base.BaseView
import com.barryzhang.gankkotlin.entities.DailyGankEntity
import com.barryzhang.gankkotlin.entities.GankDate

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 12:18.
 */
interface MainContract{

    interface View : BaseView<Presenter> {
        fun showList(list: List<Any>)
        fun setTitle(title: String?)
        fun setDate(date: String?)
        fun showRetryDialog()
    }

    abstract class Presenter : BasePresenter(){
        abstract fun getRemoteData(day: GankDate)
        abstract fun getTitle(day: GankDate)
    }

}