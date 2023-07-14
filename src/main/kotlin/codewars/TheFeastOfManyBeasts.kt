package codewars

fun TheFeastOfManyBeasts() {
    feast("great blue heron", "garlic naan")
    feast("chickadee", "chocolate cake")
    feast("brown bear", "bear claw")
    feast("marmot", "mulberry tart")
    feast("porcupine", "pie")
    feast("electric eel", "lasagna")
    feast("slow loris", "salsas")
    feast("ox", "orange lox")
    feast("blue-footed booby", "blueberry")
}

private fun feast(beast: String, dish: String): Boolean {
    val beastFirstLetter = beast.first()
    val beastLastLetter = beast.last()
    val dishFirstLetter = dish.first()
    val dishLastLetter = dish.last()

    return beastFirstLetter == dishFirstLetter && beastLastLetter == dishLastLetter
}