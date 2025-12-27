package com.iqbalwork.kmpgithub.di

import com.iqbalwork.kmpgithub.data.network.ApiService
import com.iqbalwork.kmpgithub.data.network.HttpNetwork
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
val sharedModule = module {
    single<Json> {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpNetwork.createHttpClient(get())
    }
    singleOf(::ApiService)
}
