package com.tpc.digigate.domain.repository.appPreferences

import com.tpc.digigate.domain.model.SupportedThemes

interface AppPreferences {
    val appTheme: DataStorePreference<SupportedThemes>
}