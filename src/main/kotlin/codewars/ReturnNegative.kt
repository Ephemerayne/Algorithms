package codewars

fun ReturnNegative() {
    makeNegative(42)
}

private fun makeNegative(x: Int): Int = when (x < 0) {
    true -> x
    false -> x * -1
}