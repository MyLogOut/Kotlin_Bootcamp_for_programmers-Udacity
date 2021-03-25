package aquarium.fish

import aquarium.resources.colors.Golden
import java.awt.Color

class Guppy(private val fishColor: FishColor = Golden,
            override val name: String = "",
            override val widthInches: Float = 0F,
            override val heightInches: Float = 0F,
            override val lengthInches: Float = 0F,
            override val isAggressive: Boolean = true
): FishProperties, FishAction, FishColor by fishColor {


    override fun poop(fish: Fish) {
        println("$name pooped")
    }

    override fun jump(fish: Fish) {
        println("$name just jumped")
    }

    override fun eat() {
        println("$name is eating")
    }


    override val volume: Float
        get() = widthInches * heightInches * lengthInches
    override val requiredGallons: Float
        get() =  if (isAggressive) volume*4 else volume*2

}