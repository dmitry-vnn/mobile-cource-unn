package dmitry.mobilecourseunnlab3.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonHoldBinding
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ButtonHoldFragment : Fragment() {

    private lateinit var binding: FragmentButtonHoldBinding
    private var buttonIsToggle: Boolean by Delegates.observable(
        false, ::onButtonToggle
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentButtonHoldBinding.inflate(inflater, container, false)
        initElements()

        return binding.root;
    }

    private fun initElements() {
        binding.button.setOnClickListener(::onButtonClick)
        buttonIsToggle = true
    }

    private fun onButtonToggle(property: KProperty<*>, oldValue: Boolean, newValue: Boolean) {
        binding.textButtonStatus.text = if (newValue) "Кнопка нажата" else "Кнопка отпущена"
        binding.button.setBackgroundColor(if (newValue) Color.MAGENTA else Color.GRAY)
    }

    private fun onButtonClick(view: View) {
        buttonIsToggle = !buttonIsToggle
    }


}


