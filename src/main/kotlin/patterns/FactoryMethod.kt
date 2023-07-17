package patterns

/**
порождающий паттерн проектирования, который определяет общий интерфейс для создания объектов в суперклассе,
позволяя подклассам изменять тип создаваемых объектов.

Применимость
Когда заранее неизвестны типы и зависимости объектов, с которыми должен работать ваш код.

Фабричный метод отделяет код производства продуктов от остального кода, который эти продукты использует.

Благодаря этому, код производства можно расширять, не трогая основной. Так, чтобы добавить поддержку нового продукта,
вам нужно создать новый подкласс и определить в нём фабричный метод, возвращая оттуда экземпляр нового продукта.

Когда вы хотите дать возможность пользователям расширять части вашего фреймворка или библиотеки.

Пользователи могут расширять классы вашего фреймворка через наследование. Но как сделать так, чтобы фреймворк создавал
объекты из этих новых классов, а не из стандартных?

Решением будет дать пользователям возможность расширять не только желаемые компоненты, но и классы, которые создают эти
компоненты. А для этого создающие классы должны иметь конкретные создающие методы, которые можно определить.

Например, вы используете готовый UI-фреймворк для своего приложения. Но вот беда — требуется иметь круглые кнопки,
вместо стандартных прямоугольных. Вы создаёте класс RoundButton. Но как сказать главному классу фреймворка UIFramework,
чтобы он теперь создавал круглые кнопки, вместо стандартных?

Для этого вы создаёте подкласс UIWithRoundButtons из базового класса фреймворка, переопределяете в нём метод создания
кнопки (а-ля createButton) и вписываете туда создание своего класса кнопок. Затем используете UIWithRoundButtons вместо
стандартного UIFramework.

Когда вы хотите экономить системные ресурсы, повторно используя уже созданные объекты, вместо порождения новых.

Такая проблема обычно возникает при работе с тяжёлыми ресурсоёмкими объектами, такими, как подключение к базе данных,
файловой системе и т. д.

Представьте, сколько действий вам нужно совершить, чтобы повторно использовать существующие объекты:

Сначала вам следует создать общее хранилище, чтобы хранить в нём все создаваемые объекты.
При запросе нового объекта нужно будет заглянуть в хранилище и проверить, есть ли там неиспользуемый объект.
А затем вернуть его клиентскому коду.
Но если свободных объектов нет — создать новый, не забыв добавить его в хранилище.
Весь этот код нужно куда-то поместить, чтобы не засорять клиентский код.

Самым удобным местом был бы конструктор объекта, ведь все эти проверки нужны только при создании объектов. Но, увы,
конструктор всегда создаёт новые объекты, он не может вернуть существующий экземпляр.

Значит, нужен другой метод, который бы отдавал как существующие, так и новые объекты. Им и станет фабричный метод.
 */

abstract class Pony4

class EarthPony4 : Pony4()
class Pegasus4 : Pony4()
class Unicorn4 : Pony4()

abstract class Place {
    private var numberOfPonies = 0

    abstract fun pony() : Pony4

    fun newPony() : Pony4 {
        numberOfPonies++
        return pony()
    }

    fun count() = numberOfPonies
}

class Cloudsdale : Place() {
    override fun pony() = Pegasus4()
}

class Canterlot : Place() {
    override fun pony() = Unicorn4()
}

class Ponyville : Place() {
    override fun pony() = EarthPony4()
}