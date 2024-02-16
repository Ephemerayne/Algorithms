package coroutines

import kotlinx.coroutines.*
import java.util.Date
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.coroutineContext

/*private suspend fun main() {
    GlobalScope.launch(Dispatchers.IO) {
        val startTime = System.currentTimeMillis()
        val resultOne = doLongRunningTaskOne()
        val resultTwo = doLongRunningTaskTwo()
        val currentTime = System.currentTimeMillis() - startTime
       println("debug: result: ${resultOne + resultTwo}") // back on UI thread
       println("debug: time: $currentTime") // back on UI thread
    }

    for (i in 0..100000000000) {
        val test = 2 * i
    }
}*/

private suspend fun main() {
    var i = 0
    repeat(2000) {
        CoroutineScope(Dispatchers.IO).launch {
            i++
            println(Thread.currentThread().name)
            delay(100)
        }
    }
    println("RESULT: $i")
   /* coroutineScope {
        println("THIS IS COROUTINESCOPE: ${Thread.currentThread().name + " priority: ${Thread.currentThread().priority}"}\"")
        launch(Dispatchers.Unconfined) {
            println("THIS IS FIRST LAUNCH: ${Thread.currentThread().name + " priority: ${Thread.currentThread().priority}"}\\\"\")")
            suspendTest()
        }
        val testAsync = async(Dispatchers.Unconfined) {
            println("THIS IS ASYNC: ${Thread.currentThread().name + " priority: ${Thread.currentThread().priority}"}\\\"\")")
        }
    }*/
}

suspend fun suspendTest() {
    withContext(coroutineContext) {
        println("THIS IS WITH CONTEXT: ${Thread.currentThread().name + " priority: ${Thread.currentThread().priority}"}\"")
    }
}

/* GlobalScope.launch(Dispatchers.IO) {
     val startTime = System.currentTimeMillis()

     val deferredOne = async {
         doLongRunningTaskOne()
     }

     val deferredTwo = async {
         doLongRunningTaskTwo()
     }

     val result = deferredOne.await() + deferredTwo.await()
     val currentTime = System.currentTimeMillis() - startTime

     println("debug: result: $result") // back on UI thread
     println("debug: time: $currentTime") // back on UI thread

 }

 for (i in 0..100000000000) {
     val test = 2 * i
 }*/

private suspend fun doLongRunningTaskOne(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}

private suspend fun doLongRunningTaskTwo(): Int {
    return withContext(Dispatchers.Default) {
        // your code for doing a long running task
        // Added delay to simulate
        delay(2000)
        return@withContext 10
    }
}

/*private suspend fun main(): Unit = coroutineScope {
    async {
        println("MESSAGE")
    }.await()

    for (i in 0 .. 10000) {
        println("i: $i")
    }
}*/


