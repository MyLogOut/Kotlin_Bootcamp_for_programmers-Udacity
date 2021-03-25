package aquarium.fish

interface FishProperties: FishColor {
    val name: String
    val widthInches: Float
    val heightInches: Float
    val lengthInches: Float
    val isAggressive: Boolean

    val volume: Float

    val requiredGallons: Float
}