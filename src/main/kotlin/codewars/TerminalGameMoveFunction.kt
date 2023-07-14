package codewars

fun TerminalGameMoveFunction() {
    move(0, 4)
    move(3, 6)
    move(2, 5)
}

private fun move(pos: Int, roll: Int): Int {
    return (roll * 2) + pos
}