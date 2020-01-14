package com.tencent.fskin

import com.tencent.fskin.attrs.BackgroundAttr
import com.tencent.fskin.attrs.ImageSrcAttr
import com.tencent.fskin.attrs.TextColorAttr


/**
 * 在这里可以添加动态换肤的属性
 *
 */
object SkinElementAttrFactory {

    private val supportSkinAttrs: MutableMap<String, Class<out SkinElementAttr>> = mutableMapOf()

    fun registerSkinAttr(attrName: String, attr: Class<out SkinElementAttr>) {
        supportSkinAttrs[attrName] = attr
    }


    init {
        registerSkinAttr("background", BackgroundAttr::class.java)
        registerSkinAttr("textColor", TextColorAttr::class.java)
        registerSkinAttr("src", ImageSrcAttr::class.java)
    }


    /**
     *
     */
    fun createSkinAttr(attrName: String, attrValueRefId: Int, attrValueRefName: String?, typeName: String?): SkinElementAttr? {
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