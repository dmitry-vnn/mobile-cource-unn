package dmitry.mobilecourseunnlab3.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentDatePickerDialogSelectorBinding
import dmitry.mobilecourseunnlab3.databinding.FragmentTimePickerDialogSelectorBinding
import dmitry.mobilecourseunnlab3.dialogs.CustomDatePickerDialog
import dmitry.mobilecourseunnlab3.dialogs.CustomTimePickerDialog
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimePickerDialogSelectorFragment : Fragment() {

    private lateinit var binding: FragmentTimePickerDialogSelectorBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimePickerDialogSelectorBinding.inflate(inflater, container, false)

        binding.openDialogButton.setOnClickListener(::onOpenDialogButtonClick)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onOpenDialogButtonClick(view: View?) {
        CustomTimePickerDialog(::onTimePicked).show(parentFragmentManager, "CustomTimePickerDialog")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onTimePicked(timePicker: TimePicker?, hour: Int, minute: Int) {
        val formattedTime = LocalTime.of(hour, minute).format(DateTimeFormatter.ofPattern("HH:mm"))
        binding.someText.text = formattedTime
    }

}