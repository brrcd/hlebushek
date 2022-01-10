package com.example.hlebushek.eventbus

sealed class Event {
    object CheckPrice : Event()
    object OtherOne: Event()
}