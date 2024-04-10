package dmitry.mobilecourseunnlab3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitry.mobilecourseunnlab3.TabsActivity
import dmitry.mobilecourseunnlab3.databinding.FragmentMainTabBinding

class MainTabFragment(private val adapter: TabsActivity.ViewPagerStateAdapter) : Fragment() {

    private lateinit var binding: FragmentMainTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTabBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        adapter = (requireActivity() as TabsActivity).viewPagerAdapter
        initButtonsClickListeners()
    }

    private fun initButtonsClickListeners() {
        binding.addTabButton.setOnClickListener(::onAddTabClick)
        binding.removeTabButton.setOnClickListener(::onRemoveTabClick)
    }

    private fun onRemoveTabClick(view: View?) {
        if (adapter.itemCount > 1) {
            adapter.removeFragment(adapter.itemCount - 1)
        }

        if (adapter.itemCount == 1) {
            binding.removeTabButton.visibility = View.GONE
        }
    }

    private fun onAddTabClick(view: View?) {
        adapter.addFragment(BaseFragment(), "Вкладка ${adapter.itemCount}")
        binding.removeTabButton.visibility = View.VISIBLE
    }
}