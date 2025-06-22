package com.tpc.digigate.domain.model

enum class SupportedThemes(val themeType: String, val themeId: Int) {
    LIGHT("Light", 1),
    DARK("Dark", 2),
    SYSTEM_DEFAULT("System Default", 3)
}