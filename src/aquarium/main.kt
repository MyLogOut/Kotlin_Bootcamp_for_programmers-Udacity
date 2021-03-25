package aquarium

import aquarium.fish.AquariumFish
import aquarium.fish.Plecostomus
import aquarium.resources.colors.Golden
import aquarium.tank.Tank
import getBirthDay
import getFortuneCookie

fun main() {
    println(getFortuneCookie(getBirthDay()))
    fish()
    delegate()

}

fun delegate() {
    val polecat = Plecostomus("Polecat", Golden, "Algae")
    println("Fish has color ${polecat.color}")
    polecat.eat()

}

fun fish() {
    val fish = AquariumFish("Rob32", 2.0F, 2.5F, 5.0F)
    println("${fish}\nVolume: ${fish.volume}")
    val newTank = Tank("Aquarium_1", hasDecorations =  true)
    println("Is there available space in ${newTank.title}? ${newTank.fitMoreFish()}")
    newTank.hiddenValues()
    println("Does \n ${fish.name} (${fish.volume} inches^3) fits in \n ${newTank.title} <? ${newTank.fitThisFish(fish)}")
    println("Fish added successfully? ${newTank.layFish(fish)}")
    newTank.hiddenValues()
    newTank.feedTheFish()
    newTank.hiddenValues()
    newTank.purifyWater()
    val nemo = fish.copy(name = "Nemo", widthInches = 1.0F, heightInches = 2.0F, lengthInches = 3.0F)
    println("Does \n ${nemo.name} (${nemo.volume} inches^3) fits in \n ${newTank.title} <? ${newTank.fitThisFish(nemo)}")
    println("Fish added successfully? ${newTank.layFish(nemo)}")
    newTank.hiddenValues()

    println("Is there available space in ${newTank.title}? ${newTank.fitMoreFish()}")
}