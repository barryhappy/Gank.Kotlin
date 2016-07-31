package com.barryzhang.gankkotlin.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.adapter.DailyGankAdapter
import com.barryzhang.gankkotlin.entities.GankDate
import com.barryzhang.gankkotlin.entities.History
import com.barryzhang.gankkotlin.ui.base.BaseHomeFragment

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15 11:29.
 */
class MainFragment: BaseHomeFragment(), MainContract.View {

    lateinit var p: MainContract.Presenter

    val adapter: DailyGankAdapter by lazy { DailyGankAdapter(activity) }
    val history: History by lazy { activity.intent.getSerializableExtra("history") as History }
    val lastDay: GankDate by lazy { GankDate(history.results?.first() ?: "") }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): com.barryzhang.gankkotlin.ui.MainFragment {
            return com.barryzhang.gankkotlin.ui.MainFragment()
        }
    }

    override fun getLayoutResID(): Int {
        return R.layout.content_main
    }

    override fun afterInject() {

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

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

    override fun onDestroy() {
        super.onDestroy()
        this.p.release()
    }
}