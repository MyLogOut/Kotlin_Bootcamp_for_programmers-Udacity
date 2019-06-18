import kotlin.random.Random

fun main(args: Array<String>) {
    for (i in 0..10) {
        val flag = getFortuneCookie(getBirthDay())
        if(flag.contains("Take it easy and enjoy life!")) break else println(flag)
    }
}

fun getBirthDay(): Int{
    print("Please enter your birthday: ")
    val flag = readLine()
    return if (flag!!.toIntOrNull() != null && flag!!.toIntOrNull()!! <= 100) flag!!.toInt() else 1
}
fun getFortuneCookie(birthDay: Int): String {
    val fortuneMessage = listOf(
        "You will have a great day!",
        "Things will go well for you today.",
        "Enjoy a wonderful day of success.",
        "Be humble and all will turn out well.",
        "Today is a good day for exercising restraint.",
        "Take it easy and enjoy life!",
        "Treasure your friends because they are your greatest fortune."
    )
    return when(birthDay){
        in 28..31 -> fortuneMessage[2]
        in 1..6  -> fortuneMessage[6]
        else -> {println("Birthday rem ="+ birthDay.rem(fortuneMessage.size-1))
        fortuneMessage[

                if(birthDay.rem(fortuneMessage.size) > fortuneMessage.size - 1) Random.nextInt(0,fortuneMessage.size - 1)
                else birthDay.rem(fortuneMessage.size)]
        }
    }
}