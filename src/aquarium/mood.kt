package aquarium

fun main() {


    println(whatShouldIDoToday(whatsYourMood()))

    println(whatShouldIDoToday(whatsYourMood()))

}

fun getDecorations(): String {
    return listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot").random()
}

fun whatsYourMood(): String {
    return when ((0..99).random()) {
        in (0..24) -> "Bad"
        in (25..49) -> "Not that good"
        in (50..74) -> "Fine"
        in (75..99) -> "Good"
        else -> "Bad"
    }
}
fun whatShouldIDoToday(mood: String, weather: String = "Sunny", temperature: Float = 24.0F): String {
    return when {
        isGood(mood) && isSunny(weather) -> "Go for a walk"
        isGood(mood) && isHot(temperature) -> "Drink a cola"
        isSad(mood) && isCold(temperature) -> "Drink a coffee"
        isSad(mood) && isRainy(weather) -> "Take a nap"
        else -> "Stay home and read"
    }
}

fun isGood(mood: String) = mood == "Fine" || mood == "Good"
fun isSad(mood: String) = mood == "Sad" || mood == "Not that good"
fun isSunny(weather: String) = weather == "Sunny"
fun isRainy(weather: String) = weather == "Rainy"
fun isHot(temperature: Float) = temperature > 30.0f
fun isCold(temperature: Float) = temperature <= 0.0f
