package com.example.hlebushek.di

import android.content.Context
import androidx.room.Room
import com.example.hlebushek.db.StockDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): StockDatabase =
        Room.databaseBuilder(
            context,
            StockDatabase::class.java,
            "stock.db"
        )
            .fallbackToDestructiveMigration()
            .build()
}