package aquarium.tank

import aquarium.fish.Fish
import aquarium.fishFood
import aquarium.randomDay

open class Tank(
    val title: String,
    protected val widthInches: Float = 20.0F,
    protected var heightInches: Float = 40.0F,
    protected val lengthInches: Float = 30.0F,
    protected val hasCustomWaterGallonsPerFish: Boolean = false,
    val hasDecorations: Boolean = false
) {
    private val fishDeck: MutableList<Fish> = mutableListOf()
    private var filledCubicInches: Float = 0.0F
    private var dirtiness: Float = 0.0F
    protected open val volume: Float = widthInches*heightInches*lengthInches
    protected open val waterGallons: Float = cubicInchToGallon(if (hasDecorations) (volume) * 0.80F else (volume) * 0.90F)
    private val volumeInchesToCubicCm: Float = volume*16.387064F

    private val gallonsPerInch3PerFish: Float //Custom: Each Fish has its own required amount of waterGallons)
                                            // Standard: Each Fish is taking 2 gallons per inch:
        get() = if (hasCustomWaterGallonsPerFish)
            fishDeck.sumByDouble { it.requiredGallons.toDouble() }.toFloat()
                else filledCubicInches * 2

    private val waterGallonsAvailable: Float
        get() = waterGallons - gallonsPerInch3PerFish

    private val hasSpace: Boolean
        get() = waterGallonsAvailable > 0.0F


    fun hiddenValues() {
        println("\n\n$title\n")
        fishDeck.forEach(::println)
        println("\nVolume (inches3): $volume")
        println("Volume (cm3): $volumeInchesToCubicCm")
        println("Dirtiness: $dirtiness")
        println("filledInches = $filledCubicInches")
        println("hasSpace = $hasSpace")
        println("filledCubicInches = $filledCubicInches")
        println("requiredGallonsPerInchPerFish = $gallonsPerInch3PerFish")
        println("waterGallons = $waterGallons")
        println("waterGallonsAvailable = $waterGallonsAvailable")
    }

    fun fitMoreFish(): Boolean {
        return fitThisFish()
    }

    fun fitThisFish(fish: Fish? = null): Boolean {
        val indicator = fish == null
        var doesFit = false
        var inches = 0F

        when (indicator) {
            true -> { //If [ @indicator = true ] inflate plural fish inches
                for (each in this.fishDeck) {
                    inches += each.volume
                }
                if (hasSpace) {
                    filledCubicInches = inches
                    doesFit = hasSpace
                }
            }

            false -> { //If [ @indicator = false ] inflate singular fish inches
                inches = fish?.volume ?: return false
                doesFit = waterGallonsAvailable >= cubicInchToGallon(inches)
            }
        }

        return doesFit
    }

    fun layFish(fish: Fish): Boolean {
        return if (hasSpace && fitThisFish(fish)) {
            this.fishDeck.add(fish)
            filledCubicInches+=fish.volume
            true
        } else false
    }

    fun feedTheFish() {
        val day = randomDay()
        val food = fishFood(day)
        println("Today is $day and the fish eat $food")
        if (shouldChangeWater((day))) {
            println("Change the water today")
        }
        dirtyProcessor(::updateDirty)
    }

    fun purifyWater() {
        dirtyProcessor(::waterFilter)
        println("Water purified successfully, dirtiness remaining: $dirtiness")
    }

    private fun waterFilter(dirtiness: Float) = dirtiness / 2
    private fun updateDirty(dirtiness: Float) = dirtiness+10.0F

    private fun dirtyProcessor(process: (Float) -> Float) {
        dirtiness = process(dirtiness)
    }

    private fun shouldChangeWater(
        day: String,
        temperature: Float = 22.0F,
        dirty: Float = 20.0F
    ): Boolean {
        return when {
            isTooHot(temperature) -> true
            isDirty(dirty) -> true
            isSunday(day) -> true
            else -> false
        }
    }
    private fun isTooHot(temperature: Float) = temperature > 30.0
    private fun isDirty(dirty: Float) = dirty > 30.0
    private fun isSunday(day: String) = day == "Sunday"
    protected fun cubicInchToGallon(cubicInches: Float) =  cubicInches/231.0F

    protected fun copy(
        name: String = this.title,
        widthInches: Float = this.widthInches,
        heightInches: Float = this.heightInches,
        lengthInches: Float = this.lengthInches,
        hasDecorations: Boolean = this.hasDecorations
    ) = Tank(name, widthInches, heightInches, lengthInches, hasDecorations)
}
