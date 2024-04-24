package dmitry.mobilecourse.notes.repository

import dmitry.mobilecourse.notes.models.Note

object MockNotesRepository: NotesRepository {

    private var idGenerator: Long = 1

    private val notes = hashSetOf<Note>().also {
        it += Note("Заметка 1").apply { id = idGenerator++ }
        it += Note("Заметка 2").apply { id = idGenerator++ }
        it += Note("Заметка 3").apply { id = idGenerator++ }
    }

    override fun save(note: Note) {
        note.id = idGenerator++
        notes += note
    }

    override fun remove(note: Note) {
        notes -= note
    }

    override fun findAll(): List<Note> {
        return notes.toList()
    }
}