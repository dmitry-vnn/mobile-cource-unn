package dmitry.mobilecourseunnlab3.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonHoldBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()

        val yearField = calendar.get(Calendar.YEAR)
        val monthField = calendar.get(Calendar.MONTH)
        val dayField = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, yearField, monthField, dayField)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        Log.i(
            "DatePicker",
            LocalDate.of(year, month + 1, dayOfMonth).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        )
    }


}


