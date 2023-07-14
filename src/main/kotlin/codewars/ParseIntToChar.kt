package codewars

fun ParseIntToChar() {
    getAge("4 years old")
    getAge("5 years old")
    getAge("7 years old")
}

private fun getAge(yearsOld: String) = yearsOld.filter { it.isDigit() }.toInt()