package com.barryzhang.gankkotlin.ui

import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.data.remote.GankAPI
import com.barryzhang.gankkotlin.data.local.ShareService
import com.barryzhang.gankkotlin.entities.History
import com.barryzhang.gankkotlin.ext.startPage
import com.barryzhang.gankkotlin.ui.HomeActivity
import com.barryzhang.gankkotlin.ui.base.BaseActivity
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.Gson
import rx.Observable
import rx.Subscriber
import java.util.concurrent.TimeUnit

class LauncherActivity : BaseActivity() {

    @BindView(R.id.imageViewLauncher)
    lateinit var imageViewLauncher: SimpleDraweeView;

    override fun getLayoutResourceID(): Int {
        return R.layout.activity_launcher
    }

    override fun afterInject() {

    }

    override fun start() {
        startLoadingImage()
        startGetHistory()
    }

    fun startLoadingImage() {

    }

    fun startGetHistory() {


        val subscriber: Subscriber<History> = object : Subscriber<History>() {
            override fun onNext(history: History) {
                print(history.toString())
                ShareService.saveHistory(history.results)
                startPage<HomeActivity>( "history" to history )
                finish()
            }

            override fun onCompleted() {

            }

            override fun onError(e: Throwable?) {
            }

        }
        GankAPI.instance.makeSubscribe(
                Observable.zip(Observable.timer(3, TimeUnit.SECONDS),
                        if(isDebug) getHistoryInDebugMode() else GankAPI.instance.getHistory(),
                        { time, history -> history }),
                subscriber)
        subscriberList.add(subscriber)

    }

    fun getHistoryInDebugMode(): Observable<History>{

        val test =
                """
                {
                    "error": false,
                    "results": ["2016-07-18","2016-07-15","2016-07-14","2016-07-13","2016-07-12",
                        "2016-07-11","2016-07-08","2016-07-07","2016-07-06","2016-07-05",
                        "2016-07-04","2016-07-01","2016-06-30","2016-06-29","2016-06-28",
                        "2016-06-27","2016-06-24","2016-06-23","2016-06-22","2016-06-21",
                        "2016-06-20","2016-06-17","2016-06-16","2016-06-15","2016-06-14","2016-06-13"]
                }
                """

        return Observable.just(Gson().fromJson(test,History::class.java))
    }
}
