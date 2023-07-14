package codewars

fun EasyBalanceChecking() {
    balance("1000.00!=\n125 Market !=:125.45\n126 Hardware =34.95\n127 Video! 7.45\n128 Book :14.32\n129 Gasoline ::16.10\n")
}

private fun balance(book: String): String {
    val originalBalance = "Original Balance: "
    val formattedString = (originalBalance + book)
        .replace(" ", "_")
        .replace("\\\\r\\\\n", "\n")
        .replaceAfter("\n", "Balance ")
        .replace("!=", "")
    println("deb: ${formattedString}")

   return book.replace(" ", "_").replace("\\\\r\\\\n", "\n")
}