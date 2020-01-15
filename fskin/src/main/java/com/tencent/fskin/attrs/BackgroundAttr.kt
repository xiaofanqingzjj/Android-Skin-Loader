package com.tencent.fskin.attrs

import android.view.View
import com.tencent.fskin.SkinManager
import com.tencent.fskin.SkinElementAttr


/**
 * android:background
 */
class BackgroundAttr : SkinElementAttr() {

    override fun apply(view: View?) {
        super.apply(view)
        view?.run {
            when (attrValueTypeName) {
                "color" -> setBackgroundColor(SkinManager.resources.getColor(attrValueRefId))
                "drawable" -> background = (SkinManager.resources.getDrawable(attrValueRefId))
            }
        }
    }
}