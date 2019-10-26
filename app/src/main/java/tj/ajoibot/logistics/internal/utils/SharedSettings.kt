package tj.ajoibot.logistics.internal.utils

import android.app.Application
import android.content.Context

class SharedSettings(private val application: Application){
    private val preferenceKey = "tj.ajoibot.logistics.PREFERENCE_FILE_KEY"

    fun getToken(): String? {
        return getValue("logistics_token")
    }

    fun saveToken(token: String) {
        return saveValue("logistics_token", token)
    }


    fun getValue(key: String):String?{
        return application.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
            .getString(key, "")
    }

    fun saveValue(key: String, value: String){
        val sharedPref = application.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }
}