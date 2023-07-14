package codewars

import kotlin.text.StringBuilder

fun HidePasswordFromJdbcUrl() {
    hidePasswordFromConnection("jdbc:mysql://sdasdasdasd:szdasdasd:dfsdfsdfsdf/sdfsdfsdf?user=root&password=12345&hello123")
}

private fun hidePasswordFromConnection(urlString: String): String {
    val passwordKeyLength = "password=".length
    val startIndex = urlString.indexOf("password=") + passwordKeyLength
    println("startIndex: $startIndex")

    var endIndex = urlString.substringAfter("password=").indexOf("&")
    endIndex = if (endIndex == -1) {
        urlString.length
    } else {
        endIndex + startIndex
    }

    println("endIndex: $endIndex")

    val result = StringBuilder(urlString)
        .replace(
            startIndex,
            endIndex,
            "*".repeat(endIndex - startIndex)
        ).toString()

    println("result: $result")

    return result

//    val url = urlString.substringBefore("password=")
//    val password = urlString.substringAfter("password=")
//    val waste = password.substringAfterLast("&")
//
//    val pass = password.substringBeforeLast("&")
//
//    val stringBuilder = StringBuilder()
//
//    println("waaste: $waste")
//
//    stringBuilder.append(url)
//    stringBuilder.append("password=")
//
//    repeat(pass.length) {
//        stringBuilder.append("*")
//    }
//
//    stringBuilder.append(waste)
//
//    println("$stringBuilder")

//    val password = urlString.filter { it.isDigit() }
//
//    val hidePassword = StringBuilder()
//    repeat(password.length) {
//        hidePassword.append("*")
//    }
//
//    val formattedUrl = urlString.replace(password, hidePassword.toString())
//
//
//    println("debug: $formattedUrl")


//    val password = urlString.substringAfter("password=")
//
//    val s = urlString.substringBeforeLast("=")
//    val stringBuilder = StringBuilder()
//
//    stringBuilder
//        .append(s)
//        .append("=")
//        .apply {
//            repeat(password.length) {
//                append("*")
//            }
//        }
//
//    return stringBuilder.toString()
}