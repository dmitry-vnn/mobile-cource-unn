package dmitry.mobilecourse.preferences

import android.content.Context

class SplitPreferencesStorage(context: Context): PreferenceStorage {

    private val userNameSharedPreferences = context.getSharedPreferences(USER_NAME_PREFERENCE_FILE, Context.MODE_PRIVATE)
    private val userFeelSharedPreferences = context.getSharedPreferences(USER_FEEL_PREFERENCE_FILE, Context.MODE_PRIVATE)

    private companion object {
        const val USER_NAME_PREFERENCE_FILE = "user_name"
        const val USER_FEEL_PREFERENCE_FILE = "user_feel"

        const val USER_NAME_KEY = "user_name"
        const val USER_FEEL_KEY = "user_feel"
    }

    override fun save(user: User) {
        userNameSharedPreferences.edit()
            .putString(USER_NAME_KEY, user.name)
            .apply()

        userFeelSharedPreferences.edit()
            .putBoolean(USER_FEEL_KEY, user.isGood)
            .apply()
    }

    override fun find(): User? {
        val userName = (userNameSharedPreferences.all[USER_NAME_KEY] ?: return null) as String
        val isUserGood = (userFeelSharedPreferences.all[USER_FEEL_KEY] ?: return null) as Boolean

        return User(userName, isUserGood)
    }

}
