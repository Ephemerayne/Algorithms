package patterns

/*
Паттерн "Итератор" (Iterator) предоставляет унифицированный интерфейс для последовательного доступа к элементам коллекции,
не раскрывая её внутреннего представления. Этот паттерн может быть полезен для обеспечения замены конкретных итераторов
без изменения клиентского кода.
 */

// Интерфейс итератора
interface Iterator<T> {
    fun hasNext(): Boolean
    fun next(): T
}

// Интерфейс коллекции
interface Aggregate<T> {
    fun iterator(): Iterator<T>
}

// Конкретная реализация итератора для списка
class ListIterator<T>(private val list: List<T>) : Iterator<T> {
    private var index = 0

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): T {
        return if (hasNext()) {
            val element = list[index]
            index++
            element
        } else {
            throw NoSuchElementException()
        }
    }
}

// Конкретная реализация коллекции - список
class ListAggregate<T>(private val list: List<T>) : Aggregate<T> {
    override fun iterator(): Iterator<T> {
        return ListIterator(list)
    }
}

// Пример использования

fun main() {
    val elements = listOf("A", "B", "C", "D")

// Создаем объект коллекции
    val aggregate: Aggregate<String> = ListAggregate(elements)

// Получаем итератор
    val iterator: Iterator<String> = aggregate.iterator()

// Используем итератор для последовательного доступа к элементам
    while (iterator.hasNext()) {
        val element = iterator.next()
        println(element)
    }
}

/*
В данном примере:

Iterator - интерфейс итератора с методами hasNext() и next().
Aggregate - интерфейс коллекции с методом iterator().
ListIterator - конкретная реализация итератора для списка.
ListAggregate - конкретная реализация коллекции - списка.

Итератор предоставляет стандартные методы для перемещения по коллекции, а коллекция (в данном случае, список)
предоставляет метод iterator(), который возвращает соответствующий итератор. Клиентский код может использовать итератор
для последовательного доступа к элементам коллекции, при этом не зная её внутреннего устройства.
 */