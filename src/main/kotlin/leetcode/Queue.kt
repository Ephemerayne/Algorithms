package leetcode

class MyCircularQueue(val k: Int) {
    companion object {
        const val defaultValue = -1
    }

    private val list = MutableList(k) { defaultValue }
    private var headIndex = 0
    private var rearIndex = -1
    private var availableSpace = k

    fun enQueue(value: Int): Boolean {
        return if (!isFull()) {
            rearIndex = if (rearIndex == list.lastIndex) {
                0
            } else {
                rearIndex + 1
            }

            list[rearIndex] = value

            availableSpace--
            true
        } else {
            false
        }
    }

    fun deQueue(): Boolean {
        return if (!isEmpty()) {
            list[headIndex] = defaultValue

            headIndex = if (headIndex == list.lastIndex) {
                0
            } else {
                headIndex + 1
            }

            availableSpace++
            true
        } else {
            false
        }
    }

    fun Front(): Int {
        return if (isEmpty()) {
            defaultValue
        } else {
            list[headIndex]
        }
    }

    fun Rear(): Int {
        return if (isEmpty()) {
            defaultValue
        } else {
            list[rearIndex]
        }
    }

    fun isEmpty(): Boolean {
        return availableSpace == k
    }

    fun isFull(): Boolean {
        return availableSpace == 0
    }
}