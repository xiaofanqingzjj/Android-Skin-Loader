package com.tencent.fskin

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.*
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import java.io.InputStream

class SkinResourcesWrapper(var resources: Resources)
    : Resources(resources.assets, resources.displayMetrics, resources.configuration) {

    companion object {
        const val TAG = "SkinResourcesWrapper"
    }


    private var mSkinResource: Resources? = null

    init {

//        mContext.createConfigurationContext(Configuration().apply {
//
//        })
//        Context.create
    }

    private fun getResourceIdInSkin(id: Int): Int {
        return mSkinResource?.run {
            val type = getResourceTypeName(id)
            val entryName = getResourceEntryName(id)
            val packageName = getResourcePackageName(id)

            try {
                getIdentifier(entryName, type, packageName)
            } catch (e: Exception) {
                0
            }
        } ?: 0
    }



    override fun getText(id: Int): CharSequence {
        return super.getText(id)
    }

    override fun  getFont(id: Int): Typeface {
        return super.getFont(id)
    }

    override fun getQuantityText(id: Int, quantity: Int): CharSequence {
        return super.getQuantityText(id, quantity)
    }

    override fun getString(id: Int): String {
        Log.d(TAG, "getString:$id")
        var value = super.getString(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getString(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }


    override fun getString(id: Int, vararg formatArgs: Any): String {
        Log.d(TAG, "getString:$id")
        var value = super.getString(id, formatArgs)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getString(idInSkin, formatArgs) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String {
        Log.d(TAG, "getQuantityString:$id")
        var value = super.getQuantityString(id, quantity, formatArgs)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getString(idInSkin,quantity, formatArgs) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getQuantityString(id: Int, quantity: Int): String {
        Log.d(TAG, "getQuantityString:$id")
        var value = super.getQuantityString(id, quantity)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getQuantityString(idInSkin,quantity) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

//    fun getText(id: Int, def: CharSequence): CharSequence {
//        throw RuntimeException("Stub!")
//    }
//
//    fun getTextArray(id: Int): Array<CharSequence> {
//        throw RuntimeException("Stub!")
//    }

    override fun getStringArray(id: Int): Array<String> {
        Log.d(TAG, "getStringArray:$id")
        var value = super.getStringArray(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getStringArray(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getIntArray(id: Int): IntArray {
        Log.d(TAG, "getIntArray:$id")
        var value = super.getIntArray(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getIntArray(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }


    override fun getDimension(id: Int): Float {
        Log.d(TAG, "getDimension:$id")
        var value = super.getDimension(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDimension(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getDimensionPixelOffset(id: Int): Int {
        Log.d(TAG, "getDimensionPixelOffset:$id")

        var value = super.getDimensionPixelOffset(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDimensionPixelOffset(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getDimensionPixelSize(id: Int): Int {

        Log.d(TAG, "getDimensionPixelSize:$id")

        var value = super.getDimensionPixelSize(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDimensionPixelSize(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

//    fun getFraction(id: Int, base: Int, pbase: Int): Float {
//        throw RuntimeException("Stub!")
//    }


    override fun getDrawable(id: Int): Drawable {

        Log.d(TAG, "getDrawable:$id")

        var value = super.getDrawable(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDrawable(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

     override fun getDrawable(id: Int,  theme: Theme?): Drawable? {

         Log.d(TAG, "getDrawable:$id, theme:$theme")

        var value = super.getDrawable(id, theme)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDrawable(idInSkin, theme) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }


    override fun getDrawableForDensity(id: Int, density: Int): Drawable? {

        Log.d(TAG, "getDrawableForDensity:$id, density:$density")

        var value = super.getDrawableForDensity(id, density)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDrawableForDensity(idInSkin, density) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getDrawableForDensity(id: Int, density: Int,  theme: Resources.Theme?): Drawable? {

        Log.d(TAG, "getDrawableForDensity:$id, density:$density")

        var value = super.getDrawableForDensity(id, density)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getDrawableForDensity(idInSkin, density) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

//
//    fun getMovie(id: Int): Movie {
//        throw RuntimeException("Stub!")
//    }


    override fun getColor(id: Int): Int {

        Log.d(TAG, "getColor:$id")

        var value = super.getColor(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getColor(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getColor(id: Int,  theme: Resources.Theme?): Int {
        var value = super.getColor(id, theme)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getColor(idInSkin, theme) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }


    override fun getColorStateList(id: Int): ColorStateList {
        var value = super.getColorStateList(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getColorStateList(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getColorStateList(id: Int,  theme: Resources.Theme?): ColorStateList {
        var value = super.getColorStateList(id, theme)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getColorStateList(idInSkin, theme) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getBoolean(id: Int): Boolean {
        var value = super.getBoolean(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getBoolean(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getInteger(id: Int): Int {
        var value = super.getInteger(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getInteger(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    @SuppressLint("NewApi")
    override fun getFloat(id: Int): Float {
        var value = super.getFloat(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getFloat(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getLayout(id: Int): XmlResourceParser {
        var value = super.getLayout(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getLayout(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

    override fun getAnimation(id: Int): XmlResourceParser {
        var value = super.getAnimation(id)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getAnimation(idInSkin) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }

//    @NonNull
//    @Throws(Resources.NotFoundException::class)
//    fun getXml(id: Int): XmlResourceParser {
//        throw RuntimeException("Stub!")
//    }
//
//    @NonNull
//    @Throws(Resources.NotFoundException::class)
//    fun openRawResource(id: Int): InputStream {
//        throw RuntimeException("Stub!")
//    }

////    fun openRawResource(id: Int, value: TypedValue): InputStream {
////        throw RuntimeException("Stub!")
//    }
//
//    @Throws(Resources.NotFoundException::class)
//    fun openRawResourceFd(id: Int): AssetFileDescriptor {
//        throw RuntimeException("Stub!")
//    }

    override fun getValue(id: Int, outValue: TypedValue, resolveRefs: Boolean) {
        var value = super.getValue(id, outValue, resolveRefs)

        val idInSkin  = getResourceIdInSkin(id)
        if (idInSkin != 0) {
            try {
                value = mSkinResource?.getValue(idInSkin, outValue, resolveRefs) ?: value
            } catch (e: Exception) {
                // ignore
            }
        }
        return value
    }
//
//    @Throws(Resources.NotFoundException::class)
//    fun getValueForDensity(id: Int, density: Int, outValue: TypedValue, resolveRefs: Boolean) {
//        throw RuntimeException("Stub!")
//    }
//
//    @Throws(Resources.NotFoundException::class)
//    fun getValue(name: String, outValue: TypedValue, resolveRefs: Boolean) {
//        var value = super.getValue(id, outValue, resolveRefs)
//
//        val idInSkin  = getResourceIdInSkin(id)
//        if (idInSkin != 0) {
//            try {
//                value = mSkinResource?.getValue(idInSkin, outValue, resolveRefs) ?: value
//            } catch (e: Exception) {
//                // ignore
//            }
//        }
//        return value
//        throw RuntimeException("Stub!")
//    }
//
//    fun getAttributeSetSourceResId(@Nullable set: AttributeSet): Int {
//        throw RuntimeException("Stub!")
//    }
//
//    fun newTheme(): Resources.Theme {
//        throw RuntimeException("Stub!")
//    }
//
//    fun obtainAttributes(set: AttributeSet, attrs: IntArray): TypedArray {
//        throw RuntimeException("Stub!")
//    }
//
//
//    @Deprecated("")
//    fun updateConfiguration(config: Configuration, metrics: DisplayMetrics) {
//        throw RuntimeException("Stub!")
//    }



}