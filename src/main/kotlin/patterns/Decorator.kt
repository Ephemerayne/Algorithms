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

/* Делегирование
* Делегирование представляет паттерн объектно-ориентированного программирования, который позволяет одному объекту
* делегировать/перенаправить все запросы другому объекту. В определенной степени делегирование может выступать
* альтернативой наследованию. И преимуществом Kotlin в данном случае состоит в том, что Kotlin нативно поддерживает
* данный паттерн, предоставляя необходимый инструментарий.
*
* Это т.н. шаблон "Декоратор".
Бывает нужно добавить функциональность к уже имеющемуся классу (сохранив при этом уже имеющуюся функциональность или
* возможно изменить только часть методов-родителя); сам класс для изменения может быть недоступен (например, Collections)
* */

interface Base {
    fun someFun()
}

class BaseImpl() : Base {
    override fun someFun() { }
}

class Derived(someBase: Base) : Base by someBase

class Test(val list: ArrayList<String>): List<String> by list {
    fun hello() {
        list.listIterator()
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}
