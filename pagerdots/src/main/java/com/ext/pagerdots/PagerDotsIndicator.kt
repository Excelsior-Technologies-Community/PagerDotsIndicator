package com.ext.pagerdots

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.ext.pagerdots.model.DotAnimation
import com.ext.pagerdots.model.DotType

class PagerDotsIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var dotCount = 0
    private var selectedIndex = 0

    private var dotSize = 20
    private var dotMargin = 8

    private var dotType = DotType.CIRCLE

    private var normalColor =
        ContextCompat.getColor(context, R.color.dot_normal)
    private var selectedColor =
        ContextCompat.getColor(context, R.color.dot_selected)
    private var animationType = DotAnimation.NONE


    init {
        orientation = HORIZONTAL

        attrs?.let {
            val ta = context.obtainStyledAttributes(it, R.styleable.PagerDotsIndicator)

            dotSize = ta.getDimensionPixelSize(
                R.styleable.PagerDotsIndicator_dotSize,
                20
            )

            dotMargin = ta.getDimensionPixelSize(
                R.styleable.PagerDotsIndicator_dotSpacing,
                8
            )

            normalColor = ta.getColor(
                R.styleable.PagerDotsIndicator_dotColor,
                normalColor
            )

            selectedColor = ta.getColor(
                R.styleable.PagerDotsIndicator_selectedDotColor,
                selectedColor
            )

            dotType = when (
                ta.getInt(R.styleable.PagerDotsIndicator_dotType, 0)
            ) {
                1 -> DotType.SQUARE
                2 -> DotType.ROUNDED_SQUARE
                3 -> DotType.PILL
                4 -> DotType.LINE
                5 -> DotType.TRIANGLE
                else -> DotType.CIRCLE
            }

            animationType = when (
                ta.getInt(R.styleable.PagerDotsIndicator_animationType, 0)
            ) {
                1 -> DotAnimation.SCALE
                2 -> DotAnimation.FADE
                3 -> DotAnimation.SLIDE
                4 -> DotAnimation.WORM
                else -> DotAnimation.NONE
            }

            ta.recycle()
        }
    }


    fun setDotCount(count: Int) {
        dotCount = count
        createDots()
    }

    fun selectDot(index: Int) {
        selectedIndex = index
        updateDots()
    }

    private fun createDots() {
        removeAllViews()

        repeat(dotCount) { index ->
            val dot = DotView(
                context = context,
                dotSize = dotSize,
                dotMargin = dotMargin,
                dotType = dotType,
                normalColor = normalColor,
                selectedColor = selectedColor
            )
            addView(dot)
        }

        updateDots()
    }

    private fun updateDots() {
        for (i in 0 until childCount) {
            val dot = getChildAt(i) as DotView
            val isSelected = i == selectedIndex
            dot.setDotSelected(i == selectedIndex)
            dot.animateSelection(isSelected, animationType)
        }
    }
}
