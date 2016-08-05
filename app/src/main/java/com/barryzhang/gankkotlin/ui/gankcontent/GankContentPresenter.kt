package com.barryzhang.gankkotlin.ui.gankcontent

import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.data.local.DatabaseService
import com.barryzhang.gankkotlin.entities.GankItem
import com.barryzhang.gankkotlin.ext.postStickyBusEvent
import com.barryzhang.gankkotlin.utils.openUrlWithBrowser
import com.barryzhang.gankkotlin.utils.share

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/30
 */
class GankContentPresenter : GankContentContract.Presenter {
    val v: GankContentContract.View
    var isNowFavorite: Boolean = false
    lateinit var gank: GankItem

    constructor(view: GankContentContract.View) {
        this.v = view
        v.setPresenter(this)
    }

    override fun start() {
        gank = v.getGankData()
        isNowFavorite = DatabaseService.isFavorite(gank)
        this.v.init(gank)
    }

    override fun isFavorite(): Boolean = isNowFavorite

    override fun afterViewInit() {
        if ("休息视频" == gank.type) {
            this.v.showVideoTipCover()
        } else {
            this.v.loadGankUrl(gank.url)
        }
    }

    override fun onFavoriteClicked() {
        isNowFavorite = !isNowFavorite
        if (isNowFavorite) {
            DatabaseService.saveFavorite(gank)
        } else {
            DatabaseService.deleteFavorite(gank)
        }
        this.v.onFavoriteChanged(isNowFavorite)
        postStickyBusEvent(OnFavoriteChangeEvent(gank, isNowFavorite))
    }

    override fun onOptionsItemSelected(menuItem: Int) {
        when (menuItem) {
            R.id.action_open_in_browser -> openUrlWithBrowser(this.v.getActivityInstance(), gank.url)
            R.id.action_favorite, R.id.action_unFavorite -> onFavoriteClicked()
            R.id.action_share -> share("${gank.desc} [${gank.url}] by ${gank.who}")
        }
    }

    data class OnFavoriteChangeEvent(val gankItem: GankItem,
                                     val isFavorite: Boolean) {
    }
}