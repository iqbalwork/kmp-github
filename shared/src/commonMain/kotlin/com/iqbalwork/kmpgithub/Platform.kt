package com.iqbalwork.kmpgithub

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform