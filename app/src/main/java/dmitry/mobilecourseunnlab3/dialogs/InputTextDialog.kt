package dmitry.mobilecourseunnlab3.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dmitry.mobilecourseunnlab3.databinding.DialogInputTextBinding

class InputTextDialog(private val acceptReceiver: (String) -> Unit) : DialogFragment() {

    private lateinit var binding: DialogInputTextBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogInputTextBinding.inflate(layoutInflater)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("Подтвердить") { _, _ ->
                    dialog?.apply {
                        val inputText = binding.inputText
                        acceptReceiver(inputText.text.toString())
                    }
                }
                .setNegativeButton("Отменить") { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}