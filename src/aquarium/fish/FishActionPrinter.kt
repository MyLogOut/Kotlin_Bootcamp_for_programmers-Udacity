package aquarium.fish

class FishActionPrinter(private val food: String): FishAction {
    override fun eat() {
        println("Eating $food")
    }
}