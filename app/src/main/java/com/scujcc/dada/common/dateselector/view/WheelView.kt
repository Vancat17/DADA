/*
 *  Android Wheel Control.
 *  https://code.google.com/p/android-wheel/
 *  
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.scujcc.dada.common.dateselector.view

import android.content.Context
import android.database.DataSetObserver
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.Orientation
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.animation.Interpolator
import android.widget.LinearLayout
import com.scujcc.dada.common.dateselector.adapter.DatePickAdapter
import com.scujcc.dada.common.dateselector.adapter.WheelAdapter

import java.util.LinkedList

/**
 * Numeric wheel2 view.
 *
 * @author Yuri Kanivets
 */
class WheelView : View {

    // Wheel Values
    private var currentItem = 0

    // Count of visible items
    /**
     * Gets count of visible items
     *
     * @return the count of visible items
     */
    /**
     * Sets the desired count of visible items. Actual amount of visible items
     * depends on wheel2 layout parameters. To apply changes and rebuild view
     * call measure().
     *
     * @param count the desired count for visible items
     */
    var visibleItems = DEF_VISIBLE_ITEMS

    // Item height
    private var itemHeight = 0

    // Shadows drawables
    private var topShadow: GradientDrawable? = null
    private var bottomShadow: GradientDrawable? = null

    // Scrolling
    private var scroller: WheelScroller? = null

    /**********補上偵測 Wheel 是否滾動的boolean Tag */
    var isScrollingPerformed: Boolean = false
        private set

    private var scrollingOffset: Int = 0

    // Cyclic
    internal var isCyclic = false

    // Items layout
    private var itemsLayout: LinearLayout? = null

    // The number of first item in layout
    private var firstItem: Int = 0

    // View adapter
    /**
     * Gets view adapter
     *
     * @return the view adapter
     */
    var viewAdapter: WheelAdapter? = null
        private set

    // Recycle
    private val recycle = WheelRecycle(this)

    // Listeners
    private val changingListeners = LinkedList<OnWheelChangedListener>()
    private val scrollingListeners = LinkedList<OnWheelScrollListener>()
    private val clickingListeners = LinkedList<OnWheelClickedListener>()

    private var selectTextColor = -0xbbbbbc
    private var textColor = -0x222223

    // Scrolling listener
    private var scrollingListener: WheelScroller.ScrollingListener = object : WheelScroller.ScrollingListener {
        override fun onStarted() {
            isScrollingPerformed = true
            notifyScrollingListenersAboutStart()
        }

        override fun onScroll(distance: Int) {
            doScroll(distance)

            val height = height
            if (scrollingOffset > height) {
                scrollingOffset = height
                scroller!!.stopScrolling()
            } else if (scrollingOffset < -height) {
                scrollingOffset = -height
                scroller!!.stopScrolling()
            }
        }

        override fun onFinished() {
            if (isScrollingPerformed) {
                notifyScrollingListenersAboutEnd()
                isScrollingPerformed = false
            }

            scrollingOffset = 0
            invalidate()
        }

        override fun onJustify() {
            if (Math.abs(scrollingOffset) > WheelScroller.MIN_DELTA_FOR_SCROLLING) {
                scroller!!.scroll(scrollingOffset, 0)
            }
        }
    }

    // Adapter listener
    private val dataObserver = object : DataSetObserver() {
        override fun onChanged() {
            invalidateWheel(false)
        }

        override fun onInvalidated() {
            invalidateWheel(true)
        }
    }

    /**
     * Calculates range for wheel2 items
     *
     * @return the items range
     */
    private// top + bottom items
            // process empty items above the first or below the second
    val itemsRange: ItemsRange?
        get() {
            if (getItemHeight() == 0) {
                return null
            }

            var first = currentItem
            var count = 1

            while (count * getItemHeight() < height) {
                first--
                count += 2
            }

            if (scrollingOffset != 0) {
                if (scrollingOffset > 0) {
                    first--
                }
                count++
                val emptyItems = scrollingOffset / getItemHeight()
                first -= emptyItems
                count += Math.asin(emptyItems.toDouble()).toInt()
            }
            return ItemsRange(first, count)
        }

    /**
     * Constructor
     */
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initData(context)
    }

    /**
     * Constructor
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initData(context)
    }

    /**
     * Constructor
     */
    constructor(context: Context) : super(context) {
        initData(context)
    }

    /**
     * Initializes class data
     *
     * @param context the context
     */
    private fun initData(context: Context) {
        scroller = WheelScroller(context, scrollingListener)
    }

    /**
     * Set the the specified scrolling interpolator
     *
     * @param interpolator the interpolator
     */
    fun setInterpolator(interpolator: Interpolator) {
        scroller!!.setInterpolator(interpolator)
    }

    /**
     * Sets view adapter. Usually new adapters contain different views, so it
     * needs to rebuild view by calling measure().
     *
     * @param viewAdapter the view adapter
     */
    fun setAdapter(viewAdapter: DatePickAdapter) {
        if (this.viewAdapter != null) {
            this.viewAdapter!!.unregisterDataSetObserver(dataObserver)
        }
        this.viewAdapter = viewAdapter
        if (this.viewAdapter != null) {
            this.viewAdapter!!.registerDataSetObserver(dataObserver)
        }

        invalidateWheel(true)
    }

    fun setSelectTextColor(textColor: Int, selectTextColor: Int) {
        this.selectTextColor = selectTextColor
        this.textColor = textColor
    }


    /**
     * Adds wheel2 changing listener
     *
     * @param listener the listener
     */
    fun addChangingListener(listener: OnWheelChangedListener) {
        changingListeners.add(listener)
    }

    /**
     * Removes wheel2 changing listener
     *
     * @param listener the listener
     */
    fun removeChangingListener(listener: OnWheelChangedListener) {
        changingListeners.remove(listener)
    }

    /**
     * Notifies changing listeners
     *
     * @param oldValue the old wheel2 value
     * @param newValue the new wheel2 value
     */
    protected fun notifyChangingListeners(oldValue: Int, newValue: Int) {
        for (listener in changingListeners) {
            listener.onChanged(this, oldValue, newValue)
        }

        if (oldValue < 0 || newValue < 0 || itemsLayout == null)
            return

        val oldView = itemsLayout!!.getChildAt(oldValue - firstItem)
        val newView = itemsLayout!!.getChildAt(newValue - firstItem)

        refreshTextStatus(oldView, oldValue)
        refreshTextStatus(newView, newValue)

    }

    /**
     * Adds wheel2 scrolling listener
     *
     * @param listener the listener
     */
    fun addScrollingListener(listener: OnWheelScrollListener) {
        scrollingListeners.add(listener)
    }

    /**
     * Removes wheel2 scrolling listener
     *
     * @param listener the listener
     */
    fun removeScrollingListener(listener: OnWheelScrollListener) {
        scrollingListeners.remove(listener)
    }

    /**
     * Notifies listeners about starting scrolling
     */
    protected fun notifyScrollingListenersAboutStart() {
        for (listener in scrollingListeners) {
            listener.onScrollingStarted(this)
        }
    }

    /**
     * Notifies listeners about ending scrolling
     */
    protected fun notifyScrollingListenersAboutEnd() {
        for (listener in scrollingListeners) {
            listener.onScrollingFinished(this)
        }
    }

    /**
     * Adds wheel2 clicking listener
     *
     * @param listener the listener
     */
    fun addClickingListener(listener: OnWheelClickedListener) {
        clickingListeners.add(listener)
    }

    /**
     * Removes wheel2 clicking listener
     *
     * @param listener the listener
     */
    fun removeClickingListener(listener: OnWheelClickedListener) {
        clickingListeners.remove(listener)
    }

    /**
     * Notifies listeners about clicking
     */
    protected fun notifyClickListenersAboutClick(item: Int) {
        for (listener in clickingListeners) {
            listener.onItemClicked(this, item)
        }
    }

    /**
     * Gets current value
     *
     * @return the current value
     */
    fun getCurrentItem(): Int {
        return currentItem
    }

    /**
     * Sets the current item. Does nothing when index is wrong.
     *
     * @param index    the item index
     * @param animated the animation flag
     */
    @JvmOverloads
    fun setCurrentItem(index: Int, animated: Boolean = false) {
        var index = index
        if (viewAdapter == null || viewAdapter!!.itemsCount == 0) {
            return  // throw?
        }

        val itemCount = viewAdapter!!.itemsCount
        if (index < 0 || index >= itemCount) {
            if (isCyclic) {
                while (index < 0) {
                    index += itemCount
                }
                index %= itemCount
            } else {
                return  // throw?
            }
        }
        if (index != currentItem) {
            if (animated) {
                var itemsToScroll = index - currentItem
                if (isCyclic) {
                    val scroll = itemCount + Math.min(index, currentItem) - Math.max(index, currentItem)
                    if (scroll < Math.abs(itemsToScroll)) {
                        itemsToScroll = if (itemsToScroll < 0) scroll else -scroll
                    }
                }
                scroll(itemsToScroll, 0)
            } else {
                scrollingOffset = 0


                val old = currentItem
                currentItem = index
                notifyChangingListeners(old, currentItem)
                invalidate()
            }
        }
    }

    private fun refreshTextStatus(convertView: View?, old: Int) {
        if (convertView == null) {
            return
        }
        viewAdapter!!.refreshStatus(convertView, old == currentItem)
    }

    /**
     * Tests if wheel2 is cyclic. That means before the 1st item there is shown
     * the last one
     *
     * @return true if wheel2 is cyclic
     */
    fun isCyclic(): Boolean {
        return isCyclic
    }

    /**
     * Set wheel2 cyclic flag
     *
     * @param isCyclic the flag to set
     */
    fun setCyclic(isCyclic: Boolean) {
        this.isCyclic = isCyclic
        invalidateWheel(false)
    }

    /**
     * Invalidates wheel2
     *
     * @param clearCaches if true then cached views will be clear
     */
    fun invalidateWheel(clearCaches: Boolean) {
        if (clearCaches) {
            recycle.clearAll()
            if (itemsLayout != null) {
                itemsLayout!!.removeAllViews()
            }
            scrollingOffset = 0
        } else if (itemsLayout != null) {
            // cache all items
            recycle.recycleItems(itemsLayout!!, firstItem, ItemsRange())
        }

        invalidate()
    }

    /**
     * Initializes resources
     */
    private fun initResourcesIfNecessary() {
        //		if (centerDrawable == null) {
        //			centerDrawable = getContext().getResources().getDrawable(R.drawable.wheel_val);
        //		}

        if (topShadow == null) {
            topShadow = GradientDrawable(Orientation.TOP_BOTTOM, SHADOWS_COLORS)
        }

        if (bottomShadow == null) {
            bottomShadow = GradientDrawable(Orientation.BOTTOM_TOP, SHADOWS_COLORS)
        }

        //		setBackgroundResource(R.drawable.wheel_bg);
    }

    /**
     * Calculates desired height for layout
     *
     * @param layout the source layout
     * @return the desired layout height
     */
    private fun getDesiredHeight(layout: LinearLayout?): Int {
        if (layout != null && layout.getChildAt(0) != null) {
            itemHeight = layout.getChildAt(0).measuredHeight
        }

        val desired = itemHeight * visibleItems - itemHeight * ITEM_OFFSET_PERCENT / 50

        return Math.max(desired, suggestedMinimumHeight)
    }

    /**
     * Returns height of wheel2 item
     *
     * @return the item height
     */
    fun getItemHeight(): Int {
        if (itemHeight != 0) {
            return itemHeight
        }

        if (itemsLayout != null && itemsLayout!!.getChildAt(0) != null) {
            itemHeight = itemsLayout!!.getChildAt(0).height
            return itemHeight
        }

        return height / visibleItems
    }

    /**
     * Calculates control width and creates text layouts
     *
     * @param widthSize the input layout width
     * @param mode      the layout mode
     * @return the calculated control width
     */
    private fun calculateLayoutWidth(widthSize: Int, mode: Int): Int {
        initResourcesIfNecessary()

        // TODO: make it static
        itemsLayout!!.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        itemsLayout!!.measure(View.MeasureSpec.makeMeasureSpec(widthSize, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        var width = itemsLayout!!.measuredWidth

        if (mode == View.MeasureSpec.EXACTLY) {
            width = widthSize
        } else {
            width += 2 * PADDING

            // Check against our minimum width
            width = Math.max(width, suggestedMinimumWidth)

            if (mode == View.MeasureSpec.AT_MOST && widthSize < width) {
                width = widthSize
            }
        }

        itemsLayout!!.measure(View.MeasureSpec.makeMeasureSpec(width - 2 * PADDING, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))

        return width
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        buildViewForMeasuring()

        val width = calculateLayoutWidth(widthSize, widthMode)

        var height: Int
        if (heightMode == View.MeasureSpec.EXACTLY) {
            height = heightSize
        } else {
            height = getDesiredHeight(itemsLayout)

            if (heightMode == View.MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize)
            }
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layout(r - l, b - t)
    }

    /**
     * Sets layouts width and height
     *
     * @param width  the layout width
     * @param height the layout height
     */
    private fun layout(width: Int, height: Int) {
        val itemsWidth = width - 2 * PADDING

        itemsLayout!!.layout(0, 0, itemsWidth, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (viewAdapter != null && viewAdapter!!.itemsCount > 0) {
            updateView()
            drawItems(canvas)
        }
        //		drawShadows(canvas);
    }

    /**
     * Draws shadows on top and bottom of control
     *
     * @param canvas the canvas for drawing
     */
    private fun drawShadows(canvas: Canvas) {
        val height = (1.5 * getItemHeight()).toInt()
        topShadow!!.setBounds(0, 0, width, height)
        topShadow!!.draw(canvas)

        bottomShadow!!.setBounds(0, getHeight() - height, width, getHeight())
        bottomShadow!!.draw(canvas)
    }

    /**
     * Draws items
     *
     * @param canvas the canvas for drawing
     */
    private fun drawItems(canvas: Canvas) {
        canvas.save()

        val top = (currentItem - firstItem) * getItemHeight() + (getItemHeight() - height) / 2
        canvas.translate(PADDING.toFloat(), (-top + scrollingOffset).toFloat())

        itemsLayout!!.draw(canvas)

        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled || viewAdapter == null) {
            return true
        }

        when (event.action) {
            MotionEvent.ACTION_MOVE -> if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_UP -> if (!isScrollingPerformed) {
                var distance = event.y.toInt() - height / 2
                if (distance > 0) {
                    distance += getItemHeight() / 2
                } else {
                    distance -= getItemHeight() / 2
                }
                val items = distance / getItemHeight()
                if (items != 0 && isValidItemIndex(currentItem + items)) {
                    notifyClickListenersAboutClick(currentItem + items)
                }
            }
        }

        return scroller!!.onTouchEvent(event)
    }

    /**
     * Scrolls the wheel2
     *
     * @param delta the scrolling value
     */
    private fun doScroll(delta: Int) {
        scrollingOffset += delta

        val itemHeight = getItemHeight()
        var count = scrollingOffset / itemHeight

        var pos = currentItem - count
        val itemCount = viewAdapter!!.itemsCount

        var fixPos = scrollingOffset % itemHeight
        if (Math.abs(fixPos) <= itemHeight / 2) {
            fixPos = 0
        }
        if (isCyclic && itemCount > 0) {
            if (fixPos > 0) {
                pos--
                count++
            } else if (fixPos < 0) {
                pos++
                count--
            }
            // fix position by rotating
            while (pos < 0) {
                pos += itemCount
            }
            pos %= itemCount
        } else {
            //
            if (pos < 0) {
                count = currentItem
                pos = 0
            } else if (pos >= itemCount) {
                count = currentItem - itemCount + 1
                pos = itemCount - 1
            } else if (pos > 0 && fixPos > 0) {
                pos--
                count++
            } else if (pos < itemCount - 1 && fixPos < 0) {
                pos++
                count--
            }
        }

        val offset = scrollingOffset
        if (pos != currentItem) {
            setCurrentItem(pos, false)
        } else {
            invalidate()
        }

        // update offset
        scrollingOffset = offset - count * itemHeight
        if (scrollingOffset > height) {
            scrollingOffset = scrollingOffset % height + height
        }
    }

    /**
     * Scroll the wheel2
     *
     * @param itemsToScroll items to scroll
     * @param time          scrolling duration
     */
    fun scroll(itemsToScroll: Int, time: Int) {
        val distance = itemsToScroll * getItemHeight() - scrollingOffset
        scroller!!.scroll(distance, time)
    }

    /**
     * Rebuilds wheel2 items if necessary. Caches all unused items.
     *
     * @return true if items are rebuilt
     */
    private fun rebuildItems(): Boolean {
        var updated: Boolean
        val range = itemsRange
        if (itemsLayout != null) {
            val first = recycle.recycleItems(itemsLayout!!, firstItem, range!!)
            updated = firstItem != first
            firstItem = first
        } else {
            createItemsLayout()
            updated = true
        }

        if (!updated) {
            updated = firstItem != range!!.first || itemsLayout!!.childCount != range.count
        }

        if (firstItem > range!!.first && firstItem <= range.last) {
            for (i in firstItem - 1 downTo range.first) {
                if (!addViewItem(i, true)) {
                    break
                }
                firstItem = i
            }
        } else {
            firstItem = range.first
        }

        var first = firstItem
        for (i in itemsLayout!!.childCount until range.count) {
            if (!addViewItem(firstItem + i, false) && itemsLayout!!.childCount == 0) {
                first++
            }
        }
        firstItem = first

        return updated
    }

    /**
     * Updates view. Rebuilds items and label if necessary, recalculate items
     * sizes.
     */
    private fun updateView() {
        if (rebuildItems()) {
            calculateLayoutWidth(width, View.MeasureSpec.EXACTLY)
            layout(width, height)
        }
    }

    /**
     * Creates item layouts if necessary
     */
    private fun createItemsLayout() {
        if (itemsLayout == null) {
            itemsLayout = LinearLayout(context)
            itemsLayout!!.orientation = LinearLayout.VERTICAL
        }
    }

    /**
     * Builds view for measuring
     */
    private fun buildViewForMeasuring() {
        // clear all items
        if (itemsLayout != null) {
            recycle.recycleItems(itemsLayout!!, firstItem, ItemsRange())
        } else {
            createItemsLayout()
        }

        // add views
        val addItems = visibleItems / 2
        for (i in currentItem + addItems downTo currentItem - addItems) {
            if (addViewItem(i, true)) {
                firstItem = i
            }
        }
    }

    /**
     * Adds view for item to items layout
     *
     * @param index the item index
     * @param first the flag indicates if view should be first
     * @return true if corresponding item exists and is added
     */
    private fun addViewItem(index: Int, first: Boolean): Boolean {
        val view = getItemView(index)
        refreshTextStatus(view, index)

        if (view != null) {
            if (first) {
                itemsLayout!!.addView(view, 0)
            } else {
                itemsLayout!!.addView(view)
            }

            return true
        }

        return false
    }

    /**
     * Checks whether intem index is valid
     *
     * @param index the item index
     * @return true if item index is not out of bounds or the wheel2 is cyclic
     */
    private fun isValidItemIndex(index: Int): Boolean {
        return viewAdapter != null && viewAdapter!!.itemsCount > 0 && (isCyclic || index >= 0 && index < viewAdapter!!.itemsCount)
    }

    /**
     * Returns view for specified item
     *
     * @param index the item index
     * @return item view or empty view if index is out of bounds
     */
    private fun getItemView(index: Int): View? {
        var index = index
        if (viewAdapter == null || viewAdapter!!.itemsCount == 0) {
            return null
        }
        val count = viewAdapter!!.itemsCount
        if (!isValidItemIndex(index)) {
            return viewAdapter!!.getEmptyItem(recycle.emptyItem, itemsLayout)
        } else {
            while (index < 0) {
                index = count + index
            }
        }

        index %= count
        return viewAdapter!!.getView(index, recycle.item, itemsLayout)
    }

    /**
     * Stops scrolling
     */
    fun stopScrolling() {
        scroller!!.stopScrolling()
    }

    companion object {
        private val TAG = "WheelView"
        /**
         * Top and bottom shadows colors
         */
        private val SHADOWS_COLORS = intArrayOf(-0xeeeeef, 0x00AAAAAA, 0x00AAAAAA)

        /**
         * Top and bottom items offset (to hide that)
         */
        private val ITEM_OFFSET_PERCENT = 10

        /**
         * Left and right padding value
         */
        private val PADDING = 10

        /**
         * Default count of visible items
         */
        private val DEF_VISIBLE_ITEMS = 5
    }
}
/**
 * Sets the current item w/o animation. Does nothing when index is wrong.
 *
 * @param index the item index
 */
