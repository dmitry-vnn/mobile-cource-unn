package dmitry.mobilecourse.translategame

class Top100RussianEnglishDictionary : RussianEnglishDictionary {

    private var words = setOf(
        RussianEnglishRecord("Привет", "Hi"),
        RussianEnglishRecord("Дело", "Deal"),
        RussianEnglishRecord("Слово", "Word"),
        RussianEnglishRecord("Текст", "Text"),
        RussianEnglishRecord("Утро", "Morning"),
        RussianEnglishRecord("Вчера", "Yesterday"),
        RussianEnglishRecord("Завтра", "Tomorrow"),
        RussianEnglishRecord("Закат", "Sunset"),
        RussianEnglishRecord("Луна", "Moon"),
        RussianEnglishRecord("Стул", "Chair"),
        RussianEnglishRecord("Стол", "Table"),
        RussianEnglishRecord("Виски", "Whiskey"),
        RussianEnglishRecord("Кола", "Cola"),
        RussianEnglishRecord("Удивительно", "Amazing"),
        RussianEnglishRecord("Восхитительно", "Perfect"),
        RussianEnglishRecord("Шутка", "Joke"),
    )


    override fun iterator(): Iterator<RussianEnglishRecord> {
        return words.iterator()
    }
}