package spices

import Grinder.Grinder
import spices.resources.colors.YellowSpiceColor

class SimpleSpice(override val name: String,
                  override val heat: Float = 0.0F,
                  override val spiceColor: SpiceColor = YellowSpiceColor): Curry(name, heat, spiceColor), Grinder {
    override var spiceLevel: Float = heat
        set (value) = when {
            value <=0 -> {
                field = 17.5F
            }
            value > 100.0F -> {
                field = 100.0F
            }
            else -> field = value
        }
    override val spiciness
        get() = spiceLevel

    override fun determineSpiciness(): String {
        return when (spiceLevel) {
            in (10.0F..25.0F) -> "Mild"
            in (25.1F..50.0F) -> "Somewhat spiced"
            in (50.1F..75.0F) -> "Spiced"
            in (75.1F..99.9F) -> "Spicy"
            100.0F -> "The real deal"
            else -> "Unseasoned"
        }
    }

    override fun grind() {
        println("Grinding ${this.name}'s spice")
    }

    override fun prepareSpice() {
        grind()
    }

}
