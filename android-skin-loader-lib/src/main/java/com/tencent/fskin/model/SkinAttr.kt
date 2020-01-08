package com.tencent.fskin.model

import android.view.View

abstract class SkinAttr(

        /**
         * 属性名
         */
        var attrName: String? = null,

        /**
         * 属性值
         */
        var attrValueRefId: Int = 0,


        /**
         * 属性值资源名
         */
        var attrValueRefName: String? = null,

        /**
         * 属性值资源类型名
         */
        var attrValueTypeName: String? = null) {


    /**
     * 当皮肤改变的时候重新给View设置对应的值
     */
    open fun apply(view: View?) {




    }

    override fun toString(): String {
        return ("SkinAttr \n[\nattrName=" + attrName + ", \n"
                + "attrValueRefId=" + attrValueRefId + ", \n"
                + "attrValueRefName=" + attrValueRefName + ", \n"
                + "attrValueTypeName=" + attrValueTypeName
                + "\n]")
    }

    companion object {
        const val RES_TYPE_NAME_COLOR = "color"
        const val RES_TYPE_NAME_DRAWABLE = "drawable"
    }
}