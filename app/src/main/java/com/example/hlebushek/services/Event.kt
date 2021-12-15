package com.example.hlebushek.services

sealed class Event {
    object CheckPrice : Event()
    object OtherOne: Event()
}