package codewars

fun MultiplicationTable() {
    multiplicationTable(3)
}

private fun multiplicationTable(size: Int): Array<IntArray> {
    return Array(size) { a ->
        IntArray(size) { b ->
          (b + 1) * (a + 1)
        }.apply { println(this.toList()) }
    }
}