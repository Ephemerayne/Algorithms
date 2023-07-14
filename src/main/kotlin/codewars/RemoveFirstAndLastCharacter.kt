package codewars

fun RemoveFirstAndLastCharacter() {
    removeChar("eloquent")
    removeChar("country")
    removeChar("person")
    removeChar("place")
}

private fun removeChar(str: String) = str.drop(1).dropLast(1)