package dmitry.mobilecourse.preferences

interface PreferenceStorage {

    fun save(user: User)

    fun find(): User?

}