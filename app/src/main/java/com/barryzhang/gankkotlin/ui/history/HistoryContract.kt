package com.barryzhang.gankkotlin.ui.history

import com.barryzhang.gankkotlin.ui.base.BasePresenter
import com.barryzhang.gankkotlin.ui.base.BaseView

/**
 * Created by Barry on 16/7/21 22:58.
 */
interface HistoryContract{

    interface View : BaseView<HistoryPresenter> {
        fun showList(list : List<String>)
    }

    abstract class Presenter : BasePresenter() {
        abstract fun onDateSelect(newDate: String)
    }

}