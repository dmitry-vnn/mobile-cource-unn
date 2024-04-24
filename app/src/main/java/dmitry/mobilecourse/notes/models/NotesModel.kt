package dmitry.mobilecourse.notes.models

import dmitry.mobilecourse.notes.repository.NotesRepository

class NotesModel(private val observer: Observer, private val notesRepository: NotesRepository) {

    fun loadFromDatabase() {
        notesRepository.findAll().forEach(observer::onNoteAdded)
    }

    fun addNote(note: Note) {
        notesRepository.save(note)
        observer.onNoteAdded(note)
    }

    fun removeNote(note: Note) {
        notesRepository.remove(note)
        observer.onNoteRemove(note)
    }

    interface Observer {
        fun onNoteAdded(note: Note)
        fun onNoteRemove(note: Note)
    }
}