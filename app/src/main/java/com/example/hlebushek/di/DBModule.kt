package com.example.hlebushek.di

import android.content.Context
import androidx.room.Room
import com.example.hlebushek.db.TradeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TradeDatabase =
        Room.databaseBuilder(
            context,
            TradeDatabase::class.java,
            "trade.db"
        )
            .fallbackToDestructiveMigration()
            .build()
}