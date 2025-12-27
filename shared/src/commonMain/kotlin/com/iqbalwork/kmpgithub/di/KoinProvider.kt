package com.iqbalwork.kmpgithub.di

import com.iqbalwork.kmpgithub.domain.GithubRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
object KoinProvider : KoinComponent {
    val githubRepository: GithubRepository by inject()
}
