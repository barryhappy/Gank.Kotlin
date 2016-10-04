package com.barryzhang.gankkotlin.ui.main

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.adapter.DailyGankAdapter
import com.barryzhang.gankkotlin.entities.GankDate
import com.barryzhang.gankkotlin.entities.History
import com.barryzhang.gankkotlin.ext.showActionDialog
import com.barryzhang.gankkotlin.ui.base.BaseHomeFragment
import com.barryzhang.temptyview.TEmptyView
import com.barryzhang.temptyview.TViewUtil
import java.util.*

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 11:29.
 */
class MainFragment : BaseHomeFragment(), MainContract.View {

    lateinit var p: MainContract.Presenter

    val adapter: DailyGankAdapter by lazy { DailyGankAdapter(activity) }
    val history: History by lazy { activity.intent.getSerializableExtra("history") as History }
    val lastDay: GankDate by lazy { GankDate(history.results?.first() ?: "") }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): com.barryzhang.gankkotlin.ui.main.MainFragment {
            return com.barryzhang.gankkotlin.ui.main.MainFragment()
        }
    }

    override fun getLayoutResID(): Int {
        return R.layout.content_main
    }

    override fun afterInject() {

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        TViewUtil.EmptyViewBuilder
                .getInstance(context)
                .setEmptyText("/(ㄒoㄒ)/")
                .setShowText(true)
                .bindView(recyclerView)
        p.start()
        p.getRemoteData(lastDay)
        recyclerView.post { p.getTitle(lastDay) }
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.p = presenter
    }

    override fun showList(list: List<Any>) {
        adapter.clear()
        adapter.addAndNotify(list)
    }

    override fun setTitle(title: String?) {
        setActivityTitle(title)
    }

    override fun setDate(date: String?) {
        mParent.setPageTitle(date)
    }

    override fun showRetryDialog() {
        activity.showActionDialog("加载失败，是否再试一下？",
                DialogInterface.OnClickListener {
                    dialogInterface, i ->
                    p.getRemoteData(lastDay)
                })

    }

    override fun getActivityInstance(): Activity = mParent

    override fun onDestroy() {
        super.onDestroy()
        this.p.release()
    }
}