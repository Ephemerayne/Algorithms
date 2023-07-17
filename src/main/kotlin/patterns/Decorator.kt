package patterns

/**
структурный паттерн проектирования, который позволяет динамически добавлять объектам новую функциональность,
оборачивая их в полезные «обёртки».

Применимость
Когда вам нужно добавлять обязанности объектам на лету, незаметно для кода, который их использует.

Объекты помещают в обёртки, имеющие дополнительные поведения. Обёртки и сами объекты имеют одинаковый интерфейс,
поэтому клиентам без разницы, с чем работать — с обычным объектом данных или с обёрнутым.

Когда нельзя расширить обязанности объекта с помощью наследования.

Во многих языках программирования есть ключевое слово final, которое может заблокировать наследование класса.
Расширить такие классы можно только с помощью Декоратора.
 */

interface MyPrinter {
    fun printedText() : String
}

/**
 * returns "Hello"
 *
 */
class HelloPrinter : MyPrinter {
    override fun printedText() : String {
        return "Hello"
    }
}

/**
 * adds a comma to the previous value of the printedText() function
 *
 */
class CommaPrinter(private val printer: MyPrinter) : MyPrinter {
    override fun printedText() : String {
        return "${printer.printedText()},"
    }
}

/**
 * adds a space to the previous value of the printedText() function
 *
 */
class SpacePrinter(private val printer: MyPrinter) : MyPrinter {
    override fun printedText() : String {
        return "${printer.printedText()} "
    }
}

/**
 * adds the word World to the previous value of the printedText() function
 *
 */
class WorldPrinter(private val printer: MyPrinter) : MyPrinter {
    override fun printedText() : String {
        return "${printer.printedText()}World"
    }
}

/**
 * adds an exclamation mark to the previous value of the printedText() function
 *
 */
class ExclamationPrinter(private val printer: MyPrinter) : MyPrinter {
    override fun printedText() : String {
        return "${printer.printedText()}!"
    }
}