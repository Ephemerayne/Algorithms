import algo.Person
import leetcode.BinaryTreeNode
import java.util.LinkedList

/*fun main() {
    val tree1 = BinaryTreeNode(4).apply {
        left = BinaryTreeNode(2).apply {
            left = BinaryTreeNode(1)
            right = BinaryTreeNode(3)
        }
        right = BinaryTreeNode(7).apply {
            left = BinaryTreeNode(6)
            right = BinaryTreeNode(9)
        }
    }

    val tree2 = BinaryTreeNode(3).apply {
        left = BinaryTreeNode(9)
        right = BinaryTreeNode(20).apply {
            left = BinaryTreeNode(15)
            right = BinaryTreeNode(7)
        }
    }

    maxDepth(tree2)
}*/

private fun taskInvert(root: BinaryTreeNode?): BinaryTreeNode? {
    if (root == null) return null

    val queue = LinkedList<BinaryTreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val polled = queue.poll()
        val left = polled.left
        val right = polled.right

        polled.left = right
        polled.right = left

        left?.let { queue.offer(it) }
        right?.let { queue.offer(it) }
    }

    println("$root")
    return root
}

private fun maxDepth(root: BinaryTreeNode?): Int {
    if (root == null) return 0

    val levels = mutableListOf<BinaryTreeNode>()
    levels.add(root)

    var levelIndex = 0
    while (levels.isNotEmpty()) {
        levelIndex++

        val queue = LinkedList<BinaryTreeNode>()
        queue.addAll(levels)
        levels.clear()

        while (queue.isNotEmpty()) {
            val polled = queue.poll()

            polled?.left?.let { levels.add(it) }
            polled?.right?.let { levels.add(it) }
        }
    }

    return levelIndex
}


/*
val map = Map<K, out V> // Ковариантен по типу значения V, но инвариантен по типу ключа K.
val map2 = MutableMap<K, V> // Инвариантен как по типу ключа K, так и по типу значения V.*/

/*
Обобщение
Ковариантность (out в Kotlin): Позволяет использовать тип TypeB вместо его супертипа TypeA.
Пример: List<out Animal> может включать List<Cat>, если Cat : Animal.
val readList: List<Animal> = listOf<Dog>()

Инвариантность: Тип не может быть заменен ни его подтипом, ни супертипом.
Пример: MutableList<Animal> не может быть заменен на MutableList<Cat> и наоборот.
val mutableList: MutableList<Animal> = mutableListOf<Dog>() // инвариантность (интравариантность)

Контравариантность (in в Kotlin): Позволяет использовать супертип TypeA вместо его подтипа TypeB.
Пример: Consumer<in Cat> может использовать Consumer<Animal>, если Cat : Animal.

interface Consumer<in T> {
    fun consume(item: T)
}

 */


// Контравариантность
open class Animal
class Cat : Animal()

interface Consumer<in T> {
    fun consume(item: T)
}

class AnimalFeeder : Consumer<Animal> {
    override fun consume(item: Animal) {
        println("Кормим животное")
    }
}

class CatFeeder : Consumer<Cat> {
    override fun consume(item: Cat) {
        println("Кормим кошку")
    }
}

fun feedCat(catFeeder: Consumer<Cat>) {
    catFeeder.consume(Cat())
}

fun feedAnimals(feeder: Consumer<Animal>) {
    feeder.consume(Cat())
    feeder.consume(Animal())
}

fun main() {
    test()
  /*  val animalFeeder = AnimalFeeder()
    val catFeeder = CatFeeder()

    feedCat(animalFeeder) // Это работает, потому что AnimalFeeder может кормить кошек
    feedCat(catFeeder)    // Это тоже работает, потому что CatFeeder специализируется на кошках

    feedAnimals(animalFeeder) // AnimalFeeder может кормить всех, так как кошка тоже животное
    feedAnimals(catFeeder) // catFeeder не может кормить всех животных, только котов*/
}


fun interface KRunnable {
    fun invoke()
}

class Test: KRunnable {
    override fun invoke() {
        TODO("Not yet implemented")
    }

}

data class Personn(val name: String) {
    var age: Int = 0
}

fun test() {
    val person1 = Personn(name = "John")
    val person2 = Personn(name = "John")
    person1.age = 10
    person2.age = 20

    println(person1 == person2)
}


interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

/*
fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}*/
