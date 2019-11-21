package Aquarium

import kotlin.Exception
import kotlin.random.Random

fun main(args: Array<String>) {
    println("Hello, ${args[0]}!")
    var myAquarium: MutableList<FishTank?>? = mutableListOf()
    var myFish: MutableList<Fish?>? = mutableListOf()
    var flag = 0
    do {
        when (navigationMenu(0)) {
            0 -> println("Please select a valid option")
            1 -> when (navigationMenu(1)) {//Enters to AquariumNavMenu
                1 -> myAquarium = insertingAquarium(myAquarium)
                2 -> showElements(myAquarium, null,false)
                3 -> {
                }
                4 -> return

            }
            2 -> when (navigationMenu(2)) {//Enters to FishNavMenu
                1 -> myFish = insertingFish(myFish,myAquarium)
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

    /*do {
        println("Do you want to create a new Aquarium? [Y],[N]")
        when (readLine() == "Y" || readLine() == "y" || readLine() == "Yes") {
            true -> try {
                createAquarium()
            } catch (e: Exception) {
                println(e)
            }
            else -> println("See you then")
        }
        *//*if ( readLine() == "Y" || readLine() == "y"  || readLine() == "Yes"){
            continue
        } else break*//*
    } while (readLine() != "N" || readLine() != "n" || readLine() != "No")*/
}

fun navigationMenu(menu: Int): Int {
    if (menu == 0) {
        println("Welcome to this Aquarium creator engine")
        println("Please choose among the options below:")

        println("\n\n\n1- Aquariums.\n2- Fish.\n3- Feed the fishes.\n4- Exit")
    } else if (menu == 1) {
        println("Welcome to the Aquarium navigation menu")
        println("Please choose among the options below:")

        println("\n\n\n1- Add an aquarium.\n2- Show existing aquariums.\n3- Modify an aquarium.\n4- Return")
    } else if (menu == 2) {
        println("Welcome to the Fish navigation menu")
        println("Please choose among the options below:")

        println("\n\n\n1- Add a fish.\n2- Show existing fish.\n3- Modify a fish.\n4- Return")
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

fun createAquarium(): FishTank {
    val aquariumElements = listOf(
        "name", "width", "length",
        "depth", "materialType", "formType", "true or false answer if it contains decorations"
    )

    val aquariumValues = mutableMapOf<String, Any?>().withDefault { 0.0F }
    println("Please enter the corresponding values for this aquarium:")

    for (elements in 0 until aquariumElements.size) {
        println("Type an aquarium ${aquariumElements[elements]}: ")
        try {
            aquariumValues[aquariumElements[elements]] = readLine()
        } catch (e: Exception) {
            println(e)
            if (elements != 0) elements.minus(1)
            else
                println("Please fill all the spaces given")
                break // If elements != 0, reduce the countdown,
            // If not just break the catch
        }
    }

    println(
        "${aquariumValues["name"]}, " +
                "${aquariumValues["length"]}, " +
                "${aquariumValues["waterFilled"]}, " +
                "${aquariumValues["width"]}, " +
                "${aquariumValues["formType"]}, " +
                "${aquariumValues["materialType"]}"+
                "${aquariumValues["true or false answer if it contains decorations"]}"
    )
    return FishTank(
        aquariumValues["name"].toString(),
        aquariumValues["width"].toString().toFloat(),
        aquariumValues["length"].toString().toFloat(),
        aquariumValues["depth"].toString().toFloat(),
        materialType = aquariumValues["materialType"].toString(),
        formType = aquariumValues["formType"].toString(),
        hasDecorations = aquariumValues["true or false answer if it contains decorations"].toString().toBoolean()
    )
}
fun insertingAquarium(aquariumList: MutableList<FishTank?>?): MutableList<FishTank?>? {
    val myAquarium: MutableList<FishTank?>? = aquariumList
    //2 options: rewrite or update, chosen: update. Correction: 1 option: rewrite
    // If there are no aquariums then add a new one at index 0
    if (!myAquarium!!.any()) {
        val firstAquarium: FishTank? = createAquarium()
        println(
            "${firstAquarium!!.name}, " +
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
                myAquarium.add(elements + 1, createAquarium())
            } else {
                println("The list is empty, please contact support to solve this problem.")
            }
        }
    }
    return myAquarium
}
fun insertingFish(myFish: MutableList<Fish?>?, myAquarium: MutableList<FishTank?>?): MutableList<Fish?>? {
    if (myFish!!.isEmpty()) {
        myFish.add(0, fitMoreFish(
            run {
                println("Please specify in which Aquarium this fish is going to be?")
                val id: Int? = showElements(myAquarium,null,true)
                myAquarium!![id!!] }
        ))
    } else {
        for (elements in 0 until myFish.size - 1) {
            if (myFish.size.compareTo(elements) == 0) {
                myFish.add(elements + 1, fitMoreFish(myAquarium!!.last()))
            }
        }
    }
}
fun showElements(aquariumList: MutableList<FishTank?>?, fishList: MutableList<Fish?>?, indicator: Boolean?): Int? {
    if (aquariumList != null) {
        println("\nProviding all the recorded Aquariums:")
        println("\nID       |       Aquarium Name\n")
        for (elements in aquariumList.indices) {
            println("$elements        |       ${aquariumList[elements]!!.name}\n")
        }
        if (indicator == true) println("Insert the ID of the Aquarium that you want to expand")
        else println("Define the Aquarium where your fish is going to be put")
        var id:String? = null
        do {
            id = readLine()
        } while(id!!.toString().toInt() < 0)

        for (elements in aquariumList.indices) try {
            if (id.run { toString().toInt() } == elements) {
                aquariumList[elements]!!.details() //prints the whole details of this Aquarium

                println("\n\nWould you like to edit something of this Aquarium?")
                if (readLine().toString().findAnyOf(arrayListOf("y","yes","ye","yup","yea","yeah"),0,true) != null) {
                    return null
                    showElements(aquariumList)
                    TODO("Put the edit function")
                }else if (indicator == true) return id.toString().toInt()
            }
        } catch (e: IndexOutOfBoundsException) {
            println("${e}\nPlease insert an Integer number in order to find the Aquarium you're looking for.\n")
        }

    } else if (fishList != null) {
        println("\nProviding all the recorded Fish:")
        println("\nID       |       Aquarium Name\n")
        for (elements in fishList.indices) {
            println("${elements}        |       ${fishList[elements]!!.name}\n")
        }
        println("Insert the ID of the Fish that you want to expand")
        var id:String? = null
        do {
            id = readLine()
        } while(id!!.toString().toInt() < 0)

        for (elements in fishList.indices) try {
            if (id.run { toString().toInt() } == elements) {
                fishList[elements]!!.details() //prints the whole details of this Aquarium

                println("\n\nWould you like to edit something of this Aquarium?")
                if (readLine().toString().findAnyOf(arrayListOf("y","yes","ye","yup","yeah"),0,true) != null) {
                    return null
                    fishEdit(aquariumList,fishList)
                    TODO("Put the edit function")
                }else if (indicator == true) return id.toString().toInt()
            }
        } catch (e: IndexOutOfBoundsException) {
            println("${e}\nPlease insert an Integer number in order to find the Aquarium you're looking for.\n")
        }
    }
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
            "girth" to Float, "measure unit" to String, "current aquarium" to String
        )

        val fishColors = mutableMapOf<String, MutableList<String>>("colors" to mutableListOf())
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