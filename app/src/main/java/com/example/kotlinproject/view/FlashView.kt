package com.example.kotlinproject.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.AttrRes
import com.example.kotlinproject.R

/**
 * loading加载效果
 */
class FlashView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val DEFAULT_MASK_WIDTH = 160
    private val DEFAULT_MASK_HEIGHT = 80
    private val DEFAULT_DURATION = 1200

    private var mDstPaint: Paint? = null
    private var mSrcPaint: Paint? = null
    private var mSrcBitmap: Bitmap? = null
    private var mMaskBitmap: Bitmap? = null
    private var mRenderMaskBitmap: Bitmap? = null
    private var mRenderMaskBitmapCanvas: Canvas? = null
    private var mMaskWidth: Int = 0
    private var mMaskHeight: Int = 0
    private var mHorMoveDistance: Float = 0.toFloat()
    private val mStartColor = intArrayOf(0, -1, 0)
    private val mEndColor = floatArrayOf(0.4f, 0.6f, 0.8f)
    private var mStartAlpha = 130
    private var mAnimator: ObjectAnimator? = null
    private var mPlaying = false
    private var mFlashDuration: Int = 0

    init {
        mFlashDuration = DEFAULT_DURATION
        mSrcPaint = Paint()
        mSrcPaint!!.alpha = mStartAlpha
        val mode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        mDstPaint = Paint()
        mDstPaint!!.isAntiAlias = true
        mDstPaint!!.isDither = true
        mDstPaint!!.isFilterBitmap = true
        mDstPaint!!.xfermode = mode
        val metrics = resources.displayMetrics
        mMaskWidth =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_MASK_WIDTH.toFloat(),
                metrics
            ).toInt()
        mMaskHeight =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_MASK_HEIGHT.toFloat(),
                metrics
            ).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        var resultW = widthSize
        var resultH = heightSize
        if (widthMode == View.MeasureSpec.AT_MOST) {
            resultW = mSrcBitmap!!.width
        }
        if (heightMode == View.MeasureSpec.AT_MOST) {
            resultH = mSrcBitmap!!.height
        }
        setMeasuredDimension(resultW, resultH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        ensureSrcBitmapNotNull()
        // 绘制源图
        drawSrcOnCanvas(canvas, mSrcPaint)
        // 在遮罩图上绘制源图
        ensureRenderMaskBitmapNotNull()
        drawSrcOnCanvas(mRenderMaskBitmapCanvas!!, null)
        // 在遮罩图上绘制渐变遮罩
        ensureMaskBitmapNotNull()
        mRenderMaskBitmapCanvas!!.drawBitmap(mMaskBitmap!!, mHorMoveDistance, 0f, mDstPaint)
        // 最终映射到控件上
        canvas.drawBitmap(mRenderMaskBitmap!!, 0f, 0f, null)
    }

    private fun drawSrcOnCanvas(canvas: Canvas, paint: Paint?) {
        canvas.drawBitmap(mSrcBitmap!!, 0f, 0f, paint)
    }

    private fun clearCanvas(canvas: Canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
    }

    private fun setPercent(percent: Float) {
        mHorMoveDistance = this.mMaskWidth * (percent - 1.5f)
        invalidate()
    }

    fun setImage(resId: Int) {
        mSrcBitmap = BitmapFactory.decodeResource(resources, resId)
    }

    private fun ensureSrcBitmapNotNull() {
        if (mSrcBitmap == null) {
            throw RuntimeException("You need call setImage(int resId) to set a src image!")
        }
    }

    private fun ensureRenderMaskBitmapNotNull() {
        if (mRenderMaskBitmap == null) {
            mRenderMaskBitmap =
                Bitmap.createBitmap(mMaskWidth, mMaskHeight, Bitmap.Config.ARGB_8888)
            mRenderMaskBitmapCanvas = Canvas(mRenderMaskBitmap!!)
        }
    }

    private fun ensureMaskBitmapNotNull() {
        if (mMaskBitmap == null) {
            val horMoveArea = (this.mMaskWidth * 2.5f).toInt()
            val verMoveArea = this.mMaskHeight
            val shader = LinearGradient(
                0.35f * horMoveArea,
                1.0f * verMoveArea,
                0.65f * horMoveArea,
                0.0f * verMoveArea,
                this.mStartColor,
                this.mEndColor,
                Shader.TileMode.CLAMP
            )
            mMaskBitmap = Bitmap.createBitmap(horMoveArea, verMoveArea, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(mMaskBitmap!!)
            val paint = Paint()
            paint.shader = shader
            canvas.drawRect(0.0f, 0.0f, horMoveArea.toFloat(), verMoveArea.toFloat(), paint)
        }
    }

    fun start() {
        if (mPlaying) {
            return
        }
        mPlaying = true
        startAnim()
    }

    fun stop() {
        if (mAnimator != null) {
            mAnimator!!.cancel()
        }
        mPlaying = false
    }

    private fun startAnim() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(this, "percent", 0f, 1.5f)
            mAnimator!!.interpolator = LinearInterpolator()
            mAnimator!!.duration = mFlashDuration.toLong()
            mAnimator!!.repeatCount = -1
        }
        mAnimator!!.start()
    }

    fun setDuration(duration: Int) {
        mFlashDuration = duration
    }

    fun setSrcAlpha(startAlpha: Int) {
        this.mStartAlpha = startAlpha
        if (mSrcPaint != null) {
            mSrcPaint!!.alpha = startAlpha
        }
    }

    fun isPlaying(): Boolean {
        return mPlaying
    }
}