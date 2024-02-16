package patterns

/**
структурный паттерн проектирования, который позволяет объектам с несовместимыми интерфейсами работать вместе.

Применимость
Когда вы хотите использовать сторонний класс, но его интерфейс не соответствует остальному коду приложения.

Адаптер позволяет создать объект-прокладку, который будет превращать вызовы приложения в формат, понятный
стороннему классу.

Когда вам нужно использовать несколько существующих подклассов, но в них не хватает какой-то общей функциональности,
причём расширить суперкласс вы не можете.

Вы могли бы создать ещё один уровень подклассов и добавить в них недостающую функциональность. Но при этом придётся
дублировать один и тот же код в обеих ветках подклассов.

Более элегантным решением было бы поместить недостающую функциональность в адаптер и приспособить его для работы с
суперклассом. Такой адаптер сможет работать со всеми подклассами иерархии. Это решение будет сильно напоминать
паттерн Декоратор.

 */

interface Adapter<T> {
    fun getItem(position: Int): T
    fun getItemCount(): Int
}

/**
 * here is a simplified imitation of RecyclerView component from android
 */
class RecyclerView<T> {

    private var adapter: Adapter<T>? = null

    fun changeAdapter(adapter: Adapter<T>) {
        this.adapter = adapter
    }

    /**
     * renders elements from the adapter
     *
     * @return returns a list of elements to test
     */
    fun draw(): List<T> {
        val items = mutableListOf<T>()
        val myAdapter = adapter
        if (myAdapter != null) {
            val count = myAdapter.getItemCount()
            for (i in 0 until count) {
                items.add(myAdapter.getItem(i))
                // draw item
            }
        }
        return items
    }
}









/* ПРИМЕР 2 */

// Существующий класс с несовместимым интерфейсом
class Adaptee {
    fun specificRequest(): String {
        return "Specific request"
    }
}

// Целевой интерфейс, который ожидает клиентский код
interface Target {
    fun request(): String
}

// Адаптер, преобразующий интерфейс Adaptee в Target
class Adapterr(private val adaptee: Adaptee) : Target {
    override fun request(): String {
        return "Adapter: ${adaptee.specificRequest()}"
    }
}

// Клиентский код, ожидающий объект типа Target
fun clientCode(target: Target) {
    println(target.request())
}

// Пример использования

fun main() {
// Создаем объект Adaptee
    val adaptee = Adaptee()

// Создаем адаптер, который преобразует интерфейс Adaptee в Target
    val adapter = Adapterr(adaptee)

// Клиентский код работает с объектом типа Target
    clientCode(adapter)
}