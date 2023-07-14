package codewars

fun AreaOrPerimeter() {
    areaOrPerimeter(2, 2)
}

private  fun areaOrPerimeter(l:Int, w:Int):Int {
    return if (l == w) {
        l*w
    } else {
        (l + w) * 2
    }
}