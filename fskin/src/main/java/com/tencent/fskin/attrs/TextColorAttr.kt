package com.tencent.fskin.attrs

import android.view.View
import android.widget.TextView
import com.tencent.fskin.SkinManager
import com.tencent.fskin.SkinAttr


/**
 * android:textColor
 */
class TextColorAttr : SkinAttr() {

    override fun apply(view: View?) {
        (view as? TextView)?.run {
            setTextColor(SkinManager.resources.getColor(attrValueRefId))
        }
    }
}