package aquarium

import kotlin.math.sqrt
import kotlin.math.tan

private class Polygon (
    val polygonType: Pair<String,Int>,
    val sides: Map<Int,Float>,
    var width: Float,
    var length: Float,
    var height: Float?,
    var area: Float?,
    var perimeter: Float?,
    var isRegular: Boolean
) {
    fun printProperties() {
        print("Polygon name: ${polygonType.first}. (Sides: ${polygonType.second})\nSides measures:\n${sides
            .forEach { println("Side #${it.key+1}: ${it.value} .") }}\nPolygon width: $width ." +
                "\nPolygon length: $length .\n Polygon height: $height .")
    }

    init {
        //If all sides have the same measure then define Polygon as regular and set respective regular perimeter value
        if (sides.values.all { it.compareTo(sides.values.first()) == 0 }) {
            isRegular = true
            perimeter = regularPolygonPerimeter(sides)
        } else isRegular = false;perimeter = null

        area = when (polygonType.first) {
            "Triangular" -> {triangleArea(sides,isRegular)!!}
            "Rectangular" -> {rectangleArea(length,width,isRegular)!!}
            "Trapezoidal" -> {trapezoidalRectangleArea(sides,isRegular)}
            "Pentagonal" -> {pentagonArea(sides,isRegular)}
            "Hexagonal" -> {regularPolygonPerimeter(sides)}
            "Heptagonal" -> {regularPolygonArea(sides)}
            "Octagonal" -> {regularPolygonArea(sides)}
            "Nonagonal" -> {regularPolygonArea(sides)}
            "Decagonal" -> {regularPolygonArea(sides)}
            else -> null
        }
    }

    fun triangleArea(sidesMeasures: Map<Int,Float>, isRegular: Boolean): Float? {
        val sides = mutableMapOf<Int,Float>()
        var flag = 0
        val base: Float = sidesMeasures.values.max()!!
        val area: Float
        val semiperimeter: Float


        //Setting the sides from max to min value.
        sidesMeasures.toSortedMap(compareBy { it }).forEach { it -> if (base >= it.value && it.key != sidesMeasures.maxBy { it.value }!!.key) sides[flag++] = it.value }
        /*sidesMeasures.values.sortedDescending().toList().forEach { if(base > it) sides[flag++] = it }*/
        /*for (measures in sidesMeasures.values.sortedDescending().toList()){
            if (base > measures) {
                sides[flag++] = measures
            }
        }*/

        //Getting height with Heron's formula, having the whole sides measures but not the height
        //The sum of all the sides divided by 2 to get the semi perimeter
        semiperimeter = (base + sides.values.sum()).div(2)

        //Do a subtraction
        sides.forEach { semiperimeter - it.value }
        /*for (keys in sides.keys) {
            sides[keys] = semiperimeter - sides[keys]!!
        }*/

        //Once the subtraction is done, the result must be multiplied by the semi-perimeter
        sides.forEach { it.value * semiperimeter }

        //Here the base is getting subtracted and multiplied (Subtraction that was already done on the rest of the sides but not on the base side)
        area = (semiperimeter * (semiperimeter - base)) + sides.values.toList().reduce{allSides,nextSide -> allSides + nextSide} // area = sides.values.sum()
        return sqrt(area)
    }
    fun rectangleArea(length: Float, width: Float, isRegular: Boolean): Float? {
        return length.times(width)
    }
    fun trapezoidalRectangleArea(sidesMeasures: Map<Int, Float>, isRegular: Boolean): Float {
        //Key 0 : Base 1 - Key 1 : Base 2
        val width: MutableMap<Int, Float> = sidesMeasures.filter { it.key in 0..1 }.toMutableMap()
        //Key 1 : Side 3 - Key 2 : Side 4
        val length: MutableMap<Int, Float> = sidesMeasures.filter { it.key in 2..3 }.toMutableMap()

        return (1/2)*(width.values.sum())*length.values.first()

        /*
        //Wrong stuff
        val average: Float = (sidesMeasure.values.sum()).div(sidesMeasure.values.size)

        for ((key, value) in sidesMeasure) {
            width[(sidesMeasure.minBy { it.value })!!.key] = value

            if (average > value) {
                if (value == sidesMeasure.values.min()) {
                    width[(sidesMeasure.minBy { it.value }!!.key)] = value
                } else if (value >= sidesMeasure.values.min()!!) {
                    width[key] = value
                }
            } else if (average <= value) {
                if (value == sidesMeasure.values.max()) {
                    length[(sidesMeasure.minBy { it.value }!!.key)]
                } else if (value >= sidesMeasure.values.max()!!) {
                    length[key] = value
                }
            } else throw Exception("Average out of bounds")
        }*/


    }
    fun pentagonArea(sidesMeasures: Map<Int, Float>, isRegular: Boolean): Float {
        //Regular polygon perimeter = sideMeasure.times(sidesQuantity)
        val perimeter: Float = sidesMeasures.values.first().times(sidesMeasures.values.size)
        //Apothem
        val apothem: Float = regularPolygonApothem(sidesMeasures)
        return (1/2).times((((5).times(sidesMeasures.values.first()))*apothem))
    }
    fun regularPolygonArea(sidesMeasures: Map<Int, Float>): Float {
        return (1/2).times(((sidesMeasures.size).times(sidesMeasures.values.first())).times(regularPolygonApothem(sidesMeasures)))
    }
    fun regularPolygonPerimeter(sidesMeasures: Map<Int, Float>): Float {
        return sidesMeasures.values.first().times(sidesMeasures.size)
    }
    fun regularPolygonApothem(sidesMeasure: Map<Int, Float>): Float {
        return ((sidesMeasure.values.first()).div(2* tan(((180).div(sidesMeasure.values.size).toFloat()))))
    }
}