package patterns

/**
 * поведенческий паттерн проектирования, который позволяет передавать запросы последовательно по цепочке обработчиков.
 * Каждый последующий обработчик решает, может ли он обработать запрос сам и стоит ли передавать запрос дальше по цепи.
 *
 * Применимость
 *  Когда программа должна обрабатывать разнообразные запросы несколькими способами, но заранее неизвестно, какие
 *  конкретно запросы будут приходить и какие обработчики для них понадобятся.
 *
 *  С помощью Цепочки обязанностей вы можете связать потенциальных обработчиков в одну цепь и при получении запроса
 *  поочерёдно спрашивать каждого из них, не хочет ли он обработать запрос.
 *
 *  Когда важно, чтобы обработчики выполнялись один за другим в строгом порядке.
 *
 *  Цепочка обязанностей позволяет запускать обработчиков последовательно один за другим в том порядке,
 *  в котором они находятся в цепочке.
 *
 *  Когда набор объектов, способных обработать запрос, должен задаваться динамически.
 *
 *  В любой момент вы можете вмешаться в существующую цепочку и переназначить связи так, чтобы убрать или
 *  добавить новое звено.
 */

enum class BlockFactor {
    ONE, TWO, THREE
}

/**
 *
 * I decided to give an analogy from the Minecraft game.
 * In this game there are blocks that can be broken with a stone pickaxe, iron and diamond.
 * For example: diamond may mine by iron and diamond pickaxes unlike cobblestone, which is mined by any
 *
 */
abstract class Block(private val factor: BlockFactor) {
    fun mayMine(factor: BlockFactor) = this.factor.ordinal <= factor.ordinal
}

/**
 *
 * blocks from the game
 *
 */
class StoneBlock: Block(BlockFactor.ONE)
class DiamondBlock: Block(BlockFactor.TWO)
class ObsidianBlock: Block(BlockFactor.THREE)

abstract class Pickaxe(private val factor: BlockFactor) {

    private var nextPickaxe: Pickaxe? = null
    fun changeNextPickaxe(pickaxe: Pickaxe) {
        this.nextPickaxe = pickaxe
    }

    /**
     *
     * we mine the block, if it doesn't work, we take another pickaxe, if there is one
     *
     * @return return true if a pickaxe can mine
     */
    fun mine(block: Block): Boolean =
        if (block.mayMine(factor)) {
            true
        } else {
            nextPickaxe?.mine(block) ?: false
        }

}

/**
 *
 * pickaxes from the game
 *
 */
class StonePickaxe: Pickaxe(BlockFactor.ONE)

class IronPickaxe: Pickaxe(BlockFactor.TWO)

class DiamondPickaxe: Pickaxe(BlockFactor.THREE)