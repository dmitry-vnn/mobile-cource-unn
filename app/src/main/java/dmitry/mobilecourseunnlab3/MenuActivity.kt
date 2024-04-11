package dmitry.mobilecourseunnlab3

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import dmitry.mobilecourseunnlab3.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)

        val navView = binding.navView
        navView.setOnItemSelectedListener(::onItemMenuSelected)
        setContentView(binding.root)
    }


    private fun onItemMenuSelected(menuItem: MenuItem): Boolean {
        Log.i("MenuItemSelect", menuItem.title.toString())
        return true
    }

}