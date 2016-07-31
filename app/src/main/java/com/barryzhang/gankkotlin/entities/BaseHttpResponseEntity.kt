package com.barryzhang.gankkotlin.entities

import java.io.Serializable

/**
* barryhappy2010@gmail.com
* https://github.com/barryhappy
* http://www.barryzhang.com/
* Created by Barry on 16/7/9
*/

open class BaseHttpResponseEntity<T> : Serializable{
    var error = false
    var results : T? = null
}