package coroutines

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.random.Random

/* Корутины в Kotlin построены на основе паттерна "lightweight" или "stackless" (легковесных или без стековых) корутин.
Они реализуются как сопрограммы, которые представляют собой подпрограммы, которые могут приостанавливаться и возобновляться,
не блокируя всего потока исполнения. Они позволяют писать асинхронный код в естественном, последовательном стиле,
а не вложенными обратными вызовами или синхронными блокировками.

Корутины в Kotlin неблокирующие, что означает, что когда корутина ожидает какой-либо операции
(например, сетевого запроса или задержки), она может освободить поток для выполнения других корутин или задач в это время.
Это делает корутины очень эффективными и масштабируемыми для выполнения параллельных асинхронных операций.

В отличие от традиционных потоков, которые связаны с операционной системой и управляются планировщиком ядра
(kernel-level threads), корутины являются управляемыми пользовательским уровнем (user-level threads) и не зависят от
планировщика операционной системы. Вместо этого, они используют понятие event-loop (цикл событий) для управления своим выполнением.

Когда корутина делает приостановку (например, для ожидания завершения асинхронной операции), она не блокирует поток,
а возвращает управление обратно в основной цикл событий. Основной цикл событий отвечает за планирование и выполнение
корутин, которые готовы к продолжению работы после приостановки. Это позволяет эффективно использовать один или несколько
потоков для выполнения большого числа корутин.

В Kotlin стандартная библиотека для работы с корутинами предоставляет свой собственный event-loop для управления
корутинами и их выполнением. Он умеет оптимально распределять вычисления между доступными потоками и эффективно
организовывать переключения между корутинами.

Таким образом, корутины в Kotlin позволяют писать асинхронный код с использованием привычного синхронного стиля
программирования, и при этом управление исполнением осуществляется эффективным образом через event-loop,
что делает их эффективными и легковесными для параллельного и асинхронного программирования.


Event-loop (цикл событий) - это паттерн программирования, который широко используется в асинхронных приложениях,
в том числе при работе с корутинами. Он представляет собой механизм для эффективного обработки асинхронных событий,
без блокировки и ожидания результатов операций.

Основной идеей event-loop является то, что программа ожидает наличие событий (например, завершение асинхронных операций)
и реагирует на них, когда они происходят, вместо того чтобы активно ожидать завершения каждой операции.
Это позволяет эффективно использовать ресурсы процессора и не блокирует выполнение кода, в ожидании завершения долгих операций.

Простой пример event-loop:
Программа начинает выполнение и запускает event-loop.
Event-loop начинает ожидать наличие событий, например, ожидание завершения асинхронных операций.
Когда событие происходит (например, завершение асинхронной операции), event-loop обрабатывает это событие и выполняет
соответствующий код обработчика для данного события.
После обработки события, event-loop снова начинает ожидать новых событий.
В контексте асинхронных корутин, event-loop позволяет управлять выполнением различных корутин, которые приостановлены и
возобновляются, когда события готовы к обработке. Когда корутина делает приостановку (например, для ожидания завершения
сетевого запроса или задержки), event-loop переключает управление на другую корутину, которая готова к выполнению.
Таким образом, несколько корутин могут использовать один и тот же поток выполнения, что делает их эффективными и позволяет
избежать блокировки потоков.

Event-loop обычно реализуется с использованием цикла, который бесконечно выполняется и проверяет наличие новых событий.
В языке Kotlin для работы с корутинами используется библиотека kotlinx.coroutines, которая предоставляет свой собственный
event-loop для управления корутинами и их выполнением.

Примерно вот так выглядит упрощенная структура event-loop в контексте корутин:
Запуск цикла событий (event-loop).
Проверка наличия готовых к выполнению корутин.
Выполнение следующей корутины.
Если корутины делают приостановку, возвращение управления в event-loop.
Ожидание наличия новых событий (завершение корутин и т. д.).
Обработка новых событий (выполнение соответствующего кода).
Возврат к пункту 2 и повторение цикла.

Таким образом, event-loop вместе с легковесными корутинами позволяет эффективно управлять асинхронными операциями и
параллельным выполнением кода, что делает программы более отзывчивыми и производительными.
*/

fun doTaskAsync(callback: (String) -> Unit) {
    GlobalScope.launch {
        delay(Random.nextLong(3000)) // Имитация задержки выполнения задачи
        val result = "Результат задачи: ${Random.nextInt(1, 100)}"
        callback(result)
    }
}

fun test111() {
    thread {

    }
}

// Event-loop для обработки результатов задач
fun eventLoop() {
    repeat(5) { taskNumber ->
        doTaskAsync { result ->
            println("Задача $taskNumber выполнена. $result")
        }
    }
}

fun main() {
    testEventLoop()

    // Ждем некоторое время, чтобы дать корутинам время на выполнение
    Thread.sleep(5000)
}

fun testEventLoop() = runBlocking {
    // Создание event loop
    val eventLoop = launch(Dispatchers.Default) {
        var count = 0
        while (isActive) { // isActive - это свойство, проверяющее, что корутина активна
            println("Event loop iteration $count")
            delay(500) // Имитация циклической обработки событий
            count++
        }
    }

    // Запуск асинхронной задачи
    val task = async {
        delay(1000) // Имитация длительной задачи
        "Task result"
    }

    // Ждем завершения асинхронной задачи
    val result = task.await()
    println("Received result: $result")

    // Остановка event loop
    eventLoop.cancelAndJoin()
    println("Event loop stopped")
}