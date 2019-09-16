package com.example.kotlinproject.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.kotlinproject.utils.UtilsJava.dpToPixel
import kotlin.math.roundToInt

class KeyframeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr){

    internal val radius :Float = dpToPixel(80F)
    internal var progress = 0F

    // 矩形  精度浮点型
    var arcRect : RectF = RectF()

    var paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        //抗锯齿
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = dpToPixel(40F)
        paint.textAlign = Paint.Align.CENTER
    }


    fun getProgress():Float{
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width/2
        val centerY = height/2

        paint.color = Color.parseColor("#E91E63")
        //设置填充样式
        paint.style = Paint.Style.STROKE
        //设置画笔笔刷类型 如影响画笔但始末端
        paint.strokeCap = Paint.Cap.ROUND

        paint.strokeWidth = dpToPixel(15F)
        arcRect.set(centerX-radius,centerY-radius,centerX+radius,centerY+radius)
        //画圆弧
        // 参数1 定义的圆弧的形状和大小的范围    参数2 圆弧是从哪个角度来顺时针绘画  参数3 圆弧扫过的角度
        // 参数4 设置我们的圆弧在绘画的时候，是否经过圆形   参数 5 画笔对象
        canvas.drawArc(arcRect,135F,progress * 2.7f, false, paint)

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        var pro = progress.roundToInt()
        canvas.drawText("$pro%", centerX.toFloat(), centerY - (paint.ascent() + paint.descent()) / 2, paint)


    }
}