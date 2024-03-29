package com.example.kotlinproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.app.AppConstant
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    internal var mList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        initRecycleView()

    }

    private fun initRecycleView() {
        mList.add(AppConstant.LIST_TITLE0)
        mList.add(AppConstant.LIST_TITLE1)
        mList.add(AppConstant.LIST_TITLE2)
        mList.add(AppConstant.LIST_TITLE3)
        mList.add(AppConstant.LIST_TITLE4)

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        val adpter = RecyclemAdapter(this)
        adpter.setData(mList)
        recyclerView.setAdapter(adpter)
    }
}
