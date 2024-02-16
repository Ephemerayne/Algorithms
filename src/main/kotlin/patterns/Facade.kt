package patterns

/**
структурный паттерн проектирования, который предоставляет простой интерфейс к сложной системе классов,
библиотеке или фреймворку.

Применимость
Когда вам нужно представить простой или урезанный интерфейс к сложной подсистеме.

Часто подсистемы усложняются по мере развития программы. Применение большинства паттернов приводит к появлению
меньших классов, но в бóльшем количестве. Такую подсистему проще повторно использовать, настраивая её каждый раз под
конкретные нужды, но вместе с тем, применять подсистему без настройки становится труднее. Фасад предлагает определённый
вид системы по умолчанию, устраивающий большинство клиентов.

Когда вы хотите разложить подсистему на отдельные слои.

Используйте фасады для определения точек входа на каждый уровень подсистемы. Если подсистемы зависят друг от друга,
то зависимость можно упростить, разрешив подсистемам обмениваться информацией только через фасады.

Например, возьмём ту же сложную систему видеоконвертации. Вы хотите разбить её на слои работы с аудио и видео.
Для каждой из этих частей можно попытаться создать фасад и заставить классы аудио и видео обработки общаться друг с
другом через эти фасады, а не напрямую.
 */


/**
 * imitation of local data storage (database)
 *
 */
class LocalDataSource {

    private val data = mutableListOf<String>()

    fun save(data: List<String>) {
        this.data.addAll(data)
    }

    fun read() = data
    fun isEmpty() = data.isEmpty()

}

/**
 * network request simulation
 *
 */
class NetworkDataSource {
    fun get() = listOf(
        "Harry Potter",
        "Ronald Weasley",
        "Hermione Granger"
    )
}

class Repository(private val localSource: LocalDataSource, private val networkSource: NetworkDataSource) {

    fun fetch(): List<String> {
        // I omitted error handling for simplicity
        if (localSource.isEmpty()) {
            val data = networkSource.get()
            localSource.save(data)
        }
        return localSource.read()
    }

}

/* ПРИМЕР 2 */


// Система, с которой будет взаимодействовать клиентский код
class SubsystemA {
    fun operationA(): String {
        return "Subsystem A operation"
    }
}

class SubsystemB {
    fun operationB(): String {
        return "Subsystem B operation"
    }
}

class SubsystemC {
    fun operationC(): String {
        return "Subsystem C operation"
    }
}

// Фасад, предоставляющий унифицированный интерфейс для подсистемы
class Facade(
    private val subsystemA: SubsystemA,
    private val subsystemB: SubsystemB,
    private val subsystemC: SubsystemC
) {
    fun operate(): String {
        val resultA = subsystemA.operationA()
        val resultB = subsystemB.operationB()
        val resultC = subsystemC.operationC()

        return "$resultA\n$resultB\n$resultC"
    }
}

// Пример использования
fun main() {
// Клиентский код взаимодействует с фасадом, а не напрямую с подсистемой
    val subsystemA = SubsystemA()
    val subsystemB = SubsystemB()
    val subsystemC = SubsystemC()

    val facade = Facade(subsystemA, subsystemB, subsystemC)

// Клиент вызывает операцию через фасад
    val result = facade.operate()

// Вывод результата
    println(result)
}

/*
В данном примере:
SubsystemA, SubsystemB, и SubsystemC представляют собой подсистемы с различной функциональностью.
Facade предоставляет унифицированный интерфейс для взаимодействия с подсистемами.
Метод operate выполняет операции внутри подсистем и возвращает их результаты объединенными.

Клиентский код взаимодействует с фасадом, что упрощает ему работу с подсистемой.
Фасад позволяет скрыть сложность подсистемы и предоставляет упрощенный интерфейс для клиентов.
Это способствует уменьшению зависимостей и повышению уровня абстракции в системе.
 */


