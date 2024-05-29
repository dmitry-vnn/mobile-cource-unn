package dmitry.mobilecourse.translategame

interface RussianEnglishDictionary: Iterable<RussianEnglishRecord>

data class RussianEnglishRecord(val russianWord: String, val englishWord: String)
