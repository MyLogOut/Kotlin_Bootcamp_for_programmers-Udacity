package aquarium

import java.lang.NumberFormatException
import kotlin.Exception
import kotlin.math.sqrt
import kotlin.math.tan
import kotlin.random.Random

var myAquarium: MutableList<FishTank?>? = mutableListOf()
lateinit var myFish: MutableList<Fish?>
lateinit var formType: Map<String, Int>

fun main(args: Array<String>) {
    println("Hello, rob32!")
    val flag = 0
    do {
        when (navigationMenu(0)) {
            0 -> println("Please select a valid option")
            1 -> when (navigationMenu(1)) {//Enters to AquariumNavMenu
                1 -> myAquarium = insertingAquarium(myAquarium)
                2 -> showElements(myAquarium, null, false)
                3 -> writeAquarium(myAquarium, showElements(myAquarium, null, true))
                4 -> break

            }
            2 -> when (navigationMenu(2)) {//Enters to FishNavMenu
                1 -> {
                    val newFish = insertingFish(myFish, myAquarium) ?: Pair(myFish.size -1 , Fish())
                    myFish.add(newFish.first, newFish.second)
                }
                2 -> showElements(myAquarium, null, false)
                3 -> {
                    fitMoreFish(
                        if (showElements(
                            null,
                            if (::myFish.isInitialized) {
                                println(::myFish.isInitialized)
                                myFish
                            } else emptyList<Fish>().toMutableList(), true
                        ) == null) insertingAquarium(myAquarium)!!.lastIndex else myAquarium?.lastIndex

                        ,
                        true
                    )
                }
                4 -> break
            }
            3 -> feedTheFish()
            4 -> {
                return
            }
            else -> {
                println("Unexpected error, please restart.")
            }
        }
    } while (flag == 0)
}

fun navigationMenu(menu: Int): Int {
    when (menu) {
        0 -> {
            println("Welcome to this Aquarium creator engine")
            println("Please choose among the options below:")

            println("\n\n\n1- Aquariums.\n2- Fish.\n3- Feed the fishes.\n4- Exit")
        }
        1 -> {
            println("Welcome to the Aquarium navigation menu")
            println("Please choose among the options below:")

            println("\n\n\n1- Add an aquarium.\n2- Show existing aquariums.\n3- Modify an aquarium.\n4- Return")
        }
        2 -> {
            println("Welcome to the Fish navigation menu")
            println("Please choose among the options below:")

            println("\n\n\n1- Add a fish.\n2- Show existing fish.\n3- Modify a fish.\n4- Return")
        }
        else -> println("Please type a valid option")
    }

    var option: Any? = null

    if (option == null) do {
        option = readLine().toString().toInt()
    } while (option.toString().toInt() > 4)
    when (option) {
        in 1..4 -> return option.toString().toInt()
    }
    return 0
}

fun writeAquarium(aquariumList: MutableList<FishTank?>?, id: Int?): FishTank? {

    val aquariumElements = listOf(
        "name", "width", "length",
        "depth", "materialType", "true or false answer if it contains decorations"
    )

    val aquariumValues = mutableMapOf<String, Any?>().withDefault { 0.0F }
    println("Please enter the corresponding values for this aquarium:")

    for (elements in aquariumElements.indices) {
        println("Define the//an aquarium ${aquariumElements[elements]}: ")
        try {
            when (elements) {
                in 3..4 -> {
                    if (aquariumElements[2].findAnyOf(arrayListOf("Rectangular","4"),0,true) != null){
                        formType = mapOf(readLine().toString() to elements)
                    } else aquariumValues[aquariumElements[elements]] = 0.0F
                }
                else -> { aquariumValues[aquariumElements[elements]] = readLine() }
            }
        } catch (e: Exception) {
            println(e)
            if (elements != 0) elements.minus(1)
            else
                println("Please fill all the spaces given")
            break // If elements != 0, reduce the countdown,
            // If not just break the catch
        }
    }
    println("Define this Aquarium's shape: ")
    do {
        var formSpecs = formData(readLine().toString())
        when (formSpecs.values.first()) {
            in 0..2 -> {
                println("Please insert a valid polygon value i.e(Id Est): \nRectangular or 4, Pentagonal or 5 and so on from 3 up to 10 angles or sides")
                formSpecs = formData(readLine().toString())
                formType = formSpecs
            }
            in 3..10 -> formType = formSpecs
            else -> println("Please define a polygon value between the supported range (from 3 sides up to 10).")
        }
    } while (formSpecs.values.firstOrNull() ?: 11 > 10 || formSpecs.values.firstOrNull() ?: 0 <= 2)
    println("TEST PURPOSE\n"+
        "${aquariumValues["name"]}, " +
                "${aquariumValues["length"]}, " +
                "${aquariumValues["waterFilled"]}, " +
                "${aquariumValues["width"]}, " +
                "${if(::formType.isInitialized) formType else mapOf("Rectangular" to 4)}, " +
                "${aquariumValues["materialType"]}" +
                "${aquariumValues["true or false answer if it contains decorations"]}"
    )
    if (aquariumList == null) {
        return FishTank(
            aquariumValues["name"].toString(),
            if(::formType.isInitialized) formType else mapOf("Rectangular" to 4),
            aquariumValues["width"].toString().toFloat(),
            aquariumValues["length"].toString().toFloat(),
            aquariumValues["depth"].toString().toFloat(),
            materialType = aquariumValues["materialType"].toString(),
            hasDecorations = aquariumValues["true or false answer if it contains decorations"].toString().toBoolean()
        )
    } else { //rewrite Aquarium || "Edit" Aquarium
        for (elements in 0 until aquariumList.size) {
            when (elements) {
                id -> {
                    myAquarium!![elements]!!.name = aquariumValues["name"].toString()
                    myAquarium!![elements]!!.width = aquariumValues["width"].toString().toFloat()
                    myAquarium!![elements]!!.length = aquariumValues["length"].toString().toFloat()
                    myAquarium!![elements]!!.depth = aquariumValues["depth"].toString().toFloat()
                    myAquarium!![elements]!!.materialType = aquariumValues["materialType"].toString()
                    myAquarium!![elements]!!.formType = formType
                    myAquarium!![elements]!!.hasDecorations =
                        aquariumValues["true or false answer if it contains decorations"].toString().toBoolean()
                }
                else -> {
                }
            }
        }
        return null
    }
}

fun insertingAquarium(aquariumList: MutableList<FishTank?>?): MutableList<FishTank?>? {
    val myAquarium: MutableList<FishTank?>? = aquariumList
    //2 options: rewrite or update, chosen: update. Correction: 1 option: rewrite
    // If there are no aquariums then add a new one at index 0
    if (!myAquarium!!.any()) {
        val firstAquarium: FishTank = writeAquarium(null, null) ?: FishTank()
        println(
            "${firstAquarium.name}, " +
                    "${firstAquarium.length}, " +
                    "${firstAquarium.waterFilled}, " +
                    "${firstAquarium.width}, " +
                    "${firstAquarium.formType}, " +
                    "${firstAquarium.materialType}"
        )
        myAquarium.add(firstAquarium)
    } else {
        /*Is there any existing aquarium? Then add this one to a new Array field,
            right after the last one*/
        for (elements in 0 until myAquarium.size) {
            if (elements.compareTo(myAquarium.size - 1) == 0) {
                myAquarium.add(elements + 1, writeAquarium(null, null) as FishTank)
            } else {
                println("The list is empty, please contact support to solve this problem.")
            }
        }
    }
    return myAquarium
}

fun insertingFish(myFish: MutableList<Fish?>?, myAquarium: MutableList<FishTank?>?): Pair<Int, Fish>? {
    lateinit var newFishWithIndex: Pair<Int, Fish>
    if (myAquarium!!.isNotEmpty()) {
        if (myFish!!.isEmpty()) { //If there are no Fish already then add the new and first one.
            myFish.add(0, fitMoreFish(
                run {
                    println("Please specify in which Aquarium this fish is going to be:")
                    val id: Int? = showElements(myAquarium, null, true)
                    id
                }
                , false))
        } else {
            for (elements in 0 until myFish.size - 1) {
                if (myFish.size.compareTo(elements) == 0) { //If size of actual directory is > 0 and its quantity is equal to elements then add this new Fish.
                    newFishWithIndex = Pair(
                        elements + 1, fitMoreFish(
                            run {
                                println("Please specify in which Aquarium this fish is going to be:")
                                val id: Int? = showElements(myAquarium, null, true)
                                id
                            }
                            , false) ?: Fish()
                    )
                }
            }
        }
        return newFishWithIndex
    } else println("Looks like there aren't any aquariums yet, please add at least one in order to proceed."); return null
}

fun showElements(aquariumList: MutableList<FishTank?>?, fishList: MutableList<Fish?>?, indicator: Boolean?): Int? {
    if (myAquarium!!.isNotEmpty() && !fishList.isNullOrEmpty()) {
        println("\nProviding all the recorded Aquariums:")
        println("\nID       |       Aquarium Name\n")
        for (elements in aquariumList!!.indices) {
            println("$elements        |       ${aquariumList[elements]!!.name}\n")
        }
        if (indicator == true) println("Define the Aquarium where your fish is going to be put")
        else println("Insert the ID of the Aquarium that you want to expand")
        var id: String?
        do {
            id = readLine()
        } while (!(id.toString().toInt() >= myAquarium!!.indices.first() && id.toString().toInt() <= myAquarium!!.indices.last())) //While it isn't inside the bounds.

        for (elements in aquariumList.indices) try {
            if (id.run { toString().toInt() } == elements) {
                aquariumList[elements]!!.details() //prints the whole details of this Aquarium

                println("\n\nWould you like to edit something of this Aquarium?")
                if (readLine().toString().findAnyOf(
                        arrayListOf("y", "yes", "ye", "yup", "yea", "yeah"),
                        0,
                        true
                    ) != null
                ) {
                    writeAquarium(aquariumList, id!!.toInt())
                } else if (indicator == true) return id.toString().toInt()
                else continue
            }
        } catch (e: IndexOutOfBoundsException) {
            println("${e}\nPlease insert an Integer number in order to find the Aquarium you're looking for.\n")
        }

    } else if (!fishList.isNullOrEmpty()) {
        println("\nProviding all the recorded Fish:")
        println("\nID       |       Aquarium Name\n")
        for (elements in fishList!!.indices) {
            println("$elements        |       ${fishList[elements]!!.name}\n")
        }
        println("Insert the ID of the Fish that you want to expand")
        var id: String? //Already initialized as null
        do {
            id = readLine()
        } while (id.toString().toInt() >= myFish!!.indices.first() && id.toString().toInt() <= myFish!!.indices.last())

        for (elements in fishList.indices) try {
            if (id.run { toString().toInt() } == elements) {
                fishList[elements]!!.details() //prints the whole details of this Fish

                println("\n\nWould you like to edit something of this Fish?")
                if (readLine().toString().findAnyOf(arrayListOf("y", "yes", "ye", "yup", "yeah"), 0, true) != null) {
                    fitMoreFish(id!!.toInt(), true)
                } else if (indicator == true) return id.toString().toInt()
            }
        } catch (e: IndexOutOfBoundsException) {
            println("${e}\nPlease insert an Integer number in order to find the Aquarium you're looking for.\n")
        }
    } else {
        println("It seems like something's empty here,\n you must meet all the requirements to achieve this task successfully, ${if (fishList?.isEmpty() == true) "since there are no fish at all." else ""}")
    }
        return null
}

fun fitMoreFish(id: Int?, indicator: Boolean?): Fish? {
    if (myAquarium != null) {
        println("Please enter the corresponding values for this Fish:")

        val fishProperties = mutableMapOf /*<String, Any?>*/(
            "name" to null, "width" to Float, "length" to Float,
            "girth" to Float, "measure unit" to String, "current aquarium" to String
        )

        val fishColors = mutableMapOf<String, MutableList<String>>("colours" to mutableListOf())
        //Elements = Keys of the Map
        for ((elements) in fishProperties) {
            println("Type the fish $elements: ")
            try {
                while (elements.compareTo(fishColors.keys.first()) == 0) /*When this compareTo it's true then do this
                Note: The compareTo it's happening between two string keys*/ {
                    if (elements == "colours") {
                        println("How many colours?")
                        when (readLine().toString().toInt()) {
                            in 1..10 -> {
                                println("Please type the color and press enter to insert the next one:")
                                repeat(readLine().toString().toInt()) {
                                    fishColors["colours"]!!.add(readLine().toString())
                                }
                            }
                            else -> println("This fish should not have more than 10 colours (Inserted: ${readLine().toString()})")
                        }
                        continue
                    }
                    continue
                }
            } catch (e: Exception) {
                println("Fish input failed: $e.\nPlease try again")
                return null
            }
        }

        val newFish = Fish(
            fishProperties["name"].toString(),
            colours = mutableListOf(if (!fishColors["colours"].isNullOrEmpty()) fishColors["colours"]!![0] else "Not specified"),
            width = 0.51F, type = "Gudgeon",
            currentAquarium = myAquarium!![id!!]!!.name.toString()
        )

        println("Is the current Aquarium okay?")
        if (readLine().toString().findAnyOf(
                arrayListOf("y", "yes", "ye", "yup", "yea", "yeah"),
                0,
                true
            ) != null
        ) {
            myAquarium!![id!!]!!.run {
                addThisFish(newFish) // Registering the fish in the selected Aquarium
            }
        } else {
            myAquarium!![showElements(myAquarium, null, true)!!]!!.run {
                addThisFish(newFish) // Registering the fish in the selected Aquarium
            }
        }
        if (indicator == true) { //Indicates if this is an editing.
            myFish[id!!] = newFish
            println("Fish successfully updated.\n")
        } else { //If not then just do the normal Fish addition.
            println(
                "Width left: ${myAquarium!![id]!!.fishSpace["width"]!!.minus(myAquarium!![id]!!.width!!)} cm" +
                        "\nLength left: ${myAquarium!![id]!!.fishSpace["length"]} cm" +
                        "\nTotal volume: ${myAquarium!![id]!!.cubicFishCapacity} cm^3" +
                        "\nWater level: ${myAquarium!![id]!!.waterFilled} mL." +
                        "\nCurrent Aquarium: ${newFish.currentAquarium}"
            )
            return newFish
        }
    } else println("There's no Aquarium created yet.")
    return null
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")
    if (shouldChangeWater((day))) {
        println("Change the water today")
    }

}


fun randomDay(): String {
    val week = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    return week[Random.nextInt(0, 7)]
}

fun fishFood(day: String): String {
    return when (day) {
        "Monday" -> "flakes"
        "Tuesday" -> "pallets"
        "Wednesday" -> "redworas"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Saturday" -> "lettuce"
        "Sunday" -> "plankton"
        else -> "No food fund"
    }
}

fun formData(form: String): Map<String,Int>{
    return when {//If prism then do the prism Math and return the prism sides value, if not return the map
        form.findAnyOf(arrayListOf("Trian", 3.toString()),0,true) != null -> mapOf("Triangular" to 3)
        form.findAnyOf(arrayListOf("Recta", 4.toString()),0,true) != null -> mapOf("Rectangular" to 4)
        form.findAnyOf(arrayListOf("Trapez"),0,true) != null -> mapOf("Trapezoidal" to 4)
        form.findAnyOf(arrayListOf("Penta", 5.toString()),0,true) != null -> mapOf("Pentagonal" to 5)
        form.startsWith("Hexa",true) || form.startsWith(6.toString()) -> mapOf("Hexagonal" to 6)
        form.startsWith("Hepta",true) || form.startsWith(7.toString()) -> mapOf("Heptagonal" to 7)
        form.startsWith("Octa",true) || form.startsWith(8.toString()) -> mapOf("Octagonal" to 8)
        form.startsWith("Nona",true) || form.startsWith(9.toString()) -> mapOf("Nonagonal" to 9)
        form.startsWith("Deca",true) || form.startsWith(10.toString()) -> mapOf("Decagonal" to 10)
        else -> mapOf("Not specified" to 0)
    }
}

fun prismMath(form:Map<String,Int>, depth: Float, width: Float, length: Float): Map<String,Float>? {
    val sidesMeasure:MutableMap<Int,Float?> = mutableMapOf()
    println("Does each or some sides has custom measures?")
    //If it has custom measures ask for each, or it is between 5 to 10 sides, or if it is a Trapezoidal, else ask for the unique side measure.
    if (readLine().toString().findAnyOf(listOf("Yes", "Yeah", "Yea", "ye", "yup","1"), 0, true) != null
        || form.keys.first().findAnyOf(arrayListOf("Trapez"),0,true) != null) {
        if (form.keys.first().findAnyOf(arrayListOf("Trapez")) != null) {
            for (sides in 1..form.values.first()) {
                if (sides in 1..2) {
                    println("Please define the measure of the parallel base number $sides (in centimeters) of your ${form.keys.first()} prism.")
                    print("Note: The bases are those sides which are parallel with each other: ")
                } else println("Please define the measure of the side number $sides (in centimeters) of your ${form.keys.first()} prism.\n" +
                        "Note: These sides must have the same measure: ")
                do {
                    sidesMeasure[sides-1] = try { readLine().toString().toFloat() } catch (e: NumberFormatException) { null }
                } while (sidesMeasure[sides] == null)
            }
            TODO("Ask for the sides correctly positioned to each other to recreate or define the prism as it is in real life and not " +
                    "with one or more sides wrongly positioned")
        } else {
            for (sides in 1..form.values.first()) {
                println("Please define the measure of the side number $sides (in centimeters) of your ${form.keys.first()} prism:")
                do {
                    sidesMeasure[sides-1] = try { readLine().toString().toFloat() } catch (e: NumberFormatException) { null }
                } while (sidesMeasure[sides] == null)
            }
        }

    } else {
        print("Please define the measure (in cm) for the ${form.values.first()} sides of your ${form.keys.first()} prism: ")
        for (sides in 1..form.values.first()) {
            print("Side $sides: ")
            do {
                sidesMeasure[sides - 1] = try {
                    readLine().toString().toFloat()
                } catch (e: NumberFormatException) {
                    null
                }
            } while (sidesMeasure[sides - 1] == null)
            print(" cm.\n")
        }
    }

    when (form.values.first()) {
        3 -> { return mapOf(form.keys.first() to triangleVolume(triangleArea(sidesMeasure.mapValues {
                it.value!!.toFloat()
            })!!,depth))
        }
        4 -> {
            return if (form.keys.first().findAnyOf(arrayListOf("Trapez"),0,true) != null)
                mapOf(form.keys.first() to trapezoidalRectangleVolume(trapezoidalRectangleArea(sidesMeasure.toMap().mapValues {
                    it.value!!.toFloat()
                }),depth))
            else mapOf(form.keys.first() to rectangleVolume(rectangleArea(length,width)!!,depth))
        }
        5 -> { return mapOf(form.keys.first() to pentagonVolume(pentagonArea(sidesMeasure.mapValues {
            it.value!!.toFloat()
        }), depth)) }
        6 -> {  }
        else -> return null
    }

    return mapOf("as" to 1f)

}

fun triangleBaseHeight(sidesMeasure: Map<Int, Float>): Float {
    //#ALGEBRA Get the Base height with the Area previously obtained using Heron's formula.
    val base = sidesMeasure.values.max()
    val area = triangleArea(sidesMeasure)
    val height = { baseSize: Float -> baseSize*1/2 }

    return area!!.div(height(base!!?:0F)) //Height
}
//Area: cm^2 && Volume: cm^3
fun triangleVolume(area: Float, depth: Float): Float { return area*depth }
fun triangleArea(sidesMeasure: Map<Int,Float>): Float? {
    val sides = mutableMapOf<Int,Float>()
    var flag = 0
    val base: Float = sidesMeasure.values.max()!!
    val area: Float
    val semiperimeter: Float


    //Setting the sides from max to min value.
    sidesMeasure.toSortedMap(compareBy { it }).forEach { it -> if (base >= it.value && it.key != sidesMeasure.maxBy { it.value }!!.key) sides[flag++] = it.value }
    /*sidesMeasures.values.sortedDescending().toList().forEach { if(base > it) sides[flag++] = it }*/
    /*for (measures in sidesMeasures.values.sortedDescending().toList()){
        if (base > measures) {
            sides[flag++] = measures
        }
    }*/

    //Getting height with Heron's formula, having the whole sides sizes but not the height
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
fun rectangleVolume(area: Float, depth: Float): Float { return area.times(depth) }
fun rectangleArea(length: Float, width: Float): Float? {
    return length.times(width)
}
fun trapezoidalRectangleVolume(area: Float, depth: Float): Float { return area*depth } //cm^3
fun trapezoidalRectangleArea(sidesMeasure: Map<Int, Float>): Float {
    //Key 0 : Base 1 - Key 1 : Base 2
    val width: MutableMap<Int, Float> = sidesMeasure.filter { it.key in 0..1 }.toMutableMap()
    //Key 1 : Side 3 - Key 2 : Side 4
    val length: MutableMap<Int, Float> = sidesMeasure.filter { it.key in 2..3 }.toMutableMap()

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


} //cm^2
fun pentagonVolume(area: Float, depth: Float): Float { return area*depth }
fun pentagonArea(sidesMeasures: Map<Int, Float>): Float {
    //Regular polygon perimeter = sideMeasure.times(sidesQuantity)
    val perimeter: Float = sidesMeasures.values.first().times(sidesMeasures.values.size)
    //Apothem
    val apothem: Float = regularPolygonApothem(sidesMeasures)
    return (1/2).times((((5).times(sidesMeasures.values.first()))*apothem))
}
fun regularHexagonArea(sidesMeasures: Map<Int, Float>): Float? {
    val sides = sidesMeasures.values.toList()
    return if (sides.all { it == sides.first() }) return TODO("regularHexagonArea = Area of [ 6 triángulos equiláteros ]")
    else null

}
fun regularPolygonApothem(sidesMeasure: Map<Int, Float>): Float {
    return ((sidesMeasure.values.first()).div(2* tan(((180).div(sidesMeasure.values.size).toFloat()))))
}

