package com.example.alexey.test.models

import java.util.*


/*
{
"name":"HOUSE PREF A",
"price":{
    "currency":"PHP",
    "amount":96.80
},
"percent_change":0.00,
"volume":1790,
"symbol":"8990P"
},
 */
data class Price(val currency:String, val amount:Double)
data class Item(val name:String,
                val percent_change:Double,
                val volume:Int,
                val symbol:String,
                val price: Price)

data class Stock(val stock: Array<Item>)