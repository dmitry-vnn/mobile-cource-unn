package dmitry.mobilecourse.notes.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dmitry.mobilecourse.notes.models.Note
import dmitry.mobilecourse.notes.repository.SqliteHelper.Companion.ID_COLUMN
import dmitry.mobilecourse.notes.repository.SqliteHelper.Companion.TABLE_NAME
import dmitry.mobilecourse.notes.repository.SqliteHelper.Companion.TITLE_COLUMN

class SqliteNotesRepository(context: Context) : NotesRepository {

    private val sqliteHelper = SqliteHelper(context)

    override fun save(note: Note) {
        sqliteHelper.writableDatabase?.use { db ->
            val values = ContentValues()
            values.put(TITLE_COLUMN, note.title)

            val generatedId = db.insert(TABLE_NAME, null, values)
            note.id = generatedId
        }
    }

    override fun remove(note: Note) {
        sqliteHelper.writableDatabase?.use { db ->
            db.delete(TABLE_NAME, "$ID_COLUMN = ?", arrayOf(note.id.toString()))
        }
    }

    @SuppressLint("Range")
    override fun findAll(): List<Note> {
        val result = mutableListOf<Note>()

        sqliteHelper.readableDatabase?.use {db ->
            val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    do {
                        val id = cursor.getLong(cursor.getColumnIndex(ID_COLUMN))
                        val title = cursor.getString(cursor.getColumnIndex(TITLE_COLUMN))!!
                        result += Note(title, id)
                    } while (cursor.moveToNext())
                }
            }
        }

        return result
    }

}

private class SqliteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_FILE, null, 1) {

    companion object {
        const val DATABASE_FILE = "notes.db"
        const val TABLE_NAME = "notes"

        const val ID_COLUMN = "id"
        const val TITLE_COLUMN = "title"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}
