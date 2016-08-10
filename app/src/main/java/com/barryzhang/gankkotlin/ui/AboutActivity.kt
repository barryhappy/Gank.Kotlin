package com.barryzhang.gankkotlin.ui

import android.support.design.widget.FloatingActionButton
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.ui.base.BaseActivity
import us.feras.mdv.MarkdownView

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/8
 */

class AboutActivity : BaseActivity(){
    override fun getLayoutResourceID() = R.layout.activity_about

    @BindView(R.id.markDownView)
    lateinit var markdownView: MarkdownView
    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    override fun afterInject() {

    }

    override fun start() {

        markdownView.loadMarkdownFile("file:///android_asset/about.md")
    }

}