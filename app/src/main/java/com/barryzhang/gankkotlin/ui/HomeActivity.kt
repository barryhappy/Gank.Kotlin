package com.barryzhang.gankkotlin.ui

import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.MenuItem
import android.widget.FrameLayout
import com.barryzhang.gankkotlin.R
import com.barryzhang.gankkotlin.data.MainRepository
import com.barryzhang.gankkotlin.ui.base.BaseActivity
import com.barryzhang.gankkotlin.ui.base.BaseFragment
import com.barryzhang.gankkotlin.ui.favorite.FavoriteFragment
import com.barryzhang.gankkotlin.ui.favorite.FavoritePresenter
import com.barryzhang.gankkotlin.ui.history.HistoryFragment
import com.barryzhang.gankkotlin.ui.history.HistoryPresenter
import com.barryzhang.gankkotlin.ui.main.MainFragment
import com.barryzhang.gankkotlin.ui.main.MainPresenter

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/15
 */
class HomeActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {

    val mLayoutContent : FrameLayout by lazy { findViewById(R.id.mLayoutContent) as FrameLayout };
    val toolbar : Toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
    val fab : FloatingActionButton by lazy { findViewById(R.id.fab) as FloatingActionButton }
    val drawerLayout by lazy { findViewById(R.id.drawer_layout) as DrawerLayout }
    val navigationView by lazy{ findViewById(R.id.nav_view) as NavigationView }

    val fragmentList = SparseArray<BaseFragment<HomeActivity>>()
    val fragmentListStatus = SparseBooleanArray()

    override fun getLayoutResourceID(): Int { return R.layout.activity_base_home}

    override fun afterInject() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view -> Snackbar.make(view,"Hello, This is from SnackBar", Snackbar.LENGTH_LONG)
                .setAction("OK",null)
                .show()
        }
        val actionBarToggle =  ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(actionBarToggle)
        drawerLayout.closeDrawers()
        actionBarToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun start() {
        showFragment(0)
    }

    override fun onNavigationItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_home -> showFragment(0)
            R.id.nav_history -> showFragment(1)
            R.id.nav_favorite -> showFragment(2)
        }
        drawerLayout.closeDrawers()
        return true
    }

    fun showFragment(index : Int){
        if(fragmentList.get(index) == null){
            fragmentList.put(index,newFragmentByIndex(index))
        }

        val transaction = supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        for(i in 0..fragmentList.size()){
            val fragment = fragmentList.get(i) ?: continue
            if(i != index && fragment.isAdded){
                transaction.hide(fragment)
            }else if(fragment.isAdded){
                transaction.show(fragment)
            }else{
                transaction.add(R.id.mLayoutContent,fragment)
            }
        }
        transaction.commit()

        if(!fragmentListStatus.get(index)) {
            fragmentListStatus.put(index,true)
            when(index) {
                0 -> MainPresenter(MainRepository.instance, fragmentList.get(index) as MainFragment)
                1 ->  HistoryPresenter(fragmentList.get(index) as HistoryFragment)
                2 -> FavoritePresenter(fragmentList.get(index) as FavoriteFragment)
            }
        }
    }

    fun newFragmentByIndex(index : Int ) : BaseFragment<HomeActivity> = when(index){
        0 -> MainFragment.newInstance()
        1 -> HistoryFragment.newInstance()
        2-> FavoriteFragment.newInstance()
        else -> MainFragment.newInstance()
    }

    fun setPageTitle(title : String?){
        toolbar.title = title
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers()
        }else {
            super.onBackPressed()
        }

    }
}