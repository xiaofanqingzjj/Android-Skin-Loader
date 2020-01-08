package com.tencent.fskin

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import cn.feng.skin.manager.config.SkinConfig
import cn.feng.skin.manager.listener.ISkinUpdate
import com.tencent.fskin.util.PreferencesUtils
import com.tencent.fskin.util.async
import java.io.File


/**
 *
 * 皮肤
 *
 */
object SkinManager {

    const val TAG = "SkinManager"


    private var mContext: Context? = null


    /**
     * 皮肤包的Resource
     */
    private var mSkinResources: Resources? = null


    /**
     * 通用的资源加载器
     */
    lateinit var resources: SkinResources

    var skinPackageName: String? = null

    /**
     * 初始化皮肤管理
     */
    fun init(context: Context) {
        mContext = context.applicationContext
        resources = SkinResources(context.resources)

        async {
            val skinPath = currentSkinPath()
            if (!skinPath.isNullOrEmpty()) {
                applySkin(skinPath)
            }
        }
    }


    internal fun getCurrentSkinResource(): Resources? {
        return this.mSkinResources
    }



    /**
     * 加载一个皮肤包
     *
     * @return 加载成功Resources返回的是皮肤包的Resources对象，String为失败原因
     */
    private fun loadSkin(skinPkgPath: String): Pair<Resources?, String?> {

        val context = mContext ?: return Pair(null, "Context is null")

        val file = File(skinPkgPath)

        if (!file.exists()) {
            return Pair(null, "Skin file is not exist")
        }

        val pm = context.packageManager

        return try {
            val skinPackageInfo = pm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES)

            skinPackageName = skinPackageInfo.packageName

            val skinAssetManager = AssetManager::class.java.newInstance()
            val addAssetPathMethod = skinAssetManager.javaClass.getMethod("addAssetPath", String::class.java)
            addAssetPathMethod.invoke(skinAssetManager, skinPkgPath)

            val resources: Resources = context.resources
            val skinResource = Resources(skinAssetManager, resources.displayMetrics, resources.configuration)

            Pair(skinResource, null)
        } catch (e: java.lang.Exception) {
            Pair(null, e.message)
        }
    }


    private fun saveCurrentSkin(skinPkgPath: String) {
        val context = mContext ?: return
        PreferencesUtils.putString(context, "skinPath", skinPkgPath)
    }


    /**
     * 当前应用的皮肤包
     */
    fun currentSkinPath(): String? {
        val context = mContext ?: return null
        return PreferencesUtils.getString(context, "skinPath")
    }

    /**
     * 应用皮肤
     */
    fun applySkin(skinPkgPath: String, changeCallback: ILoaderListener? = null) {

        Log.d(TAG, "applySkin:$skinPkgPath")

        val context = mContext ?: return

        async<Pair<Resources?, String?>> (
                preExecute = {
                    changeCallback?.onStart()
                },
                doBackground = {
                    loadSkin(skinPkgPath)
                },
                postExecute =  {
                    val result = it!!

                    Log.d(TAG, "applySkinComplete:$skinPkgPath, result:$it")

                    if (result.first != null) {
                        saveCurrentSkin(skinPkgPath)
                        mSkinResources = result.first
                        changeCallback?.onSuccess()

                        notifySkinUpdate()
                    } else {
                        changeCallback?.onFailed(result.second)
                    }
                })

    }

    private var skinObservers: ArrayList<ISkinUpdate>? = null

    fun notifySkinUpdate() {
        if (skinObservers == null) return
        for (observer in skinObservers!!) {
            observer.onThemeUpdate()
        }
    }

    fun attach(observer: ISkinUpdate) {
        if (skinObservers == null) {
            skinObservers = ArrayList<ISkinUpdate>()
        }
        if (!skinObservers!!.contains(observer)) {
            skinObservers!!.add(observer)
        }
    }

    fun detach(observer: ISkinUpdate) {
        if (skinObservers == null) return
        if (skinObservers!!.contains(observer)) {
            skinObservers!!.remove(observer)
        }
    }


    fun restoreDefaultTheme() {
//        SkinConfig.saveSkinPath(context, SkinConfig.DEFALT_SKIN)
//        isDefaultSkin = true
//        mResources = context.getResources()

        mSkinResources = null
        PreferencesUtils.putString(mContext!!, "skinPath", "")
        notifySkinUpdate()
    }


    interface ILoaderListener {
        fun onStart()
        fun onSuccess()
        fun onFailed(reason: String?)
    }

}