package com.iqbalwork.kmpgithub.di

import com.iqbalwork.kmpgithub.data.repository.GithubRepositoryImpl
import com.iqbalwork.kmpgithub.domain.GithubRepository
import org.koin.dsl.module

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
val repositoryModule = module {
    single<GithubRepository> { GithubRepositoryImpl(get()) }
}
