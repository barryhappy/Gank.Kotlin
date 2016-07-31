package com.barryzhang.gankkotlin.ui.base

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.ui.base.BaseActivity

/**
 * Created by Barry on 16/7/10.
 */
abstract class BaseHomeActivity : BaseActivity(){

    var mLayoutContent : FrameLayout? = null;
    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_base_home);
        mLayoutContent = findViewById(R.id.mLayoutContent) as FrameLayout?;
        layoutInflater.inflate(getLayoutResourceID(),mLayoutContent);
    }

    override fun afterInject() {
        var toolbar = findViewById(R.id.toolbar) as Toolbar;
        var fab = findViewById(R.id.fab) as FloatingActionButton;
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }}
}