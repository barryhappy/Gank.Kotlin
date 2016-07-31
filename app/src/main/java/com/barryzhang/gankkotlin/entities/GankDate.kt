package com.barryzhang.gankkotlin.entities

import com.barryzhang.gankkotlin.ext.concat
import java.util.*

/**
 * barryhappy2010@gmail.com
 * https://github.com/barryhappy
 * http://www.barryzhang.com/
 * Created by Barry on 16/7/12 16:20.
 */
class GankDate {

    var year: String? = null
    var month: String? = null
    var day: String? = null


    companion object {
        fun newInstance() : GankDate {
            return GankDate()
        }

        fun newInstance(des: String) : GankDate?{
            try {
                return GankDate(des)
            }catch (ex : Exception){
                return null
            }
        }

    }


    constructor(des: String = "")  {
        val yyyyMMdd :String
        if(des.contains("T")){
            yyyyMMdd = des.split("T")[0]
        }else{
            yyyyMMdd = des
        }
        val spilt = yyyyMMdd.split("-")
        year = spilt[0]
        month = spilt[1]
        day = spilt[2]

    }

    constructor(){
        val today = Calendar.getInstance()
        year = today.get(Calendar.YEAR).toString()
        month = (today.get(Calendar.MONTH) + 1 ).toString()//思考题:这里为什么要+1? O(∩_∩)O
        day = today.get(Calendar.DAY_OF_MONTH).toString()
    }

    fun toYMD() :String{
        return "${year}年${month}月${day}日"
    }

    fun toMD() :String{
        return "${month}月${day}日"
    }

}


fun main(args: Array<String>) {
    var date1 = GankDate();

    println(date1)

    var ss = "2016-07-12T03:03:40s"

    val split = ss.split("T")
    println(split)

    val date2 = GankDate(ss)
    println(date2)
}