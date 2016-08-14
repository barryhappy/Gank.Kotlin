package com.barryzhang.gankkotlin.ui

import android.webkit.WebView
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.ui.base.BaseAppBarActivity

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/8
 */

class AboutActivity : BaseAppBarActivity(){
    override fun getLayoutResourceID() = R.layout.content_about

    @BindView(R.id.webView)
    lateinit var webView: WebView

    override fun afterInject() {

    }

    override fun start() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { back() }
        title = "关于"

        webView.loadUrl("file:///android_asset/about.html" )
    }

}