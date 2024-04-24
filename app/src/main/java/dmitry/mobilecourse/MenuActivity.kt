package dmitry.mobilecourse

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityMenuBinding

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