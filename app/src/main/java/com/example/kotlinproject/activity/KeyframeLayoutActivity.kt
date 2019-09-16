package com.example.kotlinproject.activity

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.kotlinproject.R
import com.example.kotlinproject.view.KeyframeView


class KeyframeLayoutActivity : AppCompatActivity() {

    lateinit var btnAnimator:Button
    lateinit var keyframeView:KeyframeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyframe_layout)

        keyframeView = findViewById(R.id.keyframeView)
        btnAnimator = findViewById(R.id.btnAnimator)

        btnAnimator.setOnClickListener {
            val keyframe1 = Keyframe.ofFloat(0f, 0f) // 开始：progress 为 0
            val keyframe2 = Keyframe.ofFloat(0.5f, 100f) // 进行到一半是，progres 为 100
            val keyframe3 = Keyframe.ofFloat(1f, 80f) // 结束时倒回到 80

            val holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3)

            val animator = ObjectAnimator.ofPropertyValuesHolder(keyframeView, holder)
            animator.duration = 2000

            //(ValueAnimator.INFINITE  -1  无限次)  (ValueAnimator.RESTART  1  执行两次  ) (0  不重复执行)
            animator.repeatCount = 0
            //FastOutSlowInInterpolator 贝塞尔曲线加速器
            animator.interpolator = FastOutSlowInInterpolator()
            animator.start()
        }



    }
}
