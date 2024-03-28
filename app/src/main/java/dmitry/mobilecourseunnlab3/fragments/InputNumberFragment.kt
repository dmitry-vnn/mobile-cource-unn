package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dmitry.mobilecourseunnlab3.databinding.FragmentButtonHoldBinding
import dmitry.mobilecourseunnlab3.databinding.FragmentNumberInputBinding

class InputNumberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNumberInputBinding.inflate(inflater, container, false).root;
    }


}


