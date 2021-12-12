package com.example.hlebushek

import android.util.Log
import android.view.View

fun View.setGone(){
    visibility = View.GONE
}

fun View.setVisible(){
    visibility = View.VISIBLE
}

fun log(message: Any){
    Log.e("_TEST_", message.toString())
}