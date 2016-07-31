package com.barryzhang.gankkotlin.ui.base

import com.barryzhang.gankkotlin.ui.HomeActivity

/**
* https://github.com/barryhappy
* Created by Barry on 16/7/23
*/
abstract class BaseHomeFragment : BaseFragment<HomeActivity>(){

    var mActivityTitle : String? = null

    fun setActivityTitle(title:String?){
        mActivityTitle = title
        mParent.setPageTitle(title)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden && mActivityTitle != null){
            mParent.setPageTitle(mActivityTitle)
        }
    }

}
