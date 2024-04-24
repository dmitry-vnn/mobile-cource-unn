package dmitry.mobilecourse.notes.controllers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import dmitry.mobilecourse.databinding.ActivityNotesBinding
import dmitry.mobilecourse.databinding.ItemNoteBinding
import dmitry.mobilecourse.notes.models.Note
import dmitry.mobilecourse.notes.models.NotesModel
import dmitry.mobilecourse.notes.repository.SqliteNotesRepository

class NotesActivity: AppCompatActivity(), NotesModel.Observer {

    private lateinit var notesModel: NotesModel
    private lateinit var column: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        column = binding.column
        notesModel = NotesModel(this, SqliteNotesRepository(applicationContext))
        notesModel.loadFromDatabase()
    }

    fun onAddNoteButtonClick(view: View) {
        AddNoteDialog(::onNoteCreateButtonClick).show(supportFragmentManager, AddNoteDialog::class.simpleName)
    }

    private fun onNoteCreateButtonClick(title: String) {
        val note = Note(title)
        notesModel.addNote(note)
    }

    override fun onNoteAdded(note: Note) {
        val binding = ItemNoteBinding.inflate(layoutInflater)

        binding.noteTitleText.text = note.title

        val noteView = binding.root
        noteView.tag = note

        noteView.setOnClickListener { notesModel.removeNote(note) }

        column.addView(noteView)
    }

    override fun onNoteRemove(note: Note) {
        for (i in 0..<column.childCount) {
            if (column.getChildAt(i).tag == note) {
                column.removeViewAt(i)
                return
            }
        }
    }

}