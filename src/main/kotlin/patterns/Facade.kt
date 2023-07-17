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

    fun fetch() : List<String> {
        // I omitted error handling for simplicity
        if (localSource.isEmpty()) {
            val data = networkSource.get()
            localSource.save(data)
        }
        return localSource.read()
    }

}