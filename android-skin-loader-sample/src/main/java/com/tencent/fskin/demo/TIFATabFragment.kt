package com.tencent.fskin.demo

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTabHost
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TabWidget
import com.tencent.fskin.demo.base.BaseFragment
import com.tencent.fskin.demo.util.toPx

/**
 * TAB Fragment支持多个TAB的Fragment
 * Created by xiaofanqing on 15/8/21.
 */
class TIFATabFragment : BaseFragment() {



    private var mTabHost: FragmentTabHost? = null
    private var mTabWidget: TabWidget? = null
    var tabContainer: FrameLayout? = null
        private set
    var tabLine: ImageView? = null
        private set
    private var mChildContainer: FrameLayout? = null
    private var mOnTabChangeListener: OnTabChangeListener? = null
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
        contentView = createContentView()
        mTabHost!!.setOnTabChangedListener { tabId ->
            if (mOnTabChangeListener != null) {
                mOnTabChangeListener!!.onTabChange(tabId)
            }
        }
    }

    private fun createContentView(): View {
        mTabHost = FragmentTabHost(mContext)
        mTabHost!!.id = R.id.tabhost
        // content
        val rlContent = RelativeLayout(mContext)
        rlContent.layoutParams = LAYOUT_PARAMS_MATCH_PARENT
        mTabHost!!.addView(rlContent)
        // 1.导航容器
// menu的容器
        tabContainer = FrameLayout(mContext!!)
        tabContainer!!.id = R.id.widget_frame
        val menuHeight: Int = DEFAULT_MENU_HEIGHT.toPx(mContext!!)
        val lpMenuContainer = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, menuHeight)
        lpMenuContainer.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        tabContainer!!.layoutParams = lpMenuContainer
        // menu本身
        mTabWidget = TabWidget(mContext)
        mTabWidget!!.dividerDrawable = ColorDrawable(Color.TRANSPARENT)
        mTabWidget!!.id = R.id.tabs
        mTabWidget!!.layoutParams = ViewGroup.LayoutParams(LAYOUT_PARAMS_MATCH_PARENT)
        tabContainer!!.addView(mTabWidget)
        // 线条
        tabLine = ImageView(mContext)
        tabLine!!.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DEFAULT_LINE_HEIGHT)
        tabLine!!.setBackgroundColor(Color.BLUE)
        tabContainer!!.addView(tabLine)
        // 2.子Activity的容器
        mChildContainer = FrameLayout(mContext!!)
        mChildContainer!!.id = R.id.tabcontent
        // 布局填充父类，在menu之上
        val lpChildContainer = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        lpChildContainer.addRule(RelativeLayout.ABOVE, R.id.widget_frame)
        mChildContainer!!.layoutParams = lpChildContainer
        rlContent.addView(tabContainer)
        rlContent.addView(mChildContainer)
        // 设置
        mTabHost!!.setup(mContext, childFragmentManager, R.id.tabcontent)
        return mTabHost as FragmentTabHost
    }

    /**
     * 向TAB中添加一个子TAB
     * @param tabId TAB ID
     * @param indicator
     * @param fragmentClass
     * @param bundle
     */
    fun addTab(tabId: String?, indicator: View?, fragmentClass: Class<out Fragment?>?, bundle: Bundle?) {
        val tabSpec = mTabHost!!.newTabSpec(tabId!!)
        tabSpec.setIndicator(indicator)
        mTabHost!!.addTab(tabSpec, fragmentClass!!, bundle)
    }

    /**
     * 切换TAB
     * @param index
     */
    fun switchTab(index: Int) {
        mTabHost!!.currentTab = index
    }

    fun setOnTabChangeListener(l: OnTabChangeListener?) {
        mOnTabChangeListener = l
    }

    interface OnTabChangeListener {
        fun onTabChange(tabId: String?)
    }

    companion object {
        private val TAG = TIFATabFragment::class.java.simpleName
        private const val DEFAULT_MENU_HEIGHT = 60
        private const val DEFAULT_LINE_HEIGHT = 4
        private val LAYOUT_PARAMS_MATCH_PARENT = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}