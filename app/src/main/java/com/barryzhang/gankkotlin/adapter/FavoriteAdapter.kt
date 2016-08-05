package com.barryzhang.gankkotlin.adapter

import android.app.Activity
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
import butterknife.OnClick
import com.barryzhang.gankkotlin.ext.startPage
import com.barryzhang.gankkotlin.ui.gankcontent.HtmlActivity

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/3
 */
class FavoriteAdapter(context: Context) : TypePerEntityAdapter<Any>(context) {

    override fun mapEntityViewTypes() {

        mapEntityViewType(String::class.java, DailyGankAdapter.TitleViewType::class.java)
        mapEntityViewType(GankItem::class.java, FavoriteViewType::class.java)
    }


    class FavoriteViewType : ViewType<GankItem>() {
        @BindView(R.id.textViewDesc)
        lateinit var textViewDesc: TextView
        lateinit var data: GankItem

        override fun onCreate() {
            setContentView(R.layout.lv_item_gank_item)
            ButterKnife.bind(this, view)
        }

        override fun onRender(position: Int, data: GankItem) {
            this.data = data
            textViewDesc.text = Html.fromHtml(data.desc +
                    "<font color='#222'>（" + data.who + "）</font>")
        }

        @OnClick(R.id.textViewDesc)
        fun onContentClick(){
            if(context is Activity){
                (context as Activity) .startPage<HtmlActivity>("gankItem" to data)
            }
        }
    }
}
