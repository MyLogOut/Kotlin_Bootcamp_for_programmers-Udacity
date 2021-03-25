package dice

fun main() {
    val dice = Dice()
    dice.gamePlay(5) {
        dice.roll(it)
    }
}

