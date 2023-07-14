package codewars

fun AbbreviateATwo() {
    abbrevName("Sam Harris")
    abbrevName("Patrick Feenan")
    abbrevName("Evan Cole")
    abbrevName("P Favuzzi")
    abbrevName("David Mendieta")
}

private fun abbrevName(name: String): String {
    if (name.isEmpty()) return ""

    val formatted = StringBuilder(name).split(" ")

    val nameAbb = formatted[0].first().toUpperCase()
    val surnameAbb = formatted[1].first().uppercaseChar()

    val formattedString = StringBuilder("$nameAbb.$surnameAbb")

    return formattedString.toString()
}