package codewars

fun EvenOrOdd() {
    evenOrOdd(2)
    evenOrOdd(0)
    evenOrOdd(7)
    evenOrOdd(1)
}

fun evenOrOdd(number: Int): String {
    return if (number % 2 == 0) "Even" else "Odd"
}