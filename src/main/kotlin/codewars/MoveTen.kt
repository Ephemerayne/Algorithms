package codewars

import kotlin.math.absoluteValue

fun MoveTen() {
    moveTen("testcase")
//    moveTen("codewars")
//    moveTen("exampletesthere")
}

fun moveTen(s: String): String = s.map { 'a' + (((it - 'a') + 10) % 26) }.joinToString("")