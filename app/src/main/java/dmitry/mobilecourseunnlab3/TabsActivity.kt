package dmitry.mobilecourseunnlab3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dmitry.mobilecourseunnlab3.databinding.ActivityTabsBinding
import dmitry.mobilecourseunnlab3.fragments.MainTabFragment

class TabsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerStateAdapter(this)

        viewPagerAdapter.addFragment(MainTabFragment(viewPagerAdapter), "Главная")

        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
        }.attach()
    }

    inner class ViewPagerStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

        private val fragments = mutableListOf<Fragment>()
        private val titles = mutableListOf<String>()

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
            notifyItemInserted(fragments.size - 1)
        }

        fun removeFragment(position: Int) {
            fragments.removeAt(position)
            titles.removeAt(position)
            notifyItemRemoved(position)
        }

        fun getPageTitle(position: Int): String {
            return titles[position]
        }
    }
}

