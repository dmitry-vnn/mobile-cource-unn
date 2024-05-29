package dmitry.mobilecourse.translategame

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityTranslateGameBinding

class TranslateGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTranslateGameBinding
    private lateinit var translateGame: TranslateGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        translateGame = TranslateGame(
            Top100RussianEnglishDictionary(),
            onScoreChangeObserver = ::onScoreChange,
            onNextWordIteration = ::onNextWord
        ).apply { start() }
    }

    private fun onNextWord(word: String) {
        binding.englishWordInput.setText("")
        binding.russianWordText.text = word
    }

    private fun onScoreChange(score: Int) {
        binding.scoreText.text = score.toString()
    }

    fun onSubmitButtonClick(view: View) {
        if (!translateGame.submitWord(binding.englishWordInput.text.toString())) {
            showTextDialog("Incorrect translation :(", translateGame::chooseNextWord)
        } else {
            translateGame.chooseNextWord()
        }
    }

    private fun showTextDialog(text: String, callback: () -> Unit = {}) {
        AlertDialog.Builder(this)
            .setTitle("")
            .setMessage(text)
            .setOnCancelListener { callback() }
            .show()
    }

    fun onCheckButtonClick(view: View) {
        if (translateGame.tryGuessWord(binding.englishWordInput.text.toString())) {
            showTextDialog("Your translation is correct!")
        } else {
            showTextDialog("Incorrect translation :(")
        }
    }
}