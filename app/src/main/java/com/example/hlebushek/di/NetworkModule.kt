package com.example.hlebushek.di

import com.example.hlebushek.*
import dagger.Module
import dagger.Provides
import io.grpc.Channel
import io.grpc.ClientInterceptor
import io.grpc.Metadata
import io.grpc.okhttp.OkHttpChannelBuilder
import io.grpc.stub.MetadataUtils
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(READONLY)
    fun provideReadOnlyInterceptor(metaKey: Metadata.Key<String>): ClientInterceptor {
        val header = Metadata().also {
            it.put(metaKey, "$BEARER $READONLY_TOKEN")
        }
        return MetadataUtils.newAttachHeadersInterceptor(header)
    }

    @Provides
    @Singleton
    @Named(FULLACCESS)
    fun provideFullAccessInterceptor(metaKey: Metadata.Key<String>): ClientInterceptor {
        val header = Metadata().also {
            it.put(metaKey, "$BEARER $FULLACCESS_TOKEN_V2")
        }
        return MetadataUtils.newAttachHeadersInterceptor(header)
    }

    @Provides
    @Singleton
    fun provideOkHttpChannel(): Channel = OkHttpChannelBuilder
        .forAddress(
            TINKOFF_API_V2_URL,
            TINKOFF_API_V2_PORT
        )
        .build()

    @Provides
    fun provideMetaKey(): Metadata.Key<String> =
        Metadata.Key.of(AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER)
}