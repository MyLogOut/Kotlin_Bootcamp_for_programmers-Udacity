package aquarium.fish

class Plecostomus(name: String, private val fishColor: FishColor, food: String) : FishAction by FishActionPrinter(food), FishColor by fishColor