package dmitry.mobilecourse.notes.repository

import dmitry.mobilecourse.notes.models.Note

interface NotesRepository {

    fun save(note: Note)

    fun remove(note: Note)

    fun findAll(): List<Note>

}