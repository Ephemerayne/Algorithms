package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/* В языке Kotlin поддержка асинхронности и параллельных вычислений воплощена в виде корутин (coroutine).
Параллельные вычисления позволяют выполнять несколько задач одновременно,
а асинхронность позволяет не блокировать основной ход приложения во время выполнения задачи, которая занимает
продолжительное время.

По сути корутина представляет блок кода, который может выполняться параллельно с остальным кодом.

Модификатор suspend определяет функцию, которая может приостановить свое выполнение и возобновить его через
некоторый период времени.

Сама функция delay() тоже является подобной функцией, которая определена с модификатором suspend.
А любая функция с модификатором suspend может вызываться либо из другой функции, которая тоже имеет модификатор suspend,
либо из корутины.

Прежде всего для определения и выполнения корутины нам надо определить для нее контекст, так как корутина может
вызываться только в контексте корутины (coroutine scope). Для этого применяется функция coroutineScope() - создает
контекст корутины. Кроме того, эта функция ожидает выполнения всех определенных внутри нее корутин.
Стоит отметить, что coroutineScope() может применяться только в функции с модификатором suspend,
коей является функция main.

Сама корутина определяется и запускается с помощью построителя корутин - функции launch.
Прежде всего, launch(), как правило, применяется, когда нам не надо возвращать результат из корутины и когда нам ее надо
выполнять одновременно с другим кодом.

Корутины и потоки
В ряде языков программирования есть такие структуры, которые позволяют использовать потоки.
Однако между корутинами и потоками нет прямого соответствия. Корутина не привязана к конкретному потоку.
Она может быть приостановить выполнение в одном потоке, а возобновить выполнение в другом.

Когда корутина приостанавливает свое выполнение, например, как в случае выше при вызове задержки с помощью функции delay(),
эта корутина освобождает поток, в котором она выполнялась, и сохраняется в памяти. А освобожденный поток может быть
зайдествован для других задач. А когда завершается запущенная задача (например, выполнение функции delay()),
корутина возобновляет свою работу в одном из свободных потоков.

Корутина может выполняться только в определенной области корутины (coroutine scope). Область корутин представляет
пространство, в рамках которого действуют корутины, она имеет определенный жизненный цикл и сама управляет жизненным
циклом создаваемых внутри нее корутин.

И для создания области корутин в Kotlin может использоваться ряд функций, которые создают объект интерфейса CoroutineScope.
Одной из функций является coroutineScope.

Функция runBlocking блокирует вызывающий поток, пока все корутины внутри вызова runBlocking { ... } не завершат свое
выполнение. В этом собственно основное отличие runBlocking от coroutineScope: coroutineScope не блокирует вызывающий поток,
а просто приостанавливает выполнение, освобождания поток для использования другими ресурсами.
runBlocking() - функция создания области видимости корутины. Т.е., это адаптер между обычной функцией и функцией -
строителем корутины: launch(), async().

Job
Построитель корутин launch возвращает объект Job, с помощью которого можно управлять запущеной корутиной.
 Например, его метод join() позволяет ожидать, пока корутина не завершится(пример в функции
JobExample)
Корутина также запускается с помощью launch, однако благодаря методу join() полученного объекта Job функция main
остановит выполнение и будет ожидать завершения корутины и только после ее завершения продолжит работу.

Отложенное выполнение
По умолчанию построитель корутин launch создает и сразу же запускает корутину.
Однако Kotlin также позволяет применять технику отложенного запуска корутины (lazy-запуск),
при котором корутина запускается при вызове метода start() объекта Job.
Для установки отложенного запуска в функцию launch() передается значение start = CoroutineStart.LAZY (Пример fun delayedExecution)

Eсть еще один построитель корутин - функция async. Эта функция применяется, когда надо получить из корутины некоторый результат.
async запускает отдельную корутину, которая выполняется параллельно с остальными корутинами (пример fun AsyncExample)

Кроме того, async-корутина возвращает объект Deferred, который ожидает получения результата корутины.
(Интерфейс Deferred унаследован от интерфейса Job, поэтому для также доступны весь функционал, определенный для интефейса Job)
Для получения результата из объекта Deferred применяется функция await(). (DeferredExample)

Отложенный запуск
По умолчанию построитель корутин async создает и сразу же запускает корутину. Но как и при создании корутины с помощью
launch для async-корутин можно применять технику отложенного запуска. Только в данном случае корутина запускается
не только при вызове метода start объекта Deferred (который усналедован от интерфейса Job), но также и с помощью метода
await() при обращении к результу корутины. (delayedExample)

Контекст корутины включает себя такой элемент как диспетчер корутины. Диспетчер корутины определяет какой поток или
какие потоки будут использоваться для выполнения корутины.

Все построители корутины, в частности, функции launch и async в качестве необязательного параметра принимают объект
типа CoroutineContext, который может использоваться для определения диспетчера создаваемой корутины.

Когда функция launch вызывается без параметров, она перенимает контекст, в котором она создается и запускается.
Например, используем метод Thread.currentThread(), который предоставляет JDK, чтобы получить данные потока корутины (пример: ContextExample)

типы диспетчеров:

Dispatchers.Default: применяется по умолчанию, если тип диспетчера не указан явным образом.
Этот тип использует общий пул разделяемых фоновых потоков и подходит для вычислений, которые не работают с операциями
ввода-вывода (операциями с файлами, базами данных, сетью) и которые требуют интенсивного потребления ресурсов центрального процессора.

Dispatchers.IO: использует общий пул потоков, создаваемых по мере необходимости, и предназначен для выполнения операций
ввода-вывода (например, операции с файлами или сетевыми запросами).

Dispatchers.Main: применяется в графических приложениях, например, в приложениях Android или JavaFX.

Dispatchers.Unconfined: корутина не закреплена четко за определенным потоком или пулом потоков.
Она запускается в текущем потоке до первой приостановки. После возобновления работы корутина продолжает работу в одном
из потоков, который строго не фиксирован. Разработчики языка Kotlin в обычной ситуации не рекомендуют использовать данный тип.

newSingleThreadContext и newFixedThreadPoolContext: позволяют вручную задать поток/пул для выполнения корутины

И мы можем сами задать для корутины диспетчер, передав в функцию launch (а также async) соответствующее значение (DispatcherExample)

Отмена выполнения корутин
При работе приложения может сложиться необходимость отменить выполнение корутины. Например, в мобильном приложении
запущена корутина для загрузки данных с некоторого интернет-ресуса, но пользователь решил перейти к другой странице
приложения, и ему больше не нужны эти данные. В этом случае чтобы зря не тратить ресурсу системы, мы можем предусмотреть
отмену выполнения корутины.
Для отмены выполнения корутины у объекта Job может применяться метод cancel(). (CancelCoroutineExample)

Каналы
Каналы позволяют передавать потоки данных. В Kotlin каналы представлены интерфейсом Channel, у которого следует выделить два основных метода:
abstract suspend fun send(element: E): Unit
Отправляет объект element в канал

abstract suspend fun receive(): E
Получает данные из канала

Пример ChannelExample
 */

@OptIn(DelicateCoroutinesApi::class)
private suspend fun main() {
    cancelAsyncExceptionExample()
  /* GlobalScope.launch(Dispatchers.IO) {
       println("Корутина продолжает выполнение на потоке: ${Thread.currentThread().name}")
       delay(1000)
       println("Корутина продолжает выполнение на потоке: ${Thread.currentThread().name}")
       delay(2000)
       println("Корутина продолжает выполнение на потоке: ${Thread.currentThread().name}")


       withContext(this.coroutineContext) {
           println("Корутина 2 продолжает выполнение на потоке: ${Thread.currentThread().name}")
           delay(1000)
           println("Корутина 2 продолжает выполнение на потоке: ${Thread.currentThread().name}")
       }
    *//*   val task1 = async { testMainDispatcher() }
       val task2 =  async { testMainDispatcher2() }

        task1.await()
        task2.await()*//*
    }.join()*/
}

private suspend fun testMainDispatcher() {
    coroutineScope {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        delay(100)
        println("FIRST COROUTINE")
    }
}

private suspend fun testMainDispatcher2() {
    coroutineScope {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
        delay(100)
        println("SECOND COROUTINE")
    }
}

private suspend fun launchCoroutine() = coroutineScope {
    launch {
        for (i in 0..5) {
            delay(400L)
            println(i)
        }
    }

    println("Hello Coroutines") // -> выполнится сразу, а затем блок кода в launch
}

private suspend fun waitCoroutine() = runBlocking {
    for (i in 0..5) {
        delay(400L)
        println(i)
    }
    println("Hello Coroutines") // -> выполнится после блока кода runBlocking (ожидается его выполнение)
}

private suspend fun backValue() = coroutineScope<Int> {
    val num1 = 10
    val num2 = 20
    return@coroutineScope num1 + num2 // или просто строка num1 + num2, так как возращается последняя строка в блоке корутин
}

private suspend fun parallelRun() =
    coroutineScope {
        launch {
            for (i in 0..5) {
                delay(400L)
                println("first cycle: $i")
            }
        }
        launch {
            for (i in 6..10) {
                delay(400L)
                println("second cycle: $i")
            }
        }
    }

// подобным образом внешние корутины определяют область для вложенных корутин и управляют их жизненным циклом.
private suspend fun innerCoroutines() =
    coroutineScope {

        launch {
            delay(1000)
            println("Outer coroutine")
            launch {// выполяется после родительского launch
                delay(2000)
                println("Inner coroutine")
            }
        }

        println("End of Main") // выполняется до корутин
    }

private suspend fun jobExample() = coroutineScope {

    val job = launch {
        for (i in 1..5) {
            println(i)
            delay(400L)
        }
    }

    println("Start") // выполняется первым
    job.join() // ожидаем завершения корутины в блоке launch (выводим ее результат)
    println("End") // выполняется последним
}

private suspend fun twoDelayInFun() = coroutineScope {
    launch() {
        delay(200L)
        println("Coroutine has started") // выполнится первым
    }

    delay(1000L)
    println("Other actions in main method") // несмотря на launch выполнится медленнее, так как задержка больше
}

// обратная ситуация по сравнению с функцией TwoDelayInFun()
// Теперь корутина только создается с помощью функции launch, но непосредственно она запускается только при вызове метода job.start()
private suspend fun delayedExecution() = coroutineScope {

    val job = launch(start = CoroutineStart.LAZY) {
        delay(200L)
        println("Coroutine has started")
    }

    delay(1000) // задержка перед выполнением всего кода (и принта и корутин)
    job.start() // запускаем корутину

    println("Other actions in main method") // выполнится первым
}

private suspend fun asyncExample() = coroutineScope {
    async { printHello() }
    println("Program has finished") // выполнится первым
}

private suspend fun printHello() {
    delay(500L)  // имитация продолжительной работы (в данном случае, без задежки выполнятся обе одновременно)
    println("Hello work!")
}

private suspend fun deferredExample() = coroutineScope {
    val sum: Deferred<Int> = async { getSum() }

    println("message: ${sum.await()}") // получение результата через await(), ожидается получение результата и не пойдет дальше, пока не получит. выполнится первым
    val result = sum.await() + 100
    println("Program has finished with sum: $result") // вторым
}

private suspend fun getSum(): Int {
    delay(500L)  // имитация продолжительной работы
    return 100 + 40
}

fun deferredExample2() = runBlocking {
    val deferred: Deferred<String> = async {
        // Имитация асинхронной задачи
        delay(1000)
        "Результат"
    }

    println("Ожидание результата...")
    val result = deferred.await() // Корутина приостанавливается здесь, но поток не блокируется
    println("Полученный результат: $result")
}

/* Здесь запускается три корутины, каждая из которых выполняет функцию sum(). Эта функция складывает два числа и
возвращает их сумму в виде объекта Int. Поэтому корутины возвращают объект Deferred<Int>. Соответственно вызов метода
await() у этого объекта возвратит объект Int, то есть сумму двух чисел. При этом все три корутины будет запущены
одновременно. Например, ниже на скриншоте отладчика корутин видно, что две корутины уже работают (или находятся в состоянии Running),
и еще одна корутина только создана и ожидает запуска (состояние Created)
 */

private suspend fun multipleAsyncCoroutine() = coroutineScope {

    val numDeferred1 = async { sum(1, 2) }
    val numDeferred2 = async { sum(3, 4) }
    val numDeferred3 = async { sum(5, 6) } // можно подставить любое число из верхних deferred

    // корутины запущены одновременно, но ожидание результата последовательное
    val num1 = numDeferred1.await()
    val num2 = numDeferred2.await()
    val num3 = numDeferred3.await()

    println("number 1: $num1")
    println("number 2: $num2")
    println("number 3: $num3")
}

private suspend fun sum(a: Int, b: Int): Int {
    delay(500L) // имитация продолжительной работы
    println("Coroutine has started")
    return a + b
}

private suspend fun delayedExample() = coroutineScope {

    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY) { sum(1, 2) }

    delay(1000L)
    println("Actions after the coroutine creation") // выполнится первым
    println("sum: ${sum.await()}")   // запуск и выполнение корутины
}

/* Если необходимо, чтобы корутина еще до метода await() начала выполняться, то можно вызвать метод start() */
private suspend fun delayedExample2() = coroutineScope {

    // корутина создана, но не запущена
    val sum = async(start = CoroutineStart.LAZY) { sum(1, 2) }

    delay(1000L)
    println("Actions after the coroutine creation") // выполнится первым
    sum.start()
    println("sum: ${sum.await()}")   // получаем результат
}

private suspend fun contextExample() = coroutineScope {
    launch {
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
}

private suspend fun dispatcherExample() = coroutineScope {
    launch(Dispatchers.Default) {   // явным образом определяем диспетчер Dispatcher.Default
        println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Функция main выполняется на потоке: ${Thread.currentThread().name}")
}

/* Dispatchers.Unconfined
Тип Dispatchers.Unconfined запускает корутину в текущем вызывающем потоке до первой приостановки.
После возобновления корутина продолжает работу в одном из потоков, который строго не фиксирован.
Подобный тип подходит для корутин, которым не требуется интенсивно потреблять время CPU или работать с общими данными,
наподобие объектов пользовательского интерфейса. Применим данный тип:
 */

private suspend fun dispatcherExample2() = coroutineScope {

    launch(Dispatchers.Unconfined) {
        println("Поток корутины (до остановки): ${Thread.currentThread().name}") //1
        delay(500L)
        println("Поток корутины (после остановки): ${Thread.currentThread().name}") // другой поток
    }

    println("Поток функции main: ${Thread.currentThread().name}") //2 тот же поток что и 1
}

/* newSingleThreadContext
newSingleThreadContext вручную запускает поток с указанным именем

В то же время выделенный поток является довольно затратным ресурсом. И в реальном приложении подобный поток следует
либо освобождать с помощью функции close(), если он больше не нужен, либо хранить в глобальной переменной и
использовать его повторно для подобных задач на протяжении работы приложения.
 */

private suspend fun dispatcherExample3() = coroutineScope {

    launch(newSingleThreadContext("Custom Thread")) {
        println("Поток корутины: ${Thread.currentThread().name}")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}

/*
В Kotlin корутинах, newSingleThreadContext создает контекст выполнения на однопоточном исполнителе. Это полезно, когда
вам нужно гарантировать, что все корутины, выполняемые в этом контексте, будут исполняться на одном и том же потоке.
Однако, такой подход создает новый поток, который является ресурсоемкой операцией, и этот поток следует закрывать, когда
он больше не нужен, чтобы освободить ресурсы.
 */

private suspend fun testCreateJob() = coroutineScope {
    val contx = Job() + newSingleThreadContext("CUSTOM")

    launch(contx) {
        println("Поток корутины: ${Thread.currentThread().name}")
    }

    println("Поток функции main: ${Thread.currentThread().name}")
}

fun closeSingleThread() = runBlocking {
    // Создание однопоточного контекста
    val singleThreadContext = newSingleThreadContext("MySingleThread")

    // Запуск корутины в этом контексте
    val job = launch(singleThreadContext) {
        println("Running in ${Thread.currentThread().name}")
    }
    job.join()

    // После использования контекста его следует закрыть
    singleThreadContext.close()
}

private suspend fun cancelCoroutineExample() = coroutineScope {

    val downloader: Job = launch {
        println("Начинаем загрузку файлов")
        for (i in 1..5) {
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)     // установим задержку, чтобы несколько файлов загрузились
    println("Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
    downloader.cancel()    // отменяем корутину
    downloader.join()      // ожидаем завершения корутины

    // Также вместо двух методов cancel() и join() можно использовать один сборный метод cancelAndJoin():
    // downloader.cancelAndJoin()

    println("Работа программы завершена")
}

// Обработка исключения CancellationException
/* Все suspend-функции в пакете kotlinx.coroutines являются прерываемыми (cancellable). Это значит, что они проверяют,
прервана ли корутина. И если ее выполнение прервано, они генерируют исключение типа CancellationException. И в
самой корутине мы можем перехватить это исключение, чтобы обработать отмену корутины. Например:
 */

private suspend fun cancelCoroutineExceptionExample() = coroutineScope {

    val downloader: Job = launch {
        try {
            println("Начинаем загрузку файлов")
            for (i in 1..5) {
                println("Загружен файл $i")
                delay(500L)
            }
        } catch (e: CancellationException) {
            println("Загрузка файлов прервана")
        } finally {
            println("Загрузка завершена")
        }
    }
    delay(800L)
    println("Надоело ждать. Прерву-ка я загрузку...")
    downloader.cancelAndJoin()    // отменяем корутину и ожидаем ее завершения
    println("Работа программы завершена")
}

/* Отмена выполнения async-корутины
Подобным образом можно отменять выполнение и корутин, создаваемых с помощью функции async(). В этом случае обычно вызов метода await() помещается в блок try:
 */

private suspend fun cancelAsyncExceptionExample() = coroutineScope {

    // создаем и запускаем корутину
    val message = async {
        getMessage() // не выведет лог, так как отменится
    }
    // отмена корутины
    message.cancelAndJoin()

    try {
        // ожидаем получение результата
        println("message: ${message.await()}")
    } catch (e: CancellationException) {
        println("Coroutine has been canceled")
    }
    println("Program has finished")
}

private suspend fun getMessage(): String {
    delay(500L)
    return "Hello"
}

// CHANNELS

private suspend fun channelExample() = coroutineScope {

    val channel = Channel<Int>()
    launch {
        for (n in 1..5) {
            // отправляем данные через канал
            channel.send(n)
        }
    }

    // получаем данные из канала
    repeat(5) {
        val number = channel.receive() // получаем значения из канала
        println(number)
    }
    println("End")
}

// Другой пример - отправка через канал строк:
suspend fun channelExample2() = coroutineScope {

    val channel = Channel<String>()
    launch {
        val users = listOf("Tom", "Bob", "Sam")
        for (user in users) {
            println("Sending $user")
            channel.send(user)
        }
    }

    repeat(3) {
        val user = channel.receive()
        println("Received: $user")
    }
    println("End")
}

/* Закрытие канала
Чтобы указать, что в канале больше нет данных, его можно закрыть с помощью метода close().
Если для получения данных из канала применяется цикл for, то, получив сигнал о закрытии канала, данный цикл получит все
ранее посланные объекты до закрытия и завершит выполнение:
 */

private suspend fun closeChannelExample() = coroutineScope {

    val channel = Channel<String>()
    launch {
        val users = listOf("Tom", "Bob", "Sam")
        for (user in users) {
            channel.send(user)  // Отправляем данные в канал
        }
        channel.close()  // Закрытие канала
    }

    for (user in channel) {  // Получаем данные из канала
        println(user)
    }
    println("End")
}

/* Паттерн producer-consumer
Рассмотренный выше пример по сути является распростаненным способом передачи данных от одной корутины к другой.
И чтобы упростить написание подобного кода, Kotlin предоставляет ряд дополнительных функций. Так, функция produce()
представляет построитель корутины, который создает корутину, в которой передаются данные в канал. Например, с помощью
функции produce() мы можем определить новую функцию-корутину, которая будет отправлять определенные данные:
 */

private fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce {
    val users = listOf("Tom", "Bob", "Sam")
    for (user in users) {
        send(user)
    }
}

/* Здесь определяется функция getUsers(). Причем она определяется как функция интерфейса CoroutineScope.
Функция должна возвращать объект ReceiveChannel, типизированный типом передаваемых данных (в данном случае передаем значения типа String).


Функция getUsers() представляет корутину, создаваемую построителем корутин produce.
В корутине опять же проходим по списку строк и с помощью функции send передаем в канал данные.

Для потребления данных из канала может применяться метод consumeEach() объекта ReceiveChannel, который по сути заменяет
цикл for. Он принимает функцию, в которую в качестве параметра передается получаемый из канала объект:
*/

suspend fun channels() = coroutineScope {
    val users = getUsers()
    users.consumeEach { user -> println(user) }
    println("End")
}

/*
Задача: Счетчик событий

Допустим, вы хотите создать простой счетчик событий, который будет увеличивать значение каждый раз, когда происходит
какое-либо событие. События будут генерироваться асинхронно, и вы хотите обновлять счетчик событий в реальном времени.
 */

fun test() = runBlocking {
    val eventChannel = Channel<Unit>()

    // Корутина для увеличения счетчика каждый раз при получении события
    val eventCounterJob = launch {
        var eventCount = 0
        for (event in eventChannel) {
            eventCount++
            println("Event count: $eventCount")
        }
    }

    // Симуляция генерации событий
    launch {
        repeat(5) {
            delay(1000)
            eventChannel.send(Unit) // Отправляем пустой элемент в канал
        }
        eventChannel.close() // Закрываем канал после завершения генерации событий
    }

    eventCounterJob.join() // Ожидаем завершения корутины подсчета событий
}

/*
В этом примере мы создаем Channel, который будет использоваться для передачи событий. Затем мы запускаем две корутины:

eventCounterJob: Корутина, которая увеличивает счетчик событий каждый раз, когда происходит событие в канале. Она ожидает
элементы из канала с помощью for (event in eventChannel).
Корутина для генерации событий: Она отправляет пустой элемент в канал через определенные интервалы времени.
После отправки 5 событий, канал закрывается.
После выполнения всех корутин мы убеждаемся, что счетчик событий был правильно обновлен.
 */


fun testUnconfined() = runBlocking<Unit> {
    launch {
        println("Starting in ${Thread.currentThread().name}")

        // Приостановка ожидая сетевой запрос (пример)
        val result = withContext(Dispatchers.IO) {
            // Представляем, что здесь выполняется сетевой запрос
            println("Working in ${Thread.currentThread().name}")
            delay(1000) // Имитация задержки
            "Result of Network Request"
        }

        // Возобновление после получения результата
        println("Continuing in ${Thread.currentThread().name} with result: $result")
    }
}


/*
Вызов метода cancel() инициирует процесс отмены корутины, но для её остановки корутина должна
кооперативно реагировать на отмену. Это значит, что в блоке кода корутины нужно регулярно проверять,
не была ли она отменена, и соответствующим образом реагировать на эти проверки.

Корутина не остановится мгновенно после вызова cancel(), если в ней нет точек проверки отмены. Такие точки включают в себя:

Явную проверку свойства isActive.
Вызовы функций, которые автоматически проверяют отмену, такие как yield(), delay() и другие функции приостановки в
стандартной библиотеке корутин.
Обработку исключения CancellationException, которое может быть выброшено при использовании функций приостановки.

Если корутина выполняет блокирующие операции или не содержит точек приостановки, которые могут выбросить CancellationException,
то вызов cancel() не приведёт к немедленной остановке корутины. В таких случаях необходимо самостоятельно проверять
isActive или использовать другие механизмы для реагирования на отмену.
 */

val job = GlobalScope.launch {
    try {
        while (isActive) { // регулярная проверка отмены
            // выполняем действия
        }
    } finally {
        // здесь можно освободить ресурсы, если корутина была отменена
    }
}

/*
Функция yield() используется в корутинах Kotlin для кооперативной отмены. Когда корутина вызывает yield(),
она проверяет, была ли корутина отменена. Если это так, yield() выбросит CancellationException, что приведет к прерыванию выполнения корутины.

Вот простой пример использования yield() в корутине:
 */

fun yieldFun() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            // делаем некоторую работу
            println("job: I'm sleeping $i ...")
            yield() // проверяем, не была ли корутина отменена
            // если корутина была отменена, дальнейший код не выполнится
            Thread.sleep(500L)
        }
    }
    delay(1300L) // даем корутине время на выполнение
    println("main: I'm tired of waiting!")
    job.cancel() // отменяем корутину
    job.join() // ждем, пока корутина не завершится
    println("main: Now I can quit.")
}


/*
В этом примере корутина запускается и делает что-то в цикле. Метод yield() вызывается в каждой итерации, чтобы проверить,
не была ли корутина отменена. Если метод cancel() был вызван из вне, yield() выбросит CancellationException, и выполнение
корутины прекратится. Мы используем join(), чтобы дождаться, пока корутина полностью завершится после отмены.
 */

/*
Отмена корутины является кооперативной. Это означает, что корутина должна сама проверять, не была ли она отменена, и
соответственно реагировать на отмену. Для этого в корутине используются проверки isActive или обрабатываются исключения
CancellationException. Это позволяет корутине корректно освободить ресурсы и завершить работу.
 */

val job2 = GlobalScope.launch {
    try {
        // некоторый потенциально долгий код
    } catch (e: CancellationException) {
        // обработка отмены корутины
    }
}
