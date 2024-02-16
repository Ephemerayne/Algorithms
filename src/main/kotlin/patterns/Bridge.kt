package patterns

/*
Паттерн "Мост" (Bridge) является структурным паттерном проектирования, который разделяет абстракцию от её реализации,
позволяя им изменяться независимо друг от друга. Это позволяет компонентам эволюционировать отдельно друг от друга.
 */

// Абстракция
interface Abstraction {
    fun operation(): String
}

// Реализация
interface Implementor {
    fun operationImpl(): String
}

// Конкретная реализация
class ConcreteImplementorA : Implementor {
    override fun operationImpl(): String {
        return "ConcreteImplementorA operation"
    }
}

class ConcreteImplementorB : Implementor {
    override fun operationImpl(): String {
        return "ConcreteImplementorB operation"
    }
}

// Расширенная абстракция
class RefinedAbstraction(private val implementor: Implementor) : Abstraction {
    override fun operation(): String {
        return "RefinedAbstraction: ${implementor.operationImpl()}"
    }
}

// Пример использования

fun main() {
// Создаем объекты реализаций
    val implementorA: Implementor = ConcreteImplementorA()
    val implementorB: Implementor = ConcreteImplementorB()

// Создаем объекты абстракций, связанных с конкретными реализациями
    val refinedAbstractionA: Abstraction = RefinedAbstraction(implementorA)
    val refinedAbstractionB: Abstraction = RefinedAbstraction(implementorB)

// Вызываем операции
    println(refinedAbstractionA.operation()) // Вывод: RefinedAbstraction: ConcreteImplementorA operation
    println(refinedAbstractionB.operation()) // Вывод: RefinedAbstraction: ConcreteImplementorB operation
}

/*
В данном примере:

Abstraction - определяет интерфейс абстракции.
Implementor - определяет интерфейс для реализации.
ConcreteImplementorA и ConcreteImplementorB - конкретные реализации.
RefinedAbstraction - расширенная абстракция, связывает абстракцию с конкретной реализацией.

Этот паттерн позволяет изменять и абстракцию, и реализацию независимо друг от друга. Вместо того чтобы иметь единый класс
с множеством подклассов, мы имеем два отдельных иерархии, которые могут развиваться по-разному. Это полезно, когда
необходимо избежать постоянного увеличения количества подклассов.
 */