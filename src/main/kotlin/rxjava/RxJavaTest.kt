package rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    syncCode()
}

// Пример создания и подписки на Observable:
private fun observableFunc() {
    val list = listOf(1, 2, 3, 4, 5)

    val observable = list.toObservable()

    observable.subscribeBy(
        onNext = { println("Next: $it") },
        onError = { println("Error: $it") },
        onComplete = { println("Completed") }
    )
}

// Пример применения оператора map для преобразования элементов:
private fun mapFunc() {
    val list = listOf(1, 2, 3, 4, 5)

    val observable = list.toObservable()

    observable
        .map { it * 2 }
        .subscribeBy(
            onNext = { println("Transformed: $it") },
            onError = { println("Error: $it") },
            onComplete = { println("Completed") }
        )
}

// Пример фильтрации элементов с использованием оператора filter:
private fun filterFunc() {
    val list = listOf(1, 2, 3, 4, 5)

    val observable = list.toObservable()

    observable
        .filter { it % 2 == 0 }
        .subscribeBy(
            onNext = { println("Even number: $it") },
            onError = { println("Error: $it") },
            onComplete = { println("Completed") }
        )
}

// Пример объединения двух Observable с использованием оператора zip:
private fun zipFunc() {
    val numbers = listOf(1, 2, 3, 4, 5)
    val letters = listOf("A", "B", "C", "D", "E")

    val numbersObservable = numbers.toObservable()
    val lettersObservable = letters.toObservable()

    numbersObservable
        .zipWith(lettersObservable) { number, letter -> "$number$letter" }
        .subscribeBy(
            onNext = { println("Combined: $it") },
            onError = { println("Error: $it") },
            onComplete = { println("Completed") }
        )
}

// Пример асинхронного кода
private fun asyncCode() {
    // Создаем Observable, который эмулирует асинхронное выполнение
    val observable = Observable.create<Int> { emitter ->
        // Выполняем какую-то долгую операцию асинхронно
        Thread.sleep(1000)
        emitter.onNext(1)
        Thread.sleep(1000)
        emitter.onNext(2)
        emitter.onComplete()
    }

    observable
        .subscribeOn(Schedulers.io()) // Запускаем выполнение на фоновом потоке
        .observeOn(Schedulers.newThread()) // Подписываемся на результат на новом потоке
        .subscribe(
            { value -> println("Received: $value on Thread: ${Thread.currentThread().name}") },
            { error -> println("Error: $error") },
            { println("Completed") }
        )

    // Ожидаем завершения работы асинхронной операции
    Thread.sleep(3000)

    /* В этом примере мы создали асинхронный Observable, который выполняет долгие операции на фоновом потоке, а затем
    подписываемся на его результаты на другом потоке с использованием subscribeOn и observeOn.
     */
}

// Пример синхронного кода
private fun syncCode() {
    val list = listOf(1, 2, 3, 4, 5)

    val observable = list.toObservable()

    observable
        .map { it * 2 }
        .subscribe(
            { value -> println("Transformed: $value") },
            { error -> println("Error: $error") },
            { println("Completed") }
        )

    // Завершаем программу без ожидания завершения работы Observable

    /* В этом примере мы создали синхронный Observable, который просто преобразует элементы списка и выводит результаты
    сразу же. Программа завершается после выполнения всех операций над Observable, и нет необходимости ожидать
    завершения асинхронной работы, как в предыдущем примере.
     */
}