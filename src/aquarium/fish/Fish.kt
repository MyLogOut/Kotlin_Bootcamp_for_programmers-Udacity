package aquarium.fish

abstract class Fish {
    abstract val name: String
    abstract val widthInches: Float
    abstract val heightInches: Float
    abstract val lengthInches: Float
    abstract val isAggressive: Boolean

    abstract val volume: Float

    abstract val requiredGallons: Float


    /*protected abstract fun acquire(name: String,
                    widthInches: Float = 0.5F,
                    heightInches: Float = 1.0F,
                    lengthInches: Float = 2.0F
        ): Fish*/
}
