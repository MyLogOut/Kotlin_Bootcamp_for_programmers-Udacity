package test

import java.util.*

fun main() {
    var sidesMeasure: MutableMap<Int,Float?> = mutableMapOf()
    sidesMeasure[1] = 110F
    sidesMeasure[2] = 210F
    println("${sidesMeasure.keys} "+"${sidesMeasure.values}")

    val sides:Collection<Int> = sidesMeasure.keys
    //Was trying to pass mutable plus Float? to non-mutable + Float
    val measures:Collection<Float> = Collections.unmodifiableCollection<Float>(sidesMeasure.values)
    println(sides)
    println(measures)
}