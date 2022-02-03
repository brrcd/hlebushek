package com.example.hlebushek.eventbus

sealed class Event {
    object UpdatePrice : Event()
    object OtherOne: Event()
}