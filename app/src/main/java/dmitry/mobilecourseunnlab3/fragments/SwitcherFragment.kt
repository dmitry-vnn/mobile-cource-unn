package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentSwitcherBinding

class SwitcherFragment : Fragment() {

    private lateinit var binding: FragmentSwitcherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSwitcherBinding.inflate(inflater, container, false)
        initialize()

        return binding.root;
    }

    private fun initialize() {
        binding.switcher.setOnCheckedChangeListener { _, isChecked ->
            binding.label.text = if (isChecked) "По***изм: Включен" else "По***изм: Выключен"
        }
    }



}


