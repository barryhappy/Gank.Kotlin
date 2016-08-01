package com.barryzhang.gankkotlin.entities


import com.orm.SugarRecord
import com.orm.dsl.Table
import com.orm.dsl.Unique
import java.io.Serializable

/**
 * Created by Barry on 16/4/20.
 */
class GankItem : SugarRecord(), Serializable {

    @Unique
    var _id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var isUsed: Boolean = false
    var who: String? = null

    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GankItem

        if (_id != other._id) return false
        if (desc != other.desc) return false
        if (source != other.source) return false
        if (type != other.type) return false
        if (url != other.url) return false
        if (isUsed != other.isUsed) return false
        if (who != other.who) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id?.hashCode() ?: 0
        result = 31 * result + (desc?.hashCode() ?: 0)
        result = 31 * result + (source?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + isUsed.hashCode()
        result = 31 * result + (who?.hashCode() ?: 0)
        return result
    }

}
