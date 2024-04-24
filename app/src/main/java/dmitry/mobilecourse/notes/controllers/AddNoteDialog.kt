package dmitry.mobilecourse.notes.controllers

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dmitry.mobilecourse.databinding.DialogAddNoteBinding

class AddNoteDialog(private val callbackReceiver: (String) -> Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = DialogAddNoteBinding.inflate(layoutInflater)

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("Подтвердить") { _, _ ->
                    dialog?.apply {
                        val inputText = binding.noteEditText
                        callbackReceiver(inputText.text.toString())
                    }
                }
                .setNegativeButton("Отменить") { _, _ -> }
            builder.create()
        }

    }

}