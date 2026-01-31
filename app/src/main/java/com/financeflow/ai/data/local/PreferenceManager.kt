package com.financeflow.ai.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val API_KEY = stringPreferencesKey("api_key")
    private val PROVIDER = stringPreferencesKey("provider")
    private val BASE_URL = stringPreferencesKey("base_url")
    private val MODEL_NAME = stringPreferencesKey("model_name")
    private val ADVICE_PROMPT = stringPreferencesKey("advice_prompt")
    private val ANALYSIS_PROMPT = stringPreferencesKey("analysis_prompt")

    val apiKey: Flow<String?> = context.dataStore.data.map { it[API_KEY] }
    val provider: Flow<String> = context.dataStore.data.map { it[PROVIDER] ?: "Gemini" }
    val baseUrl: Flow<String> = context.dataStore.data.map { it[BASE_URL] ?: "https://api.openai.com/v1/" }
    val modelName: Flow<String> = context.dataStore.data.map { it[MODEL_NAME] ?: "gemini-2.0-flash" }
    val advicePrompt: Flow<String?> = context.dataStore.data.map { it[ADVICE_PROMPT] }
    val analysisPrompt: Flow<String?> = context.dataStore.data.map { it[ANALYSIS_PROMPT] }

    suspend fun saveSettings(key: String, provider: String, url: String, model: String, advice: String, analysis: String) {
        context.dataStore.edit { preferences ->
            preferences[API_KEY] = key
            preferences[PROVIDER] = provider
            preferences[BASE_URL] = url
            preferences[MODEL_NAME] = model
            preferences[ADVICE_PROMPT] = advice
            preferences[ANALYSIS_PROMPT] = analysis
        }
    }
}
