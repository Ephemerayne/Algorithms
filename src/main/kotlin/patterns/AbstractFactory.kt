package patterns

/**
Абстрактная фабрика — это порождающий паттерн проектирования, который позволяет создавать семейства связанных объектов,
не привязываясь к конкретным классам создаваемых объектов.

Применимость
Когда бизнес-логика программы должна работать с разными видами связанных друг с другом продуктов, не завися от конкретных
классов продуктов.

Абстрактная фабрика скрывает от клиентского кода подробности того, как и какие конкретно объекты будут созданы.
Но при этом клиентский код может работать со всеми типами создаваемых продуктов, поскольку их общий интерфейс был
заранее определён.

Когда в программе уже используется Фабричный метод, но очередные изменения предполагают введение новых типов продуктов.

В хорошей программе каждый класс отвечает только за одну вещь. Если класс имеет слишком много фабричных методов, они
способны затуманить его основную функцию. Поэтому имеет смысл вынести всю логику создания продуктов в отдельную иерархию
классов, применив абстрактную фабрику.
 */

interface Button {
    fun draw() {}
}

class AndroidButton : Button
class IOSButton : Button

interface Text {
    fun draw() {}
}

class AndroidText : Text
class IOSText : Text



interface ButtonFactory {
    fun createButton() : Button
    fun createText() : Text
}

class AndroidButtonFactory : ButtonFactory {
    override fun createButton() : Button = AndroidButton()
    override fun createText(): Text = AndroidText()
}

class IOSButtonFactory : ButtonFactory {
    override fun createButton() : Button = IOSButton()
    override fun createText(): Text = IOSText()
}