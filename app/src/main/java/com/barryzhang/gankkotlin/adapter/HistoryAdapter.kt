package com.barryzhang.gankkotlin.adapter

import android.content.Context
import android.text.Html
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.ui.wiget.PinnedSectionListView
import com.github.mzule.easyadapter.MultiTypeAdapter
import com.github.mzule.easyadapter.ViewType

/**
* Created by Barry on 16/7/21 .
*/
class HistoryAdapter constructor(context:Context) : MultiTypeAdapter<String>(context) ,
        PinnedSectionListView.PinnedSectionListAdapter{

    override fun registerViewTypes() {

        registerViewType(HistoryTitleViewType::class.java)
        registerViewType(HistoryItemViewType::class.java)
    }

    override fun getViewType(position: Int, data: String): Class<out ViewType<*>> {
        return if (data.length > 7) HistoryItemViewType::class.java else HistoryTitleViewType::class.java
    }

    override fun isItemViewTypePinned(viewType: Int): Boolean {
        return getRawViewType(HistoryTitleViewType::class.java) == viewType
    }

    class HistoryTitleViewType : ViewType<String>() {

        @BindView(R.id.textViewTitle)
        lateinit var textViewTitle: TextView

        override fun onCreate() {
            setContentView(R.layout.lv_item_title)
            ButterKnife.bind(this, view)
        }

        override fun onRender(position: Int, data: String) {
            textViewTitle.text = data
        }
    }

    class HistoryItemViewType : ViewType<String>() {
        @BindView(R.id.textViewDesc)
        lateinit var textViewDesc: TextView

        override fun onCreate() {
            setContentView(R.layout.lv_item_gank_item)
            ButterKnife.bind(this, view)
        }

        override fun onRender(position: Int, data: String) {
            textViewDesc.text = Html.fromHtml("<font color='#1a1a1a'>$data</font>")
        }
    }

}