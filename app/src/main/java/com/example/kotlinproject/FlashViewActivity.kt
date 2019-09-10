package com.example.kotlinproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinproject.view.FlashView

class FlashViewActivity : AppCompatActivity() {


    lateinit var flashView: FlashView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_view)

        flashView = findViewById(R.id.flashView)
        flashView.setImage(R.drawable.flash)
        flashView.start()
    }
}
