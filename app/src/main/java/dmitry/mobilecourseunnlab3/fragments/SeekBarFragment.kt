package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentSeekBarBinding

class SeekBarFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: FragmentSeekBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSeekBarBinding.inflate(inflater, container, false)
        initialize()

        return binding.root;
    }

    private fun initialize() {
        binding.seekBar.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        binding.textSeekValue.text = progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}


