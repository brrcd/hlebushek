package com.example.hlebushek.repository

import com.example.hlebushek.FULLACCESS
import com.example.hlebushek.READONLY
import com.example.hlebushek.model.local.Share
import io.grpc.Channel
import io.grpc.ClientInterceptor
import ru.tinkoff.piapi.contract.v1.*
import javax.inject.Inject
import javax.inject.Named

interface RemoteRepository {
    fun getListOfShares(): List<ShareOrBuilder>
    fun getListOfLastPrices(shares : List<Share>): List<LastPriceOrBuilder>

    fun getAccountId(): List<Account>
    fun getPortfolioResponse(accountId: String): PortfolioResponse
}

class RemoteRepositoryImpl
@Inject constructor(
    private val okHttpChannel: Channel,
    @Named(READONLY) private val ro_interceptor: ClientInterceptor,
    @Named(FULLACCESS) private val fa_interceptor: ClientInterceptor
) : RemoteRepository {

    override fun getListOfShares(): List<ShareOrBuilder> {
        val stub = InstrumentsServiceGrpc.newBlockingStub(okHttpChannel)
        val request = InstrumentsRequest.newBuilder()
            .build()
        val result = stub
            .withInterceptors(ro_interceptor)
            .shares(request)
        return result.instrumentsOrBuilderList
    }

    override fun getListOfLastPrices(shares : List<Share>): List<LastPriceOrBuilder> {
        val figiList = shares.map { it.figi }
        val stub = MarketDataServiceGrpc.newBlockingStub(okHttpChannel)
        val request = GetLastPricesRequest.newBuilder()
            .addAllFigi(figiList)
            .build()
        val result = stub
            .withInterceptors(ro_interceptor)
            .getLastPrices(request)
        return result.lastPricesList
    }

    override fun getAccountId(): List<Account> {
        val stub = UsersServiceGrpc.newBlockingStub(okHttpChannel)
        val request = GetAccountsRequest.newBuilder()
            .build()
        val result = stub
            .withInterceptors(ro_interceptor)
            .getAccounts(request)
        return result.accountsList
    }

    override fun getPortfolioResponse(accountId: String): PortfolioResponse {
        val stub = OperationsServiceGrpc.newBlockingStub(okHttpChannel)
        val request = PortfolioRequest.newBuilder()
            .setAccountId(accountId)
            .build()
        return stub
            .withInterceptors(ro_interceptor)
            .getPortfolio(request)
    }
}