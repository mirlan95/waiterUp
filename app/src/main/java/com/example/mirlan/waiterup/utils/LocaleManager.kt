package com.example.mirlan.waiterup.utils

object LocaleManager {

    fun setLocale(c: Context) {
        setNewLocale(c, getLanguage(c))
    }

    fun setNewLocale(c: Context, language: String) {
        persistLanguage(c, language)
        updateResources(c, language)
    }

    fun getLanguage(c: Context): String {}

    private fun persistLanguage(c: Context, language: String) {}

    private fun updateResources(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.getResources()
        val config = Configuration(res.getConfiguration())
        config.locale = locale
        res.updateConfiguration(config, res.getDisplayMetrics())
    }
}
