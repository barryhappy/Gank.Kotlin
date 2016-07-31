package com.barryzhang.gankkotlin.data.local

import com.barryzhang.gankkotlin.utils.UtilShare

/**
 * Created by Barry on 16/7/22 00:16.
 */
object ShareService{
    fun saveHistory(history: List<String>?){
        UtilShare.saveData("_history",history)
    }
    fun getHistory():List<String>{
        return UtilShare.getDataStringList("_history")
    }
}