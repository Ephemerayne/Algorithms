package codewars

fun IsHeGonnaSurvive() {
    hero(10, 5)
    hero(7, 4)
    hero(4, 5)
    hero(100, 40)
    hero(1500, 751)
    hero(0, 1)
}

fun hero(bullets: Int, dragons: Int) = bullets >= dragons * 2