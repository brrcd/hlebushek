package com.example.hlebushek.repository

import com.example.hlebushek.convertToFraction
import com.example.hlebushek.model.local.LastPrice
import com.example.hlebushek.model.local.Settings
import com.example.hlebushek.model.local.Share
import ru.tinkoff.piapi.contract.v1.Account
import ru.tinkoff.piapi.contract.v1.GetMarginAttributesResponse
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

    fun getListOfShares(): List<Share>
    fun getListOfLastPrices(shares : List<Share>): List<LastPrice>
    fun getAccountId(): List<Account>
    fun getMarginAttributes(accountId: String): GetMarginAttributesResponse
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

    override fun getListOfShares(): List<Share> {
        val data = remoteRepository.getListOfShares()
        return mapShareOrBuilderToShare(data)
    }

    override fun getListOfLastPrices(shares : List<Share>): List<LastPrice> {
        val data = remoteRepository.getListOfLastPrices(shares)
        return mapLastPriceOrBuilderToLastPrice(data)
    }

    override fun getAccountId(): List<Account> =
        remoteRepository.getAccountId()

    override fun getMarginAttributes(accountId: String): GetMarginAttributesResponse =
        remoteRepository.getMarginAttributes(accountId)

    private fun mapLastPriceOrBuilderToLastPrice(lastPrices: List<LastPriceOrBuilder>): List<LastPrice> =
        lastPrices.map {
            LastPrice(
                it.figi,
                it.price.units.toDouble() + it.price.nano.convertToFraction(),
                it.time.seconds
            )
        }

    private fun mapShareOrBuilderToShare(list: List<ShareOrBuilder>): List<Share> =
        list.map {
            Share(
                it.figi,
                it.name,
                it.ticker,
                it.lot,
                it.currency
            )
        }
}