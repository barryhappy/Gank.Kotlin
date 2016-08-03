package com.barryzhang.gankkotlin.adapter

import android.content.Context
import android.text.Html
import android.widget.TextView

import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.entities.GankItem
import com.barryzhang.gankkotlin.ui.wiget.PinnedSectionListView
import com.github.mzule.easyadapter.TypePerEntityAdapter
import com.github.mzule.easyadapter.ViewType

import butterknife.BindView
import butterknife.ButterKnife

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/3
 */
class FavoriteAdapter(context: Context) : TypePerEntityAdapter<Any>(context), PinnedSectionListView.PinnedSectionListAdapter {

    override fun mapEntityViewTypes() {

        mapEntityViewType(String::class.java, DailyGankAdapter.TitleViewType::class.java)
        mapEntityViewType(GankItem::class.java, FavoriteViewType::class.java)
    }


    class FavoriteViewType : ViewType<GankItem>() {
        @BindView(R.id.textViewDesc)
        lateinit var textViewDesc: TextView

        override fun onCreate() {
            setContentView(R.layout.lv_item_gank_item)
            ButterKnife.bind(this, view)
        }

        override fun onRender(position: Int, data: GankItem) {

            textViewDesc.text = Html.fromHtml(data.desc +
                    "<font color='#222'>（" + data.who + "）</font>")
        }
    }

    override fun isItemViewTypePinned(viewType: Int): Boolean {
        return getRawViewType(DailyGankAdapter.TitleViewType::class.java) == viewType
    }
}
