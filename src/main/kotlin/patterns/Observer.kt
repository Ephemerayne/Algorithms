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