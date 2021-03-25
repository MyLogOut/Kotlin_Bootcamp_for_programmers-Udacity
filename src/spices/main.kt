package spices

import kotlin.random.Random

fun main() {
    val curry = SimpleSpice(name ="Curry", heat = Random.nextFloat()*100.0F)
    val curry2 = SimpleSpice(name ="Curry", heat = Random.nextFloat()*100.0F)
    println("Determining ${curry.name} spiciness [current: ${curry.spiciness}] level: ${curry.determineSpiciness()}")

    val spiceContainer = SpiceContainer("${curry.determineSpiciness()} ${curry.name}", curry)
    val spiceContainer2 = SpiceContainer("${curry2.determineSpiciness()} ${curry2.name}", curry2)

    val spiceCabinet: List<SpiceContainer> = listOf(spiceContainer, spiceContainer2)
    for (container in spiceCabinet) {
        println(container.content)
        println(container.color)
        println(container)
        container.open()
        println("You try to get 10mg of ${container.label}")
        container.get(10)
        container.close()

        println(container.content)
        println(container.color)
        println(container)
        container.open()
        println("You try to get 10mg of ${container.label}")
        container.get(10)
        container.close()

        println(container.content)
        println(container.color)
        println(container)
        container.open()
        println("You try to get 10mg of ${container.label}")
        container.get(10)
        container.close()

    }
}