package com.barryzhang.gankkotlin.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.CardView
import android.text.Html
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.entities.BeautyData
import com.barryzhang.gankkotlin.entities.GankItem
import com.barryzhang.gankkotlin.ext.startPage
import com.barryzhang.gankkotlin.ui.gankcontent.HtmlActivity
import com.barryzhang.gankkotlin.utils.loadImageWithWidth
import com.barryzhang.temptyview.TViewUtil
import com.facebook.drawee.view.SimpleDraweeView
import com.github.mzule.easyadapter.ViewType
import com.github.mzule.easyadapter.recycler.TypePerEntityAdapter

class DailyGankAdapter(context: Context) : TypePerEntityAdapter<Any>(context) {

    override fun mapEntityViewTypes() {
        mapEntityViewType(String::class.java, TitleViewType::class.java)
        mapEntityViewType(GankItem::class.java, GankItemViewType::class.java)
        mapEntityViewType(BeautyData::class.java, BeautyViewType::class.java)
    }

    class TitleViewType : ViewType<String>() {

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

    class GankItemViewType : ViewType<GankItem>() {
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

        @OnClick(R.id.rootView)
        internal fun onItemClick() {
            if (context is Activity){
                (context as Activity).startPage<HtmlActivity>("gankItem" to data)
            }
        }
    }


    class BeautyViewType : ViewType<BeautyData>() {
        @BindView(R.id.imageViewBeauty)
        lateinit var imageViewBeauty: SimpleDraweeView
        @BindView(R.id.textViewDes)
        lateinit var textViewDes: TextView
        @BindView(R.id.cardView)
        lateinit var cardView : CardView

        lateinit var data:BeautyData

        override fun onCreate() {
            setContentView(R.layout.lv_item_beautiful)
            ButterKnife.bind(this, view)
        }

        override fun onRender(position: Int, data: BeautyData) {
            this.data = data
            textViewDes.text = data.beauty?.desc
            loadImageWithWidth(imageViewBeauty,
                    data.beauty?.url,
                    TViewUtil.sp2px(context, 316f))// 猜猜看这个数字怎么来的？o(╯□╰)o
        }

        @OnClick(R.id.imageViewBeauty)
        fun onImageClick(){
//            Observable.create<Unit> { DrawableUtil.save(context,Uri.parse(data.beauty?.url)) }
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .observeOn(Schedulers.io())
//                    .subscribe { context.toast("保存到相册啦~") }

        }
    }
}
