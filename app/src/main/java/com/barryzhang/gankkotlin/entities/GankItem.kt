package com.barryzhang.gankkotlin.entities


import java.io.Serializable

/**
 * Created by Barry on 16/4/20.
 */
class GankItem : Serializable {
    private var id: Long? = null
    var _Id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var isUsed: Boolean = false
    var who: String? = null

    fun syncID() {
        id = Math.abs(hashCode()).toLong()
    }

    fun getId(): Long {
        return hashCode().toLong()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val gankItem = o as GankItem?

        if (if (desc != null) desc != gankItem!!.desc else gankItem!!.desc != null) return false
        if (if (type != null) type != gankItem.type else gankItem.type != null) return false
        if (if (url != null) url != gankItem.url else gankItem.url != null) return false
        return if (who != null) who == gankItem.who else gankItem.who == null

    }

    override fun hashCode(): Int {
        var result = if (desc != null) desc!!.hashCode() else 0
        result = 31 * result + if (type != null) type!!.hashCode() else 0
        result = 31 * result + if (url != null) url!!.hashCode() else 0
        result = 31 * result + if (who != null) who!!.hashCode() else 0
        return result
    }
}
