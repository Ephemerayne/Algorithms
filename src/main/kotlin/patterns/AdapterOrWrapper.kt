package patterns

/*
Паттерн "Wrapper" (или "Adapter") — это структурный паттерн проектирования, который позволяет объектам с несовместимыми
интерфейсами работать вместе. Этот паттерн создает прослойку (wrapper) с унифицированным интерфейсом для взаимодействия
с объектами, чей интерфейс не совпадает с ожидаемым.
 */

// Интерфейс, который ожидается клиентским кодом
interface Targett {
    fun request(): String
}

// Класс, чей интерфейс не соответствует ожидаемому
class Adapteee {
    fun specificRequest(): String {
        return "Specific request"
    }
}

// Обертка (Wrapper), адаптирующая интерфейс Adaptee к интерфейсу Target
class Adapterrr(private val adaptee: Adapteee) : Target {
    override fun request(): String {
        // Вызываем метод specificRequest() через Adaptee
        return "Adapter: ${adaptee.specificRequest()}"
    }
}

fun clientCode(target: Targett) {
    println(target.request())
}

fun main() {
    // Создаем объект Adaptee
    val adapteee = Adapteee()

    // Создаем Adapter и передаем ему Adaptee
    val adapter = Adapterrr(adapteee)

    // Клиентский код работает с объектом, ожидающим интерфейс Target
    clientCode(adapter)
}

/*
В данном примере:

Targett - это интерфейс, ожидаемый клиентским кодом.
Adapteee - это класс, чей интерфейс не соответствует ожидаемому интерфейсу Targett.
Adapterrr - это класс-обертка, который адаптирует интерфейс Adapteee к интерфейсу Targett. Метод request делегирует вызов
метода specificRequest объекта Adapteee.
Клиентский код вызывает метод request через объект Adapterrr, что позволяет взаимодействовать с объектом Adapteee,
используя ожидаемый интерфейс Targett.
 */