package com.example.hlebushek.repository

import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.local.Share
import ru.tinkoff.piapi.contract.v1.LastPriceOrBuilder
import ru.tinkoff.piapi.contract.v1.ShareOrBuilder
import javax.inject.Inject

interface MainRepository {
    fun addShareToCurrentTrade(share: Share)
    fun getListOfSharesFromDB(): List<Share>
    fun updateShare(share: Share)
    fun updateShares(shares: List<Share>)

    fun saveSettings(settings: Settings)
    fun getSettings(): Settings?

    fun getListOfShares(): List<ShareOrBuilder>
    fun getListOfLastPrices(shares : List<Share>): List<LastPriceOrBuilder>
}

class MainRepositoryImpl
@Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : MainRepository {
    override fun addShareToCurrentTrade(share: Share) =
        localRepository.addShareToCurrentTrade(share)

    override fun getListOfSharesFromDB(): List<Share> =
        localRepository.getListOfSharesFromDB()

    override fun updateShare(share: Share) =
        localRepository.updateShare(share)

    override fun updateShares(shares: List<Share>) =
        localRepository.updateShares(shares)

    override fun saveSettings(settings: Settings) =
        localRepository.saveSettings(settings)

    override fun getSettings(): Settings? =
        localRepository.getSettings()

    override fun getListOfShares(): List<ShareOrBuilder> =
        remoteRepository.getListOfShares()

    override fun getListOfLastPrices(shares : List<Share>): List<LastPriceOrBuilder> =
        remoteRepository.getListOfLastPrices(shares)
}