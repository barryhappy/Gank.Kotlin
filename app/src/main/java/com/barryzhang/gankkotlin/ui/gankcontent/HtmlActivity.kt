package com.barryzhang.gankkotlin.ui.gankcontent

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.entities.GankItem
import com.barryzhang.gankkotlin.ext.toast
import com.barryzhang.gankkotlin.ui.base.BaseActivity
import com.barryzhang.gankkotlin.ui.base.BaseAppBarActivity
import com.barryzhang.gankkotlin.utils.DrawableUtil
import net.steamcrafted.materialiconlib.MaterialDrawableBuilder

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/7/28
 */

class HtmlActivity : BaseAppBarActivity(), GankContentContract.View {
    lateinit var p: GankContentContract.Presenter
    override fun setPresenter(presenter: GankContentContract.Presenter) {
        this.p = presenter
    }

    @BindView(R.id.webView)
    lateinit var webView: WebView
    @BindView(R.id.viewCover)
    lateinit var viewCover: View

    lateinit var mOptionsMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        GankContentPresenter(this)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResourceID(): Int = R.layout.content_html

    override fun getActivityInstance(): Activity = this

    override fun afterInject() {

    }

    override fun start() {
        this.p.start()
    }

    override fun init(gank: GankItem) {
        initToolbar(gank)
        initWebView()
        fab.post { refreshFavoriteUI(this.p.isFavorite()) }
        fab.setOnClickListener { this.p.onFavoriteClicked() }
        this.p.afterViewInit()
    }

    override fun loadGankUrl(url: String?) {
        showLoadingDialog()
        webView.loadUrl(url)
    }

    override fun onFavoriteChanged(isFavorite: Boolean) {
        toast(if (isFavorite) "√ 已收藏" else "√ 收藏已移除")
        refreshFavoriteUI(isFavorite)
    }

    private fun refreshFavoriteUI(isFavorite: Boolean) {
        refreshMenu(isFavorite)

        fab.setImageDrawable(DrawableUtil.buildMaterialDrawable(
                MaterialDrawableBuilder.with(this)
                        .setIcon(if (isFavorite) MaterialDrawableBuilder.IconValue.STAR
                        else MaterialDrawableBuilder.IconValue.STAR_OUTLINE)
                        .setColor(Color.WHITE)
                        .setSizeDp(20)))
    }

    override fun showVideoTipCover() {
        fab.post({
            val snackBar = Snackbar.make(fab,
                    "如需播放视频，选择『在浏览器中打开』，进行播放",
                    Snackbar.LENGTH_INDEFINITE).setAction("我知道啦", {
                viewCover.visibility = View.GONE
            })
                    .setActionTextColor(Color.parseColor("#cccccc"))
            snackBar.view.setBackgroundColor(Color.parseColor("#ff4081"))
            snackBar.show()
        })
        viewCover.visibility = View.VISIBLE
    }

    override fun getGankData(): GankItem {
        return intent.getSerializableExtra("gankItem") as GankItem
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.html, menu)
        mOptionsMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.p.onOptionsItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    fun initToolbar(gank: GankItem) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = gank.desc
        toolbar.setNavigationOnClickListener { back() }
    }

    fun initWebView() {
        webView.settings.setAppCachePath(filesDir.path + "/cache")
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.setAppCacheEnabled(true)
        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                hideLoadingDialog()
            }
        })
    }

    private fun refreshMenu(isFavorite: Boolean) {
        mOptionsMenu.getItem(0).isVisible = !isFavorite
        mOptionsMenu.getItem(1).isVisible = isFavorite
    }

}