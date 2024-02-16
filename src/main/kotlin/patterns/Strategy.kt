package patterns

/**
поведенческий паттерн проектирования, который определяет семейство схожих алгоритмов и помещает каждый из них
в собственный класс, после чего алгоритмы можно взаимозаменять прямо во время исполнения программы.

Применимость
Когда вам нужно использовать разные вариации какого-то алгоритма внутри одного объекта.

Стратегия позволяет варьировать поведение объекта во время выполнения программы, подставляя в него различные
объекты-поведения (например, отличающиеся балансом скорости и потребления ресурсов).

Когда у вас есть множество похожих классов, отличающихся только некоторым поведением.

Стратегия позволяет вынести отличающееся поведение в отдельную иерархию классов, а затем свести первоначальные классы
к одному, сделав поведение этого класса настраиваемым.

Когда вы не хотите обнажать детали реализации алгоритмов для других классов.

Стратегия позволяет изолировать код, данные и зависимости алгоритмов от других объектов, скрыв эти детали внутри
классов-стратегий.

Когда различные вариации алгоритмов реализованы в виде развесистого условного оператора.
Каждая ветка такого оператора представляет собой вариацию алгоритма.

Стратегия помещает каждую лапу такого оператора в отдельный класс-стратегию. Затем контекст получает определённый
объект-стратегию от клиента и делегирует ему работу. Если вдруг понадобится сменить алгоритм, в контекст можно подать
другую стратегию.
 */

interface ExchangeStrategy {
    fun into(price: Double): Double

    class Dollar : ExchangeStrategy {
        override fun into(price: Double): Double {
            return price / 70
        }
    }

    class Tenge : ExchangeStrategy {
        override fun into(price: Double): Double {
            return price * 6
        }
    }
}

class RubleExchangeRate {
    private var strategy: ExchangeStrategy = ExchangeStrategy.Dollar()

    fun changeStrategy(strategy: ExchangeStrategy) {
        this.strategy = strategy
    }

    fun exchange(priceInRuble: Double) = strategy.into(priceInRuble)
}


/* ПРИМЕР 2 */

// Интерфейс стратегии
interface PaymentStrategy {
    fun pay(amount: Int)
}

// Конкретные стратегии
class CreditCardPaymentStrategy(private val cardNumber: String) : PaymentStrategy {
    override fun pay(amount: Int) {
        println("Paid $amount using credit card $cardNumber")
    }
}

class PayPalPaymentStrategy(private val email: String) : PaymentStrategy {
    override fun pay(amount: Int) {
        println("Paid $amount using PayPal with email $email")
    }
}

// Класс, использующий стратегию
class ShoppingCart(private val paymentStrategy: PaymentStrategy) {
    fun checkout(amount: Int) {
        paymentStrategy.pay(amount)
    }
}

// Пример использования

fun main() {
// Создаем стратегии оплаты
    val creditCardStrategy = CreditCardPaymentStrategy("1234-5678-9876-5432")
    val paypalStrategy = PayPalPaymentStrategy("user@example.com")

// Создаем объекты с использованием стратегий оплаты
    val shoppingCart1 = ShoppingCart(creditCardStrategy)
    val shoppingCart2 = ShoppingCart(paypalStrategy)

// Выполняем оплату с использованием соответствующих стратегий
    shoppingCart1.checkout(100)
    shoppingCart2.checkout(50)
}