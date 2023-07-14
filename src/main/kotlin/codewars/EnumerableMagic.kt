package codewars

fun EnumerableMagic() {
    include(intArrayOf(1, 2, 3, 4), 2)
    include(intArrayOf(1, 2, 4, 5), 3)
}

fun include(arr: IntArray, item: Int) = arr.contains(item)