package dmitry.mobilecourse.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import dmitry.mobilecourse.databinding.FragmentDatePickerDialogSelectorBinding
import dmitry.mobilecourse.dialogs.CustomDatePickerDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DatePickerDialogSelectorFragment : Fragment() {

    private lateinit var binding: FragmentDatePickerDialogSelectorBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatePickerDialogSelectorBinding.inflate(inflater, container, false)

        binding.openDialogButton.setOnClickListener(::onOpenDialogButtonClick)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onOpenDialogButtonClick(view: View?) {
        CustomDatePickerDialog(::onDatePicked).show(parentFragmentManager, "CustomDatePickerDialog")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onDatePicked(datePicker: DatePicker?, year: Int, month: Int, day: Int) {
        val formattedDate =
            LocalDate.of(year, month + 1, day).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        binding.someText.text = formattedDate
    }

}