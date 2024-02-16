package coroutines

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.PriorityQueue
import java.util.concurrent.locks.ReentrantLock

/*
Многопоточность в программировании может привести к ряду проблем, связанных с совместным доступом к общим ресурсам,
синхронизацией, гонками данных и другими. Вот некоторые из этих проблем, а также примеры кода, демонстрирующие ситуации,
в которых они могут возникнуть.

Гонка данных (Race Condition) или Data race:
Проблема возникает, когда два или более потока пытаются изменить общие данные одновременно, что может привести к
непредсказуемым результатам или ошибкам.

Race condition — ошибка проектирования многопоточной системы или приложения, при которой работа системы или
приложения зависит от того, в каком порядке выполняются части кода.

Race condition — это нежелательная ситуация, которая возникает, когда устройство или система пытается выполнить две или
более операций одновременно, но из-за природы устройства или системы, операции должны выполняться в правильной
последовательности, чтобы быть выполненными правильно.

Race condition — это недостаток, связанный с синхронизацией или упорядочением событий, что приводит к ошибочному
поведению программы.

Но мне нравиться наиболее короткое и простое:

Race condition — это недостаток, возникающий, когда время или порядок событий влияют на правильность программы.

Важно, что Race condition — это семантическая ошибка.

Data race возникает в ситуации, когда:

По крайней мере, два потока обращаются к общей переменной или ресурсу.
Хотя бы один из потоков выполняет операцию записи (например, инкремент или присваивание значения).
Отсутствует явная синхронизация между потоками, чтобы обеспечить правильную последовательность выполнения операций.

чтобы избежать data race, необходимо правильно синхронизировать доступ к общим ресурсам, например, с
использованием мьютексов (locks), атомарных операций или других механизмов синхронизации,
предоставляемых языками программирования.

Мьютекс (mutex) - это механизм синхронизации, который используется для обеспечения взаимного исключения при доступе к
общему ресурсу из нескольких потоков. Он предназначен для предотвращения гонок данных (data races) и других проблем,
связанных с параллельным выполнением кода.

Мьютекс позволяет только одному потоку за раз получить "ключ" к общему ресурсу. Когда поток захватывает мьютекс
(или блокирует его), ни один другой поток не может получить доступ к этому ресурсу до тех пор, пока первый поток
не освободит мьютекс (разблокирует его). Таким образом, мьютексы обеспечивают взаимное исключение и предотвращают
одновременный доступ нескольких потоков к общему ресурсу. (MutexExample)

Инверсия приоритетов (Priority Inversion) - это проблема, связанная с многопоточностью, которая возникает,
когда выполнение низкоприоритетной задачи блокируется высокоприоритетной задачей из-за несогласованности в управлении
приоритетами. Это может привести к снижению производительности и даже к нарушению временных ограничений в системах реального времени.
(priorityInversionExample)

Сценарий инверсии приоритетов обычно выглядит следующим образом:

Высокоприоритетная задача (Task A) начинает выполнение.
Низкоприоритетная задача (Task C) начинает выполнение.
Происходит блокировка низкоприоритетной задачи, так как она ожидает какого-то ресурса
(например, блокировки или мьютекса), который занят высокоприоритетной задачей.
Следующая задача, имеющая приоритет между высоким и низким (Task B), начинает выполнение.
Так как Task B имеет приоритет выше, чем у Task C, высокоприоритетная задача продолжает ждать выполнения Task B.
Task B может затянуть свое выполнение, так как приоритет низкоприоритетной задачи Task C ниже, и это приводит к
блокировке высокоприоритетной задачи Task A.
В результате высокоприоритетная задача (Task A) может быть заблокирована низкоприоритетной задачей (Task C),
что приводит к обратной ситуации, когда задача с более низким приоритетом влияет на выполнение задачи с более высоким приоритетом.

Инверсия приоритетов может быть особенно опасной в системах реального времени, где точное и своевременное выполнение
задач имеет первостепенное значение. Для решения этой проблемы используются различные методы, такие как использование
приоритетных инверсий, механизмы приоритетного наследования или приоритетного обмена, чтобы обеспечить согласованные
приоритеты выполнения задач.

Эта проблема актуальна как для многопоточных систем, так и для систем с параллельными задачами, и ее решение требует
внимательного проектирования и анализа системы.
 */

fun main() {
    priorityInversionExample()
}

private fun raceCondition() {
    var counter = 0

    val thread1 = Thread {
        for (i in 1..1000) {
            counter++
        }
    }

    val thread2 = Thread {
        for (i in 1..1000) {
            counter++
        }
    }

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println("Counter value: $counter") // Может быть непредсказуемым

}

private fun mutexExample() {
    val mutex = ReentrantLock()
    var sharedCounter = 0

    val thread1 = Thread {
        for (i in 1..10) {
            println("thread1 mutex lock")
            mutex.lock()
            sharedCounter++
            mutex.unlock()
            println("thread1 mutex unlock")
        }
    }

    val thread2 = Thread {
        for (i in 1..10) {
            println("thread2 mutex lock")
            mutex.lock()
            sharedCounter++
            mutex.unlock()
            println("thread1 mutex unlock")
        }
    }

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println("Final counter value: $sharedCounter")
}

/* Этот код гарантирует, что операции инкремента и декремента над counter будут выполняться атомарно(неделимое),
то есть только один поток сможет изменять значение counter в конкретный момент времени.
Таким образом, мы избегаем гонок данных и обеспечиваем корректное поведение программы при параллельном выполнении кода.
 */

private fun mutexExample2() {
    val mutex = ReentrantLock()
    var counter = 0

    val thread1 = Thread {
        mutex.lock()
        try {
            counter++
        } finally {
            mutex.unlock()
        }
    }

    val thread2 = Thread {
        mutex.lock()
        try {
            counter--
        } finally {
            mutex.unlock()
        }
    }

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println("Counter value: $counter") // Будет равен 0
}

// KOTLIN MUTEX COROUTINES
private fun kotlinMutex() {
    val mutex = Mutex()
    var counter = 0

    runBlocking { // главная корутина
        val job1 = launch { // дочерняя корутина
            repeat(10) {
                mutex.withLock { // автоматически захватывает мьютекс перед выполнением блока кода и освобождает его после завершения
                    counter++
                }
            }
        }

        val job2 = launch { // дочерняя корутина
            repeat(10) {
                mutex.withLock {
                    counter--
                }
            }
        }

        joinAll(job1, job2) // ожидание завершения всех корутин перед выводом результата.

        println("Counter value: $counter") // Будет равен 0
    }
}

/* Взаимная блокировка (Deadlock):
Проблема возникает, когда два или более потока блокируют друг друга, ожидая освобождения ресурсов, которые заблокированы
другими потоками.
 */

private fun deadlock() {
    val resource1 = Object()
    val resource2 = Object()

    val thread1 = Thread {
        synchronized(resource1) {
            println("Thread 1: Holding resource 1...")
            Thread.sleep(100)
            println("Thread 1: Waiting for resource 2...")
            synchronized(resource2) {
                println("Thread 1: Holding resource 1 and resource 2...")
            }
        }
    }

    val thread2 = Thread {
        synchronized(resource2) {
            println("Thread 2: Holding resource 2...")
            Thread.sleep(100)
            println("Thread 2: Waiting for resource 1...")
            synchronized(resource1) {
                println("Thread 2: Holding resource 2 and resource 1...")
            }
        }
    }

    thread1.start()
    thread2.start()
}

/* Неправильная синхронизация (Improper Synchronization):
Проблема возникает, когда разные потоки работают с общими данными без правильной синхронизации,
что может привести к некорректным результатам.
 */

private fun synchro() {
    var counter = 0

    val thread1 = Thread {
        for (i in 1..1000) {
            counter++
        }
    }

    val thread2 = Thread {
        for (i in 1..1000) {
            counter--
        }
    }

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()

    println("Counter value: $counter") // Может быть некорректным
}

/*
Для предотвращения этих проблем необходимо использовать правильную синхронизацию, механизмы блокировки, атомарные
операции и другие инструменты, предоставляемые языками программирования, для обеспечения безопасного параллельного
выполнения кода.
 */

private fun priorityInversionExample() {
    val mutex = ReentrantLock()

    val taskA = Thread {
        println("Task A (High Priority): Started")
        Thread.sleep(1000)
        println("Task A (High Priority): Finished")
    }

    val taskB = Thread {
        println("Task B (Medium Priority): Started")
        Thread.sleep(500)
        println("Task B (Medium Priority): Finished")
    }

    val taskC = Thread {
        println("Task C (Low Priority): Started")
        mutex.lock()
        println("Task C (Low Priority): Obtained Mutex")
        Thread.sleep(200)
        println("Task C (Low Priority): Releasing Mutex")
        mutex.unlock()
        println("Task C (Low Priority): Finished")
    }

    taskA.priority = Thread.MAX_PRIORITY
    taskB.priority = Thread.NORM_PRIORITY
    taskC.priority = Thread.MIN_PRIORITY

    taskA.start()
    taskB.start()
    taskC.start()

    taskA.join()
    taskB.join()
    taskC.join()

    println("All tasks finished")
}

/* В этом примере есть три задачи с разными приоритетами: Task A (высокий приоритет), Task B (средний приоритет) и
Task C (низкий приоритет). Task C блокирует мьютекс, что приводит к возможной инверсии приоритетов.
Task A и Task B будут ожидать, пока Task C не освободит мьютекс.
 */