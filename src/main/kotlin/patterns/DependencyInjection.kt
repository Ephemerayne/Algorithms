package patterns

/*
ependency Injection (DI) — это принцип объектно-ориентированного программирования, который предлагает инвертировать
управление созданием и внедрением зависимостей в компоненты приложения. Вместо того чтобы компоненты создавали свои
зависимости самостоятельно, зависимости предоставляются им извне. Этот процесс делает компоненты более гибкими,
переиспользуемыми и тестируемыми.

Существует несколько способов реализации Dependency Injection:

Конструкторная инъекция (Constructor Injection): Зависимости передаются через конструктор компонента.
 */

interface UserRepository

class UserServiceConstructor(private val userRepository: UserRepository) {
    // ...
}

/*
Метод инъекции (Method Injection): Зависимости передаются через метод компонента.
 */

class UserServiceMethod {
    fun setUserRepository(userRepository: UserRepository) {
        // ...
    }
}

/*
Инъекция через свойства (Property Injection): Зависимости устанавливаются через свойства компонента.
 */

class UserServiceProperty {
    lateinit var userRepository: UserRepository
    // ...
}

/*
Dependency Injection может быть реализован с использованием внешних фреймворков или библиотек, таких как Dagger,
Guice (для Java), Koin (для Kotlin) и других. Эти инструменты автоматизируют процесс создания и внедрения зависимостей,
что делает код более чистым и поддерживаемым.
 */

// Интерфейс для процессора платежей
interface PaymentProcessor {
    fun processPayment(amount: Double)
}

// Реализация процессора платежей
class CreditCardProcessor : PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing credit card payment of $amount")
        // Логика обработки платежа по кредитной карте
    }
}

// Класс заказа, зависящий от PaymentProcessor
class Order(private val paymentProcessor: PaymentProcessor) {
    fun checkout(amount: Double) {
        println("Checking out order...")
        paymentProcessor.processPayment(amount)
        // Логика завершения заказа
    }
}

fun main() {
    // Создаем экземпляр процессора платежей
    val creditCardProcessor = CreditCardProcessor()

    // Создаем заказ, передавая ему зависимость (процессор платежей)
    val order = Order(creditCardProcessor)

    // Проводим заказ с указанной суммой
    order.checkout(100.0)
}

/*
В этом примере:

PaymentProcessor - это интерфейс, который определяет метод processPayment.
CreditCardProcessor - это реализация PaymentProcessor для обработки платежей по кредитной карте.
Order - класс заказа, который принимает в конструкторе зависимость PaymentProcessor. Метод checkout использует этот
процессор для обработки платежа.
В функции main создается экземпляр CreditCardProcessor и передается в конструктор Order, что представляет собой пример
конструкторной инъекции зависимости. При необходимости легко можно заменить процессор платежей другой реализацией, не
меняя код в классе Order. Это демонстрирует гибкость и переиспользуемость кода, которые достигаются
благодаря Dependency Injection.
 */
