package com.barryzhang.gankkotlin.ui.history

import android.app.Activity
import android.widget.ListView
import butterknife.BindView
import butterknife.OnItemClick
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.adapter.HistoryAdapter
import com.barryzhang.gankkotlin.ui.base.BaseHomeFragment

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/21
 */
class HistoryFragment : BaseHomeFragment(), HistoryContract.View {

    lateinit var presenter: HistoryContract.Presenter

    val adapter by lazy { HistoryAdapter(activity) }

    @BindView(R.id.listViewHistory)
    lateinit var listView: ListView

    companion object {
        fun newInstance(): com.barryzhang.gankkotlin.ui.history.HistoryFragment {
            return com.barryzhang.gankkotlin.ui.history.HistoryFragment()
        }
    }

    override fun getLayoutResID(): Int {
        return R.layout.content_history
    }

    override fun afterInject() {
        listView.adapter = adapter

        presenter.start()

        setActivityTitle("历史的车轮滚滚向前")
    }

    override fun setPresenter(presenter: HistoryPresenter) {
        this.presenter = presenter
    }

    override fun getActivityInstance(): Activity = mParent

    override fun showList(list: List<String>) {
        adapter.addAndNotify(list)
    }

    @OnItemClick(R.id.listViewHistory)
    fun onHistoryListClick(position: Int) {
        mParent.showFragment(0)
        this.presenter.onDateSelect(adapter.getItem(position))
    }

}