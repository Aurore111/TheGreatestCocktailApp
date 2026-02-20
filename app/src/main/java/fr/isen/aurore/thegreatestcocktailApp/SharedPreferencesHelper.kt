package fr.isen.aurore.thegreatestcocktailApp

import android.content.Context
import android.view.WindowInsets
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.runtime.key
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharePreferencesHelper(context: Context) {
    private val key = "favDrinks"
    private val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveFavoriteList(list: ArrayList<String>) {
        val json = gson.toJson(list)
        sharedPreferences.edit { putString(key, json) }
    }

    fun getFavoritesList(): ArrayList<String> {
        val json = sharedPreferences.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }
}




