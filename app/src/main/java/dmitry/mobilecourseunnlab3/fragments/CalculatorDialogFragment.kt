package dmitry.mobilecourseunnlab3.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import dmitry.mobilecourseunnlab3.R

class CalculatorDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(R.layout.fragment_calculator_dialog)
                .setPositiveButton("Calculate") { _, _ ->
                    dialog?.apply {
                        val firstNumberInput = findViewById<EditText>(R.id.input_first_number)
                        val secondNumberInput = findViewById<EditText>(R.id.input_second_number)

                        val sum = firstNumberInput.text.toString()
                            .toInt() + secondNumberInput.text.toString().toInt()

                        Log.w(
                            "calculate result",
                            sum.toString()
                        )
                    }
                }
                .setNegativeButton("Cancel") { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}