package patterns

/* Компоновщик (Composite): Позволяет клиентам обращаться к отдельным объектам и их композициям одинаковым образом.
Можно добавлять новые виды компонентов, не изменяя код клиента.
 */

// Абстрактный компонент
interface FileSystemComponent {
    fun display()
}

// Лист - конкретный компонент
class File(private val name: String) : FileSystemComponent {
    override fun display() {
        println("File: $name")
    }
}

// Контейнер - конкретный компонент
class Directory(private val name: String) : FileSystemComponent {
    private val children = mutableListOf<FileSystemComponent>()

    fun add(component: FileSystemComponent) {
        children.add(component)
    }

    fun remove(component: FileSystemComponent) {
        children.remove(component)
    }

    override fun display() {
        println("Directory: $name")
        children.forEach { it.display() }
    }
}

// Пример использования

fun main() {
// Создаем листы (файлы)
    val file1 = File("file1.txt")
    val file2 = File("file2.txt")
    val file3 = File("file3.txt")

// Создаем контейнер (директорию)
    val directory = Directory("My Documents")

// Добавляем листы в контейнер
    directory.add(file1)
    directory.add(file2)

// Создаем вложенную директорию
    val subdirectory = Directory("Subfolder")
    subdirectory.add(file3)

// Добавляем вложенную директорию в основную директорию
    directory.add(subdirectory)

// Выводим структуру
    directory.display()
}