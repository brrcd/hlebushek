package com.example.hlebushek

import android.util.Log
import android.view.View
import com.example.hlebushek.App.Companion.TAG

fun View.setGone(){
    visibility = View.GONE
}

fun View.setVisible(){
    visibility = View.VISIBLE
}

fun log(message: Any){
    Log.e(TAG, message.toString())
}