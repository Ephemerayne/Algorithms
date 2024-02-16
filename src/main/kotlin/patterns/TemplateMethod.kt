package patterns

/*
Шаблонный метод (Template Method) — это поведенческий паттерн проектирования, который определяет основу алгоритма и
позволяет подклассам переопределять определенные шаги этого алгоритма без изменения его структуры.

Структура шаблонного метода:
Абстрактный класс (AbstractClass): Определяет шаблонный метод, который представляет собой основу алгоритма.
 В этом методе могут быть определены шаги, используемые в алгоритме, и вызовы абстрактных методов, которые подклассы
 должны реализовать.

Конкретный класс (ConcreteClass): Реализует абстрактные методы, определенные в абстрактном классе. Конкретные классы
могут переопределять некоторые шаги алгоритма, не изменяя его общей структуры.
 */

// Абстрактный класс с шаблонным методом
abstract class AbstractClass {
    fun templateMethod() {
        stepOne()
        stepTwo()
        stepThree()
    }

    abstract fun stepOne()
    abstract fun stepTwo()
    abstract fun stepThree()
}

// Конкретный класс, реализующий шаблонный метод
class ConcreteClassA : AbstractClass() {
    override fun stepOne() {
        println("ConcreteClassA: Step One")
    }

    override fun stepTwo() {
        println("ConcreteClassA: Step Two")
    }

    override fun stepThree() {
        println("ConcreteClassA: Step Three")
    }
}

// Конкретный класс, реализующий шаблонный метод
class ConcreteClassB : AbstractClass() {
    override fun stepOne() {
        println("ConcreteClassB: Step One")
    }

    override fun stepTwo() {
        println("ConcreteClassB: Step Two")
    }

    override fun stepThree() {
        println("ConcreteClassB: Step Three")
    }
}

fun main() {
    // Использование шаблонного метода с разными конкретными классами
    val concreteA = ConcreteClassA()
    val concreteB = ConcreteClassB()

    println("Using ConcreteClassA:")
    concreteA.templateMethod()

    println("\nUsing ConcreteClassB:")
    concreteB.templateMethod()
}

/*
В данном примере:

AbstractClass — абстрактный класс с шаблонным методом templateMethod, включающим три шага (stepOne, stepTwo, stepThree).
ConcreteClassA и ConcreteClassB — конкретные классы, реализующие абстрактные методы и предоставляющие свою специфичную
логику для каждого шага.
Когда вызывается templateMethod на объекте ConcreteClassA или ConcreteClassB, выполняются шаги алгоритма в порядке,
определенном абстрактным классом.
 */