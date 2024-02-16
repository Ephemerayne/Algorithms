package patterns

/*
Паттерн "Прокси" (Proxy) предоставляет заместитель или оболочку для другого объекта. Прокси контролирует доступ к этому
объекту, что позволяет осуществлять дополнительную логику до или после выполнения запросов к основному объекту.
Этот паттерн может быть полезен для ленивой загрузки данных, контроля доступа, кеширования и других сценариев.
 */

// Общий интерфейс для реального объекта и прокси
interface Image {
    fun display()
}

// Конкретный реальный объект
class RealImage(private val filename: String) : Image {
    init {
        loadFromDisk()
    }

    private fun loadFromDisk() {
        println("Loading image: $filename")
    }

    override fun display() {
        println("Displaying image: $filename")
    }
}

// Прокси
class ProxyImage(private val filename: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) {
            realImage = RealImage(filename)
        }
        realImage?.display()
    }
}

// Пример использования
fun main() {
// Создаем объект прокси
    val image: Image = ProxyImage("example.jpg")

// Клиентский код работает с объектом через прокси
    image.display()

// Вывод:
// Loading image: example.jpg
// Displaying image: example.jpg
}

/*
В данном примере:

Image - общий интерфейс для реального объекта и прокси.
RealImage - конкретная реализация реального объекта, загружающая изображение при создании и отображающая его при вызове
метода display().
ProxyImage - прокси, который создает реальный объект только при вызове метода display(). Позволяет лениво загружать
изображение.
Прокси может выполнять различные функции, такие как контроль доступа, кеширование, отложенная загрузка данных и т. д.
Он предоставляет тот же интерфейс, что и реальный объект, и клиентский код может использовать прокси вместо реального
объекта без изменения своей логики.
 */