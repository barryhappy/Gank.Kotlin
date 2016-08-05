package com.barryzhang.gankkotlin.entities

import com.orm.SugarRecord

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/1
 */
class FavoriteDao : SugarRecord() {
    var addTime: Long? = null
    var type: String? = null
    var data: GankItem? = null


    companion object{
        fun newInstance(data:GankItem) : FavoriteDao{
            val bean = FavoriteDao()
            bean.addTime = System.currentTimeMillis()
            bean.type = data.type
            bean.id = Math.abs(bean.hashCode()) as Long
            return bean
        }
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as FavoriteDao

        if (type != other.type) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int{
        var result = type?.hashCode() ?: 0
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }


}