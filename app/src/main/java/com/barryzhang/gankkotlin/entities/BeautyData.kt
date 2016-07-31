package com.barryzhang.gankkotlin.entities


class BeautyData {
    private val id: Long? = null
    var date: String? = null
    var beauty: GankItem? = null
    var title: String? = null

    constructor(date: String, beauty: GankItem) {
        this.date = date
        this.beauty = beauty
    }

    companion object {

        fun create(date: String, beauty: GankItem): BeautyData {
            val b = BeautyData(date, beauty)
            return b
        }
    }
}
