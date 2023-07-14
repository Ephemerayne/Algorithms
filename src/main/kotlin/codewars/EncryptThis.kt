package codewars

fun EncryptThis() {
    encryptThis("A wise old owl lived in an oak")
    encryptThis("The more he saw the less he spoke")
    encryptThis("The less he spoke the more he heard")
    encryptThis("Why can we not all be like that wise old bird")
    encryptThis("Thank you Piotr for all your help")
}

private fun encryptThis(text: String): String {
    val listOfWords = text.split(" ")
    val result = arrayListOf<String>()

    for (word in listOfWords) {
        val formattedWord = replaceFirstChar(replaceSecondAndLastChar(word))
        result.add(formattedWord)
    }

    val encryptedString = result.fold("") { acc, cur -> "$acc $cur" }.trim()

    println("debug: $encryptedString")
    return encryptedString
}

private fun replaceFirstChar(word: String): String {
    val code = word.first().code
   return word.replaceFirstChar { "$code" }
}

private fun replaceSecondAndLastChar(word: String): String {
    if (word.length == 1) return word

    val secondLetter = word[1]
    val lastLetter = word.last()

    val stringBuilder = StringBuilder(word)
    val formattedWord = stringBuilder
        .replace(1, 2, lastLetter.toString())
        .replace(word.length - 1, word.length, secondLetter.toString()).toString()
        .trim()

    return formattedWord
}