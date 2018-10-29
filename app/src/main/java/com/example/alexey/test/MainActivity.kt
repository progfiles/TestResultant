package com.example.alexey.test

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.example.alexey.test.models.Item
import com.example.alexey.test.mvp.ListPresenter
import com.example.alexey.test.mvp.ListView
import com.hannesdorfmann.mosby3.mvp.MvpActivity


class MainActivity : MvpActivity<ListView, ListPresenter>(), ListView {

    lateinit var dataView: RecyclerView
    lateinit var updateButton:Button
    private  val adapter=DataAdapter()


    override fun createPresenter(): ListPresenter=ListPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataView = findViewById(R.id.dataView)
        updateButton=findViewById(R.id.buttonView)
        dataView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        dataView.adapter=adapter
        presenter.attachView(this)
        updateButton.setOnClickListener { presenter.updateData() }
        presenter.updateData()
    }

    override fun onUpdate(stock: Array<Item>) {
        adapter.resetItems(stock)
    }
    override fun onLoading(load: Boolean) {

    }
}
