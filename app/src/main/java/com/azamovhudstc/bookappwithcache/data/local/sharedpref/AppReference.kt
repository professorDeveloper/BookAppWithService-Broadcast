package com.azamovhudstc.bookappwithcache.data.local.sharedpref


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppReference @Inject constructor(
    @ApplicationContext
    context: Context,
) {
    private var sharedPref: SharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPref.edit()


    fun setToken(token: String) {
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("TOKEN", "")
    }

    var startScreen: String
        set(value) = sharedPref.edit().putString("INTRO", value).apply()
        get() = sharedPref.getString("INTRO", "INTRO")!!

    var userName: String
        set(value) = sharedPref.edit().putString("USERNAME", value).apply()
        get() = sharedPref.getString("USERNAME", "USERNAME")!!

    var verifyToken: String
        set(value) = sharedPref.edit().putString("VERIFY_TOKEN", value).apply()
        get() = sharedPref.getString("VERIFY_TOKEN", "")!!

    fun clear() {
        editor.clear()
    }
}