package com.tpc.digigate.domain.repository.appPreferences

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface DataStorePreference<T> {
    suspend fun get(): T = getFlow().first()

    fun getFlow(): Flow<T>

    suspend fun set(value: T)

    suspend fun getAndUpdate(update: (T) -> T): Unit =
        throw NotImplementedError("getAndUpdate has not been implemented for this preference.")
}