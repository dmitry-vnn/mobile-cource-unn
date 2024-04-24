package dmitry.mobilecourse.preferences

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityPreferencesBinding

class PreferenceActivity: AppCompatActivity() {

    private lateinit var switcher: Switch
    private lateinit var nameEditText: EditText

    private lateinit var preferencesStorage: PreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPreferencesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        preferencesStorage = SplitPreferencesStorage(applicationContext)

        nameEditText = binding.nameEditText
        switcher = binding.switcher

        binding.saveButton.setOnClickListener(::onSaveButtonClick)

        loadPreferences()
    }

    private fun loadPreferences() {
        preferencesStorage.find()?.apply {
            switcher.isChecked = isGood
            nameEditText.setText(name)
        }
    }

    private fun onSaveButtonClick(view: View?) {
        val user = User(nameEditText.text.toString(), switcher.isChecked)
        preferencesStorage.save(user)
    }

}