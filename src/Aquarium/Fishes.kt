package Aquarium

import Blog.Article
import addToBlog
import kotlin.Exception
import kotlin.random.Random

fun main(args: Array<String>) {
    println("Hello, ${args[0]}!")
    var myAquarium: ArrayList<FishTank?>? = null
    var myFish: ArrayList<Fish?>? = null
    when (navigationMenu()) {
        0 -> println("Please select a valid option")
        1 -> { // If there are no aquariums then add a new one at index 0
            if (myAquarium == null) myAquarium!!.add(0, createAquarium())
            else {
                /*Is there any existing aquarium? Then add this one to a new Array field,
                    right after the last one*/
                for (elements in 0 until myAquarium.size - 1) {
                    if (myAquarium.size.compareTo(elements) == 0) {
                        myAquarium.add(elements + 1, createAquarium())
                    }
                }
            }
        }
        2 -> if (myFish == null) myFish!!.add(0, fitMoreFish(myAquarium!!.last()))
        else for (elements in 0 until myFish.size - 1)
            if (myFish.size.compareTo(elements) == 0)
                myFish.add(elements + 1, fitMoreFish(myAquarium!!.last()))
        3 -> feedTheFish()
        4 -> {
            return
        }
        else -> {
            println("Unexpected error, please restart.")}
    }
    do {
        println("Do you want to create a new Aquarium? [Y],[N]")
        when (readLine() == "Y" || readLine() == "y" || readLine() == "Yes") {
            true -> try {
                createAquarium()
            } catch (e: Exception) {
                println(e)
            }
            else -> println("See you then")
        }
        /*if ( readLine() == "Y" || readLine() == "y"  || readLine() == "Yes"){
            continue
        } else break*/
    } while (readLine() != "N" || readLine() != "n" || readLine() != "No")
}

fun navigationMenu(): Int {
    println("Welcome to this Aquarium creator engine")
    println("Please choose among the options below:")

    println("\n\n\n1- Add an aquarium.\n2- Add a fish.\n3- Feed the fishes.\n4- Exit")
    var option: Int = 0

    while (option !is Int || option.toString().toInt() > 4) {
        option = readLine().toString().toInt()
    }
    when (option) {
        in 1..4 -> return option.toString().toInt()
    }
    return 0
}

fun createAquarium(): FishTank {
    val aquariumElements = listOf(
        "name", "width", "length",
        "depth", "material type", "form type", "true or false answer if it contains decorations"
    )

    val aquariumValues = mutableMapOf<String, Any?>().withDefault { 0.0F }
    println("Please enter the corresponding values for this aquarium:")

    for (elements in 0 until aquariumElements.size - 1) {
        println("Type an aquarium ${aquariumElements[elements]}: ")
        try {
            aquariumValues[aquariumElements[elements]] = readLine()
        } catch (e: Exception) {
            println(e)
            if (elements != 0) elements.minus(1) else break // If elements != 0, reduce the countdown,
                                                                  // If not just break the catch
        }
    }

    return FishTank(
        aquariumValues["name"].toString(),
        aquariumValues["width"].toString().toFloat(),
        aquariumValues["length"].toString().toFloat(),
        aquariumValues["depth"].toString().toFloat(),
        materialType = aquariumValues["material type"].toString(),
        formType = aquariumValues["form type"].toString(),
        hasDecorations = aquariumValues["true or false answer if it contains decorations"].toString().toBoolean()
    )
}

fun fitMoreFish(myAquarium: FishTank?): Fish? {
    if (myAquarium != null) {
        println("Please enter the corresponding values for this Fish:")
        /*val fishProperties = listOf(
            "name",
            "colours",
            "width",
            "length",
            "girth",
            "measure unit",
            "current aquarium"
        )*/
        val fishProperties = mutableMapOf /*<String, Any?>*/(
            "name" to null, "width" to Float, "length" to Float,
            "girth" to Float, "measure unit" to String, "current aquarium" to String)

        val fishColors = mutableMapOf<String,MutableList<String>>("colors" to mutableListOf())
            //Elements = Keys of the Map
        for ((elements) in fishProperties) {
            println("Type the fish $elements: ")
            try {
                while (elements.compareTo(fishColors.keys.first()) == 0) {
                    if (elements == "colours") {
                        println("How many colours?")
                        when (readLine().toString().toInt()) {
                            in 1..10 -> {
                                    println("Please type the color and press enter to insert the next one:")
                                    repeat(readLine().toString().toInt()){
                                        fishColors["colors"]!!.add(readLine().toString())
                                    }
                                }
                            else -> println("This fish should not have more than 10 colors (Inserted: ${readLine().toString()})")
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
            colours = mutableListOf(fishColors["colours"]!![0]),
            width = 0.51F, Type = "Gudgeon",
            currentAquarium = myAquarium.name.toString()
        )
        myAquarium.run {
            addThisFish(newFish) // Registering the fish in the selected Aquarium
        }
        println(
            "Width left: ${myAquarium.fishSpace["width"]!!.minus(myAquarium.width!!)} cm" +
                    "\nLength left: ${myAquarium.fishSpace["length"]} cm" +
                    "\nTotal volume: ${myAquarium.cubicFishCapacity} cm^3" +
                    "\nWater level: ${myAquarium.waterFilled} mL." +
                    "\nCurrent Aquarium: ${newFish.currentAquarium}"
        )
        return newFish
    } else println("There's no Aquarium created yet.")
    return null
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println("Today is $day and the fish eat $food")
}


fun randomDay(): String {
    val week = listOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
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