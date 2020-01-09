package com.tencent.fskin

import com.tencent.fskin.attrs.BackgroundAttr
import com.tencent.fskin.attrs.TextColorAttr


/**
 * 在这里可以添加动态换肤的属性
 *
 */
object SkinAttrFactory {

    private val supportSkinAttrs: MutableMap<String, Class<out SkinAttr>> = mutableMapOf()

    fun registerSkinAttr(attrName: String, attr: Class<out SkinAttr>) {
        supportSkinAttrs[attrName] = attr
    }


    init {
        registerSkinAttr("background", BackgroundAttr::class.java)
        registerSkinAttr("textColor", TextColorAttr::class.java)
    }


    /**
     *
     */
    fun createSkinAttr(attrName: String, attrValueRefId: Int, attrValueRefName: String?, typeName: String?): SkinAttr? {
        val skinAttr = supportSkinAttrs[attrName]?.newInstance()
        skinAttr?.run {
            this.attrName = attrName
            this.attrValueRefId = attrValueRefId
            this.attrValueRefName = attrValueRefName
            this.attrValueTypeName = typeName
        }
        return skinAttr
    }

    fun isSupportedAttr(attrName: String): Boolean {
        return supportSkinAttrs.keys.contains(attrName)
    }
}