package com.tencent.fskin.model

import android.view.View
import java.util.*

data class SkinItem(

    var view: View? = null,
    var attrs: ArrayList<SkinAttr> = ArrayList()) {



    fun apply() {
        attrs.forEach {
            it.apply(view)
        }
    }

    fun clean() {
        attrs.clear()
    }


}