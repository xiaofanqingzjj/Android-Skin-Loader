package com.tencent.fskin.model.skinattrs

import android.view.View
import android.widget.TextView
import com.tencent.fskin.SkinManager
import com.tencent.fskin.model.SkinAttr

class TextColorAttr : SkinAttr() {

    override fun apply(view: View?) {
        (view as? TextView)?.run {
            setTextColor(SkinManager.resources.getColor(attrValueRefId))
        }
    }
}