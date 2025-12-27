package com.iqbalwork.kmpgithub.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
fun initKoin(
    config: KoinAppDeclaration? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule, repositoryModule, sharedModule
        )
    }
}
