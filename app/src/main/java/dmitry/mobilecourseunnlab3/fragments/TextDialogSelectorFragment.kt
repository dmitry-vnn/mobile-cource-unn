package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentTextDialogSelectorBinding
import dmitry.mobilecourseunnlab3.dialogs.InputTextDialog

class TextDialogSelectorFragment : Fragment() {

    private lateinit var binding: FragmentTextDialogSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextDialogSelectorBinding.inflate(inflater, container, false)

        binding.openDialogButton.setOnClickListener(::onOpenDialogButtonClick)

        return binding.root
    }

    private fun onOpenDialogButtonClick(view: View?) {
        InputTextDialog(::onDialogAccept).show(parentFragmentManager, "InputTextDialog")
    }

    private fun onDialogAccept(inputText: String) {
        binding.someText.text = inputText
    }

}