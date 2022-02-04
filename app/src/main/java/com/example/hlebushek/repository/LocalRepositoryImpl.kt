package com.example.hlebushek.repository

import com.example.hlebushek.db.TradeDatabase
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.local.Share
import javax.inject.Inject

interface LocalRepository {
    fun addShareToCurrentTrade(share: Share)
    fun getListOfSharesFromDB(): List<Share>
    fun updateShare(share: Share)
    fun updateShares(shares: List<Share>)
    fun saveSettings(settings: Settings)
    fun getSettings(): Settings?
}

class LocalRepositoryImpl
@Inject constructor(
    private val database: TradeDatabase
): LocalRepository {

    override fun addShareToCurrentTrade(share: Share) =
        database
            .shareDao()
            .addShareToCurrentTrade(share)

    override fun getListOfSharesFromDB() =
        database
            .shareDao()
            .getListOfSharesFromDB()

    override fun updateShare(share: Share) =
        database
            .shareDao()
            .updateShare(share)

    override fun updateShares(shares: List<Share>) =
        database
            .shareDao()
            .updateShares(shares)

    override fun saveSettings(settings: Settings) =
        database
            .settingsDao()
            .saveSettings(settings)

    override fun getSettings(): Settings? =
        database
            .settingsDao()
            .getSettings()
}