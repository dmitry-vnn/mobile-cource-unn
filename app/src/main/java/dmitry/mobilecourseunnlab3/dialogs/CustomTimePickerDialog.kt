package dmitry.mobilecourseunnlab3.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class CustomTimePickerDialog(private val callback: TimePickerDialog.OnTimeSetListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(), callback, currentHour, currentMinute, true)
    }

}