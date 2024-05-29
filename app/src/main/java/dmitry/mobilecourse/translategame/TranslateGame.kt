package dmitry.mobilecourse.translategame

import java.util.Locale

class TranslateGame(
    private val russianEnglishDictionary: RussianEnglishDictionary,
    private val onScoreChangeObserver: (Int) -> Unit,
    private val onNextWordIteration: (String) -> Unit,
) {

    private lateinit var dictionaryIterator: Iterator<RussianEnglishRecord>
    private lateinit var lastDictionaryRecord: RussianEnglishRecord
    
    private var score = 0

    fun start() {
        dictionaryIterator = russianEnglishDictionary.iterator()
        chooseNextWord()
    }

    fun chooseNextWord() {
        if (!dictionaryIterator.hasNext()) {
            return
        }
        lastDictionaryRecord = dictionaryIterator.next()
        onNextWordIteration(lastDictionaryRecord.russianWord)
    }

    fun tryGuessWord(engWord: String): Boolean {
        return lastDictionaryRecord.englishWord.lowercase(Locale.ROOT) == engWord.lowercase(Locale.ROOT)
    }

    fun submitWord(engWord: String): Boolean {
        val guessed = tryGuessWord(engWord)

        if (guessed) {
            score++
            onScoreChangeObserver(score)
        }

        return guessed
    }

}