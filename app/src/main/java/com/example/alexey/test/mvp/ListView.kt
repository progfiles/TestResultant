package com.example.alexey.test.mvp

import com.example.alexey.test.models.Item
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ListView:MvpView{
    fun onUpdate(stock: Array<Item>)
    fun onLoading(load:Boolean)
}