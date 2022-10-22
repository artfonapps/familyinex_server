package com.artfonapps

import com.artfonapps.repository.UserRepository
import com.artfonapps.repository.impl.UserRepositoryImpl

object RepositoryModule {
    fun getUserRepository(): UserRepository = UserRepositoryImpl
}