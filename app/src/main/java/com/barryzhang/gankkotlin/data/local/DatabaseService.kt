package com.barryzhang.gankkotlin.data.local

import com.barryzhang.gankkotlin.entities.GankItem
import com.orm.SugarRecord

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/1
 */
class DatabaseService {
    companion object {
        fun saveFavorite(bean: GankItem): Long = bean.save()

        fun deleteFavorite(bean: GankItem): Boolean = bean.delete()

        fun findAllFavorite(): List<GankItem> {
            return SugarRecord.listAll(GankItem::class.java)
        }

        fun isFavorite(bean: GankItem): Boolean {
            return SugarRecord.count<GankItem>(
                    GankItem::class.java,
                    "_id = ?",
                    arrayOf(bean._id.toString())) > 0
        }

    }


}