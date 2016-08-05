package com.barryzhang.gankkotlin.ui.favorite

import android.app.Activity
import android.widget.ListView
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.adapter.FavoriteAdapter
import com.barryzhang.gankkotlin.ui.base.BaseHomeFragment
import com.barryzhang.gankkotlin.ui.gankcontent.GankContentPresenter
import org.greenrobot.eventbus.EventBus

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/3
 */

class FavoriteFragment : BaseHomeFragment(), FavoriteContract.View {

    lateinit var p: FavoriteContract.Presenter

    @BindView(R.id.listViewFavorite)
    lateinit var listView: ListView

    val adapter: FavoriteAdapter by lazy { FavoriteAdapter(activity) }

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }

    override fun getLayoutResID(): Int = R.layout.content_favorite
    override fun getActivityInstance(): Activity = activity

    override fun afterInject() {
        listView.adapter = adapter
        this.p.start()
    }

    override fun showList(list: List<Any>) {
        adapter.clear()
        adapter.addAndNotify(list)
    }

    override fun setTitle(title: String?) {
        setActivityTitle(title)
    }

    override fun setPresenter(presenter: FavoriteContract.Presenter) {
        this.p = presenter
    }

    override fun onResume() {
        super.onResume()
        val stickyEvent = EventBus.getDefault().getStickyEvent(GankContentPresenter.OnFavoriteChangeEvent::class.java)
        if (stickyEvent != null){
            this.p.onFavoriteChange(stickyEvent)
            EventBus.getDefault().removeStickyEvent(GankContentPresenter.OnFavoriteChangeEvent::class.java)
        }
    }

}