package codewars

fun RepeatString() {
    repeatStr(4, "a")
    repeatStr(3, "Hello")
    repeatStr(5, "")
    repeatStr(0, "kata")
}

private fun repeatStr(r: Int, str: String): String {
    if (r == 0) return ""

    var i = 1
    val formattedString = StringBuilder(str)

    while (i in 1 until r) {
        formattedString.append(str)

        i++
    }

    println("debug: ${formattedString.toString()}")
    return formattedString.toString()
}