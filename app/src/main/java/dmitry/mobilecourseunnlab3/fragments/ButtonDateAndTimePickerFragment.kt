package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonDateAndTimePickerBinding

class ButtonDateAndTimePickerFragment : Fragment() {

    private lateinit var binding: FragmentButtonDateAndTimePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentButtonDateAndTimePickerBinding.inflate(inflater, container, false)
        initElements()

        return binding.root;
    }

    private fun initElements() {
        binding.buttonDatePicker.setOnClickListener(::onDatePickerButtonClick)
        binding.buttonTimePicker.setOnClickListener(::onTimePickerButtonClick)
    }

    private fun onDatePickerButtonClick(v: View?) {
        DatePickerDialogFragment().show(parentFragmentManager, "datePickerDialog")
    }

    private fun onTimePickerButtonClick(v: View?) {
        TimePickerDialogFragment().show(parentFragmentManager, "timePickerDialog")
    }


}


