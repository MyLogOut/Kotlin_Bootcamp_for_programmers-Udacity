package aquarium.fish


interface FishAction {
    fun poop(fish: Fish) {
        println("${fish.name} pooped")
    }
    fun jump(fish: Fish) {
        println("${fish.name} just jumped")
    }
    fun eat() {
        println("Eating Algae")
    }

/*
    fun KClass<FishAction>.acquire(
        name: String,
        widthInches: Float = 0.5F,
        heightInches: Float = 1.0F,
        lengthInches: Float = 2.0F
    ): AquariumFish
    fun <T> FishProperties.copy(name: String = this.name,
             widthInches: Float = this.widthInches,
             heightInches: Float = this.heightInches,
             lengthInches: Float = this.lengthInches,
             isAggressive: Boolean = this.isAggressive,
             color: FishColor
    ): FishProperties
*/

}