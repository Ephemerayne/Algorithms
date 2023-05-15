package codewars

import java.lang.StringBuilder

fun testFixed() {
   realization("8 8 Bi fk8h B 8 BB8B B B  B888 c hl8 BhB fd")
   realization("8aaaaa dddd r     ")
}

fun noSpace(x: String): String {
    val result = x.replace(" ", "")
    println(result)
  return x.replace(" ", "")
}

fun realization(x: String): String {
    val sb = StringBuilder()
    for (char in x) {
        if (!char.isWhitespace()) {
           sb.append(char).toString()
        }
    }
    println(sb.toString())
    return sb.toString()
}