package com.example.hlebushek.db

import androidx.room.*
import com.example.hlebushek.model.local.Share

@Dao
interface ShareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShareToCurrentTrade(share: Share)

    @Query("SELECT * FROM shares")
    fun getListOfSharesFromDB(): List<Share>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShare(share: Share)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShares(shares: List<Share>)
}