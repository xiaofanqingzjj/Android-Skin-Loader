package com.tencent.fskin.attrs

import android.view.View
import com.tencent.fskin.SkinManager
import com.tencent.fskin.SkinElementAttr


/**
 * android:background
 */
class BackgroundAttr : SkinElementAttr() {

    override fun apply(view: View?) {
        view?.run {
            when (attrValueTypeName) {
                RES_TYPE_NAME_COLOR -> setBackgroundColor(SkinManager.resources.getColor(attrValueRefId))
                RES_TYPE_NAME_DRAWABLE -> background = (SkinManager.resources.getDrawable(attrValueRefId))
            }
        }
    }
}