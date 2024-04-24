package dmitry.mobilecourse.preferences

import android.content.Context

class CommonPreferencesStorage(context: Context): PreferenceStorage {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE)

    private companion object {
        const val PREFERENCE_FILE = "user_settings"

        const val USER_NAME_KEY = "user_name"
        const val USER_FEEL_KEY = "user_feel"
    }

    override fun save(user: User) {
        sharedPreferences.edit()
            .putString(USER_NAME_KEY, user.name)
            .putBoolean(USER_FEEL_KEY, user.isGood)
            .apply()
    }

    override fun find(): User? {
        val userName = (sharedPreferences.all[USER_NAME_KEY] ?: return null) as String
        val isUserGood = (sharedPreferences.all[USER_FEEL_KEY] ?: return null) as Boolean

        return User(userName, isUserGood)
    }

}
