package com.barryzhang.gankkotlin.ui.gankcontent

import com.barryzhang.gankkotlin.entities.GankItem

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/30
 */
class GankContentPresenter : GankContentContract.Presenter {
    val v: GankContentContract.View
    var isNowFavorite: Boolean = false
    lateinit var gank : GankItem

    constructor(view: GankContentContract.View) {
        this.v = view
        v.setPresenter(this)
    }

    override fun start() {
        gank = v.getGankData()
        this.v.init(gank)
    }

    override fun isFavorite(): Boolean = isNowFavorite

    override fun afterViewInit() {
        if ("休息视频" == gank.type){
            this.v.showVideoTipCover()
        }else{
            this.v.loadGankUrl(gank.url)

        }
    }

}