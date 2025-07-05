package com.tpc.digigate.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.tpc.digigate.domain.model.SupportedThemes
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences
import com.tpc.digigate.domain.repository.appPreferences.DataStorePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferencesImpl(context: Context) : AppPreferences {

    private val datastore = DataStoreProvider.getInstance(context)

    private object PreferenceKeys {
        val APP_THEME = intPreferencesKey("theme")
    }

    override val appTheme: DataStorePreference<SupportedThemes>
        get() = object : DataStorePreference<SupportedThemes> {
            override fun getFlow(): Flow<SupportedThemes> {
                return datastore.data.map { prefs ->
                    val themeId =
                        prefs[PreferenceKeys.APP_THEME] ?: SupportedThemes.SYSTEM_DEFAULT.themeId
                    SupportedThemes.entries.find { it.themeId == themeId }
                        ?: SupportedThemes.SYSTEM_DEFAULT
                }
            }

            override suspend fun set(value: SupportedThemes) {
                datastore.edit { prefs ->
                    prefs[PreferenceKeys.APP_THEME] = value.themeId
                }
            }

        }
}

object DataStoreProvider {
    private val dataStoreScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @Volatile
    private var INSTANCE: DataStore<androidx.datastore.preferences.core.Preferences>? =
        null

    fun getInstance(context: Context): DataStore<androidx.datastore.preferences.core.Preferences> {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: PreferenceDataStoreFactory.create(
                scope = dataStoreScope
            ) {
                context.preferencesDataStoreFile("settings")
            }.also { INSTANCE = it }
        }
    }
}