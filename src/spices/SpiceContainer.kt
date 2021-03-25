package spices

data class SpiceContainer(
    var label: String = "",
    private val spice: Spice
): SpiceColor by spice.spiceColor {

    val content: String by lazy { "${spice.determineSpiciness()} ${spice.name}" }
    private var isOpen = false
    private var level: Float = 100.0F
    fun open() {
        isOpen = true
        println("${label}'s container opened")
    }
    fun close() {
        isOpen = false
        println("${label}'s container closed")
    }
    fun get(quantity: Int): Map<Int, Spice> {
        level -= quantity
        return mapOf(quantity to spice)
            .also { println("Heat: ${it.values.first().spiciness}. You got ${it.keys}mg of ${it.values.first().determineSpiciness()} ${it.values.first().name}, leaving ${level}mg remaining.") }
    }
}