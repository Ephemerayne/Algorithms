package patterns

/**
поведенческий паттерн проектирования, который создаёт механизм подписки, позволяющий одним объектам следить и
реагировать на события, происходящие в других объектах.

Применимость
Когда после изменения состояния одного объекта требуется что-то сделать в других, но вы не знаете наперёд,
какие именно объекты должны отреагировать.

Описанная проблема может возникнуть при разработке библиотек пользовательского интерфейса, когда вам надо дать
возможность сторонним классам реагировать на клики по кнопкам.

Паттерн Наблюдатель позволяет любому объекту с интерфейсом подписчика зарегистрироваться на получение оповещений о
событиях, происходящих в объектах-издателях.

Когда одни объекты должны наблюдать за другими, но только в определённых случаях.

Издатели ведут динамические списки. Все наблюдатели могут подписываться или отписываться от получения оповещений
прямо во время выполнения программы.


 */

fun interface Observer {
    fun update(item: List<String>)
}

/**
 * the interface of the object whose changes we will listen to
 *
 */
interface Observable {
    fun observe(observer: Observer)
    fun cancel(observer: Observer)
    fun notifyObservers()
}

/**
 * contains some data, when it changes we will notify observers
 *
 */
class PonyList : Observable {

    private val ponies = mutableListOf<String>()

    private val observers = mutableSetOf<Observer>()

    fun add(pony: String) {
        ponies.add(pony)
        // notify our observers that the data has changed
        notifyObservers()
    }

    override fun observe(observer: Observer) {
        observers.add(observer)
    }

    override fun cancel(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { observer -> observer.update(ponies) }
    }
}

/* ПРИМЕР 2 */

// Интерфейс для наблюдателя
interface Observerr {
    fun update(message: String)
}

// Конкретный класс наблюдателя
class ConcreteObserver(private val name: String) : Observerr {
    override fun update(message: String) {
        println("$name received message: $message")
    }
}

// Интерфейс для субъекта (наблюдаемого объекта)
interface Subject {
    fun addObserver(observer: Observerr)
    fun removeObserver(observer: Observerr)
    fun notifyObservers(message: String)
}

// Конкретный класс субъекта
class ConcreteSubject : Subject {
    private val observers = mutableListOf<Observerr>()

    override fun addObserver(observer: Observerr) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observerr) {
        observers.remove(observer)
    }

    override fun notifyObservers(message: String) {
        for (observer in observers) {
            observer.update(message)
        }
    }

    fun doSomethingImportant() {
        // Выполняем какие-то действия
        println("Something important happened!")
        // Уведомляем наблюдателей
        notifyObservers("Something important happened!")
    }
}

// Пример использования

fun main() {
// Создаем наблюдателей
    val observer1 = ConcreteObserver("Observer 1")
    val observer2 = ConcreteObserver("Observer 2")

// Создаем наблюдаемый объект
    val subject = ConcreteSubject()

// Добавляем наблюдателей к наблюдаемому объекту
    subject.addObserver(observer1)
    subject.addObserver(observer2)

// Наблюдаемый объект выполняет какие-то действия
    subject.doSomethingImportant()

// Вывод:
// Observer 1 received message: Something important happened!
// Observer 2 received message: Something important happened!
}