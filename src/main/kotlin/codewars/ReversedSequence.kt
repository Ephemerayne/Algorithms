package codewars

fun ReversedSequence() {
    reverseSeq(5)
}

private fun reverseSeq(n: Int): List<Int> {
    var i = 0
    val listOfNums = arrayListOf<Int>()

    while (i < n) {
        i++

        listOfNums.add(i)
    }

    return listOfNums.reversed()
}