package com.example.kotlinproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.example.kotlinproject.R
import com.example.kotlinproject.bean.SportsData
import com.example.kotlinproject.view.MISportsConnectView

/**
 * 仿小米运动
 */
class XMSportActivity : AppCompatActivity() {

    lateinit var miSportsLoadingView: MISportsConnectView
    lateinit var btnConnect :Button

    private var handler: Handler? = null
    internal var connect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xmsport)

        miSportsLoadingView = findViewById(R.id.miSportsLoadingView)

        val sportsData = SportsData()
        sportsData.step = 2714
        sportsData.distance = 1700f
        sportsData.calories = 34
        sportsData.progress = 75
        miSportsLoadingView.setSportsData(sportsData)

        handler = Handler()
        val connectButton = findViewById<Button>(R.id.btnConnect)
        connectButton.setOnClickListener {
            handler!!.postDelayed(Runnable {
                connect = !connect
                miSportsLoadingView.setConnected(connect)
                connectButton.text =
                    if (connect) getString(R.string.disconnect) else getString(R.string.connect)
            }, 500)
        }
    }
}
