package com.barryzhang.gankkotlin.ui.favorite

import com.barryzhang.gankkotlin.data.local.DatabaseService
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/3
 */
class FavoritePresenter : FavoriteContract.Presenter {

    val v: FavoriteContract.View

    constructor(view: FavoriteContract.View) {
        v = view
        v.setPresenter(this)
    }

    override fun getRemoteData() {
        Observable.just(DatabaseService.findAllFavorite())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list -> this.v.showList(list) }
    }

    override fun getTitle() {
        this.v.setTitle("收藏")
    }

    override fun start() {
        getTitle()
        getRemoteData()
    }

}