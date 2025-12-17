package com.ext.pagerdots

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.LinearLayout
import com.ext.pagerdots.model.DotAnimation
import com.ext.pagerdots.model.DotType

class DotView(
    context: Context,
    private val dotSize: Int,
    private val dotMargin: Int,
    private val dotType: DotType,
    private val normalColor: Int,
    private val selectedColor: Int
) : View(context) {

    private val drawable = GradientDrawable()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    init {
        val params = LinearLayout.LayoutParams(dotSize, dotSize)
        params.setMargins(dotMargin, dotMargin, dotMargin, dotMargin)
        layoutParams = params

        applyShape()
        setDotSelected(false)
    }

    private fun applyShape() {
        val params = layoutParams as LinearLayout.LayoutParams

        when (dotType) {

            DotType.CIRCLE -> {
                params.width = dotSize
                params.height = dotSize
                drawable.shape = GradientDrawable.OVAL
                background = drawable
            }

            DotType.SQUARE -> {
                params.width = dotSize
                params.height = dotSize
                drawable.shape = GradientDrawable.RECTANGLE
                drawable.cornerRadius = 0f
                background = drawable
            }

            DotType.ROUNDED_SQUARE -> {
                params.width = dotSize
                params.height = dotSize
                drawable.shape = GradientDrawable.RECTANGLE
                drawable.cornerRadius = dotSize * 0.25f
                background = drawable
            }

            DotType.PILL -> {
                params.width = dotSize * 2
                params.height = dotSize
                drawable.shape = GradientDrawable.RECTANGLE
                drawable.cornerRadius = dotSize / 2f
                background = drawable
            }

            DotType.LINE -> {
                params.width = dotSize * 3
                params.height = dotSize / 3
                drawable.shape = GradientDrawable.RECTANGLE
                drawable.cornerRadius = params.height / 2f
                background = drawable
            }

            DotType.TRIANGLE -> {
                params.width = dotSize
                params.height = dotSize
                background = null   // Canvas will draw
            }
        }

        layoutParams = params
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (dotType != DotType.TRIANGLE) return

        val path = Path().apply {
            moveTo(width / 2f, 0f)
            lineTo(0f, height.toFloat())
            lineTo(width.toFloat(), height.toFloat())
            close()
        }

        canvas.drawPath(path, paint)
    }

    fun setDotSelected(isSelected: Boolean) {
        val color = if (isSelected) selectedColor else normalColor

        if (dotType == DotType.TRIANGLE) {
            paint.color = color
            invalidate()
        } else {
            drawable.setColor(color)
        }
    }

    fun animateSelection(
        selected: Boolean,
        animation: DotAnimation
    ) {
        when (animation) {

            DotAnimation.SCALE -> {
                animate()
                    .scaleX(if (selected) 1.4f else 1f)
                    .scaleY(if (selected) 1.4f else 1f)
                    .setDuration(200)
                    .start()
            }

            DotAnimation.FADE -> {
                animate()
                    .alpha(if (selected) 1f else 0.4f)
                    .setDuration(200)
                    .start()
            }

            else -> Unit
        }
    }
}
