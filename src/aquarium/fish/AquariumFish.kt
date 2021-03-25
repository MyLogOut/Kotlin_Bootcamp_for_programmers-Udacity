package aquarium.fish

import aquarium.resources.colors.Golden

open class AquariumFish constructor(
    override val name: String = "Aquarium",
    override val widthInches: Float = 0.5F,
    override val heightInches: Float = 1.0F,
    override val lengthInches: Float = 2.0F,
    override val isAggressive: Boolean = false,
    private val fishColor: FishColor = Golden
) : Fish(), FishAction, FishColor by fishColor {

    override val volume: Float
        get() = widthInches * heightInches * lengthInches
    override val requiredGallons: Float
        get() = if (isAggressive) volume*4 else volume*2

/*    override fun KClass<FishAction>.acquire(
        name: String,
        widthInches: Float,
        heightInches: Float,
        lengthInches: Float
    ): AquariumFish {
        return AquariumFish(name, heightInches, widthInches, lengthInches)
    }*/

/*    override fun <T> FishProperties.copy(
        name: String,
        widthInches: Float,
        heightInches: Float,
        lengthInches: Float,
        isAggressive: Boolean,
        color: FishColor
    ): FishProperties {
        return AquariumFish(name, widthInches, heightInches, lengthInches, isAggressive, color)
    }*/

    fun copy(
        name: String = this.name,
        widthInches: Float = this.widthInches,
        heightInches: Float = this.heightInches,
        lengthInches: Float = this.lengthInches,
        isAggressive: Boolean = this.isAggressive,
        color: FishColor = this.fishColor
    ): AquariumFish {
        return AquariumFish(name, widthInches, heightInches, lengthInches, isAggressive, color)
    }
}