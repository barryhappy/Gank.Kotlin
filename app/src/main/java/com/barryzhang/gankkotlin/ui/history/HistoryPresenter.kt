package com.barryzhang.gankkotlin.ui.history

import com.barryzhang.gankkotlin.data.local.ShareService
import com.barryzhang.gankkotlin.entities.GankDate
import com.barryzhang.gankkotlin.ext.postBusEvent
import java.util.*

/**
* https://github.com/barryhappy
* Created by Barry on 16/7/21 23:00
*/
class HistoryPresenter : HistoryContract.Presenter {
    val v: HistoryContract.View

    constructor(view: HistoryContract.View) {
        v = view
        v.setPresenter(this)
    }

    override fun start() {
        v.showList(getHistory())
    }

    fun getHistory(): List<String> {
        val newList = ArrayList<String>()
        var month :String? =null
        ShareService.getHistory().forEach {
            val currentMonth = it.substring(0,7)
            if(!currentMonth.equals(month) ){
                month = currentMonth
                newList.add(currentMonth)
            }
            newList.add(it)
        }
        return newList
    }

    override fun onDateSelect(newDate: String) {
        if(newDate.length <= 7){
            return
        }
        postBusEvent(GankDate(des = newDate))

    }

    override fun release() {
        super.release()
    }

}