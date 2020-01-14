package com.tencent.fskin.attrs

import android.util.Log
import android.view.View
import android.widget.TextView
import com.tencent.fskin.SkinManager
import com.tencent.fskin.SkinElementAttr


/**
 * android:textColor
 */
class TextColorAttr : SkinElementAttr() {

    companion object {
        const val TAG = "TextColorAttr"
    }

    override fun apply(view: View?) {

//        Log.d(TAG, "applyView:$view, this: $this")

        (view as? TextView)?.run {
            setTextColor(SkinManager.resources.getColorStateList(attrValueRefId))
        }
    }
}