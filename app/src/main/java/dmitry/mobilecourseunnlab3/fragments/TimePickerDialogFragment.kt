package dmitry.mobilecourseunnlab3.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        val hourField = calendar.get(Calendar.HOUR_OF_DAY)
        val minuteField = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hourField, minuteField, true)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        Log.i(
            "TimePicker",
            LocalTime.of(hourOfDay, minute).format(DateTimeFormatter.ofPattern("HH:mm"))
        )
    }


}


