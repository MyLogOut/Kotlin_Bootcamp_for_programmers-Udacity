package dice

class Dice(sides: Int = 6) {
    private var sidesNumber = sides
        set(value) = if (value <= 2 || value >= 24) field = 6 else  field = value

    private fun rolling(): Int {
        return (1..sidesNumber).random()
    }

    fun roll(times: Int = 1) {
        repeat(times) {
            println("Dice rolled, it seems to be: ${rolling()}!")
        }
    }

    fun gamePlay(turns: Int, action: (Int) -> Unit) {
        action(turns)
    }
}