package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.R
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonDateAndTimePickerBinding
import dmitry.mobilecourseunnlab3.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentSpinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        initializeSpinner()

        return binding.root;
    }

    private fun initializeSpinner() {
        val spinner = binding.spinner

        spinner.onItemSelectedListener = this

        val arrayAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.presidents_array,
            android.R.layout.simple_spinner_item
        )

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i("Spinner", binding.spinner.adapter.getItem(position).toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}


