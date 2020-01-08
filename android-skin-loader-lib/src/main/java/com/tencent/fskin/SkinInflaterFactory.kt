package com.tencent.fskin

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import cn.feng.skin.manager.util.L
import com.tencent.fskin.model.SkinAttr
import com.tencent.fskin.model.SkinAttrFactory
import com.tencent.fskin.model.SkinItem
import java.util.*

class SkinInflaterFactory : LayoutInflater.Factory {



    /**
     * Store the view item that need skin changing in the activity
     */
    private val mSkinItems: MutableList<SkinItem> = ArrayList()

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? { // if this is NOT enable to be skined , simplly skip it

//        val isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false)
//        if (!isSkinEnable) {
//            return null
//        }

        val view = createView(context, name, attrs) ?: return null

        parseSkinAttr(context, attrs, view)

        return view
    }

    private fun createView(context: Context, name: String, attrs: AttributeSet): View? {

        var view: View? = null
        try {
            if (-1 == name.indexOf('.')) { // 系统自带的widget

                if ("View" == name) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs)
                }

                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs)
                }

                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs)
                }

            } else {
                view = LayoutInflater.from(context).createView(name, null, attrs)
            }

            L.i("about to create $name")

        } catch (e: Exception) {
            L.e("error while create 【" + name + "】 : " + e.message)
            view = null
        }
        return view
    }

    /**
     * Collect skin able tag such as background , textColor and so on
     *
     * @param context
     * @param attrs
     * @param view
     */
    private fun parseSkinAttr(context: Context, attrs: AttributeSet, view: View) {

        val viewAttrs: MutableList<SkinAttr> = ArrayList()

        for (i in 0 until attrs.attributeCount) {

            val attrName = attrs.getAttributeName(i)
            val attrValue = attrs.getAttributeValue(i)

            if (!SkinAttrFactory.isSupportedAttr(attrName)) { // 看属性是否是支持换肤的属性
                continue
            }

            if (attrValue.startsWith("@")) { // 只有@开头的才表示用了引用资源
                try {
                    val id = attrValue.substring(1).toInt()

                    val entryName = context.resources.getResourceEntryName(id)
                    val typeName = context.resources.getResourceTypeName(id)

                    val mSkinAttr = SkinAttrFactory.createSkinAttr(attrName, id, entryName, typeName)

                    if (mSkinAttr != null) {
                        viewAttrs.add(mSkinAttr)
                    }

                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                } catch (e: NotFoundException) {
                    e.printStackTrace()
                }
            }
        }

        if (viewAttrs.isNotEmpty()) {
            val skinItem = SkinItem()
            skinItem.view = view
            skinItem.attrs.addAll(viewAttrs)

            mSkinItems.add(skinItem)

            skinItem.apply()
        }

//
//        if (!ListUtils.isEmpty(viewAttrs)) {
//            val skinItem = SkinItem()
//            skinItem.view = view
//            skinItem.attrs = viewAttrs
//
//
//            mSkinItems.add(skinItem)
//            if (SkinManager.getInstance().isExternalSkin) {
//                skinItem.apply()
//            }
//        }
    }

    fun applySkin() {

        mSkinItems.forEach {
            if (it.view != null) {
                it.apply()
            }
        }

    }

//    fun dynamicAddSkinEnableView(context: Context, view: View?, pDAttrs: List<DynamicAttr>) {
//        val viewAttrs: MutableList<SkinAttr> = ArrayList()
//        val skinItem = SkinItem()
//        skinItem.view = view
//        for (dAttr in pDAttrs) {
//            val id = dAttr.refResId
//            val entryName = context.resources.getResourceEntryName(id)
//            val typeName = context.resources.getResourceTypeName(id)
//            val mSkinAttr = AttrFactory.get(dAttr.attrName, id, entryName, typeName)
//            viewAttrs.add(mSkinAttr)
//        }
//        skinItem.attrs = viewAttrs
//        addSkinView(skinItem)
//    }
//
//    fun dynamicAddSkinEnableView(context: Context, view: View?, attrName: String?, attrValueResId: Int) {
//        val entryName = context.resources.getResourceEntryName(attrValueResId)
//        val typeName = context.resources.getResourceTypeName(attrValueResId)
//        val mSkinAttr = AttrFactory.get(attrName, attrValueResId, entryName, typeName)
//        val skinItem = SkinItem()
//        skinItem.view = view
//        val viewAttrs: MutableList<SkinAttr> = ArrayList()
//        viewAttrs.add(mSkinAttr)
//        skinItem.attrs = viewAttrs
//        addSkinView(skinItem)
//    }
//
//    fun addSkinView(item: SkinItem) {
//        mSkinItems.add(item)
//    }

    fun clean() {
        mSkinItems?.forEach {
            it.clean()
        }
        mSkinItems.clear()
    }

    companion object {
        private const val DEBUG = true
    }
}