package com.barryzhang.gankkotlin.ui.favorite

import com.barryzhang.gankkotlin.ui.base.BasePresenter
import com.barryzhang.gankkotlin.ui.base.BaseView
import com.barryzhang.gankkotlin.entities.DailyGankEntity
import com.barryzhang.gankkotlin.entities.GankDate
import com.barryzhang.gankkotlin.ui.gankcontent.GankContentPresenter

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/8/3
 */
interface FavoriteContract {

    interface View : BaseView<Presenter> {
        fun showList(list: List<Any>)
        fun setTitle(title: String?)
    }

    abstract class Presenter : BasePresenter() {
        abstract fun getRemoteData( )
        abstract fun getTitle( )
        abstract fun onFavoriteChange(e:GankContentPresenter.OnFavoriteChangeEvent)
    }

}