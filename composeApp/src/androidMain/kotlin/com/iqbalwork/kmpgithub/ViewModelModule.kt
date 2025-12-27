package com.iqbalwork.kmpgithub

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
val viewModelModule = module {
    viewModelOf(::MainViewModel)
}
