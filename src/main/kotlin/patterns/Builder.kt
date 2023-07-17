package patterns

/**
 * порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово.
 * Строитель даёт возможность использовать один и тот же код строительства для получения разных представлений объектов.

Применимость
Когда вы хотите избавиться от «телескопического конструктора».

Допустим, у вас есть один конструктор с десятью опциональными параметрами. Его неудобно вызывать, поэтому вы создали
ещё десять конструкторов с меньшим количеством параметров. Всё, что они делают — это переадресуют вызов к базовому
конструктору, подавая какие-то значения по умолчанию в параметры, которые пропущены в них самих.

Паттерн Строитель позволяет собирать объекты пошагово, вызывая только те шаги, которые вам нужны. А значит, больше не
нужно пытаться «запихнуть» в конструктор все возможные опции продукта.

Когда ваш код должен создавать разные представления какого-то объекта. Например, деревянные и железобетонные дома.

Строитель можно применить, если создание нескольких представлений объекта состоит из одинаковых этапов, которые
отличаются в деталях.

Интерфейс строителей определит все возможные этапы конструирования. Каждому представлению будет соответствовать
собственный класс-строитель. А порядок этапов строительства будет задавать класс-директор.

Когда вам нужно собирать сложные составные объекты, например, деревья Компоновщика.

Строитель конструирует объекты пошагово, а не за один проход. Более того, шаги строительства можно выполнять рекурсивно.
А без этого не построить древовидную структуру, вроде Компоновщика.

Заметьте, что Строитель не позволяет посторонним объектам иметь доступ к конструируемому объекту, пока тот не будет
полностью готов. Это предохраняет клиентский код от получения незаконченных «битых» объектов.
 */


/**
 *
 * The first variant
 *
 */

class Pony1 {
    private val name: String
    private val family: String
    private val cutieMark: String
    private val city: String

    private constructor(name: String, family: String, cutieMark: String, city: String) {
        this.name = name
        this.family = family
        this.cutieMark = cutieMark
        this.city = city
    }

    override fun toString(): String {
        return "$name, $family, $cutieMark, $city"
    }

    class Builder {
        private var name: String = ""
        private var family: String = ""
        private var cutieMark: String = ""
        private var city: String = ""

        fun changeName(name: String) = apply {
            this.name = name
        }

        fun changeFamily(family: String) = apply {
            this.family = family
        }

        fun changeCutieMark(cutieMark: String) = apply {
            this.cutieMark = cutieMark
        }

        fun changeCity(city: String) = apply {
            this.city = city
        }

        fun build() = Pony1(name, family, cutieMark, city)

    }

}

/**
 *
 * The second variant
 *
 */

class Pony2 {
    private var name: String = ""
    private var family: String = ""
    private var cutieMark: String = ""
    private var city: String = ""

    override fun toString(): String {
        return "$name, $family, $cutieMark, $city"
    }

    companion object {
        fun newBuilder() = Pony2().Builder()
    }

    inner class Builder {

        fun changeName(name: String) = apply {
            this@Pony2.name = name
        }

        fun changeFamily(family: String) = apply {
            this@Pony2.family = family
        }

        fun changeCutieMark(cutieMark: String) = apply {
            this@Pony2.cutieMark = cutieMark
        }

        fun changeCity(city: String) = apply {
            this@Pony2.city = city
        }

        fun build() = this@Pony2
    }

}

/**
 *
 * Kotlin variant with default arguments
 *
 */

class Pony3(
    private var name: String = "",
    private var family: String = "",
    private var cutieMark: String = "",
    private var city: String = ""
) {
    override fun toString(): String {
        return "$name, $family, $cutieMark, $city"
    }
}