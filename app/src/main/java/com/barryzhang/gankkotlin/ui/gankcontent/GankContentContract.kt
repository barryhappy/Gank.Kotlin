package com.barryzhang.gankkotlin.ui.gankcontent

import com.barryzhang.gankkotlin.ui.base.BasePresenter
import com.barryzhang.gankkotlin.ui.base.BaseView
import com.barryzhang.gankkotlin.entities.GankItem

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/30
 */

interface GankContentContract {


    interface View : BaseView<Presenter> {
        fun init(gank: GankItem)
        fun loadGankUrl(url : String?)
        fun onFavoriteChanged(isFavorite: Boolean)
        fun showVideoTipCover()
        fun getGankData(): GankItem
    }

    abstract class Presenter : BasePresenter() {
        abstract fun isFavorite () : Boolean
        abstract fun afterViewInit()
        abstract fun onFavoriteClicked()
    }

}