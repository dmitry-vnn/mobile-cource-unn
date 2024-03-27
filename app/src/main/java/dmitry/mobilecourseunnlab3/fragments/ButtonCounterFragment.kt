package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonClickerBinding
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ButtonCounterFragment : Fragment() {

    private lateinit var binding: FragmentButtonClickerBinding
    private var counter: UInt by Delegates.observable(
        0u, ::onButtonToggle
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentButtonClickerBinding.inflate(inflater, container, false)
        initElements()

        return binding.root;
    }

    private fun initElements() {
        binding.button.setOnClickListener(::onButtonClick)
    }

    private fun onButtonToggle(property: KProperty<*>, oldValue: UInt, newValue: UInt) {
        binding.button.text = "Нажатий: $newValue"
    }

    private fun onButtonClick(view: View) {
        counter++
    }


}


