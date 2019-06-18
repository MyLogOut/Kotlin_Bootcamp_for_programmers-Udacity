package Aquarium

class FishTank(
    var name: String? = "No specified",
    var width: Float? = 0.0F,
    var length: Float? = 0.0F,
    var depth: Float? = 0.0F,
    var materialType: String? = "Glass",
    var formType: String? = "Rectangular",
    var hasDecorations: Boolean? = true,
    val measureUnit: String? = "cm"

) {
    // The current amount of fish inside the aquarium
    private var fishInside = arrayListOf<Fish>()
    // The volume of the tank
    var cubicFishCapacity: Float? = calculateFishCapacity()
    /*Without Fish volume isn't possible to estimate the fishVolume - waterInTank
    * Rule: 1 inch per fish per gallon of water*/
    var waterFilled: Float? = cubicFishCapacity!!.times(0.8000F)
    // Empty (0.0F) Float variable to storage the current fish space used
    var fishSpace = mutableMapOf<String, Float?>().withDefault { 0.0F }

    init {
        fishSpace.put("width", 0.0F)
        fishSpace.put("length", 0.0F)

    }


    fun getFishTankMaterialType(): String? {
        return this.materialType
    }

    fun getFishTankFormType(): String? {
        return this.formType
    }

    fun getFishTankWidth(): Float? {
        println(message = this.measureUnit + ": ")
        return this.width
    }

    fun getFishTankHLength(): Float? {
        return this.length
    }

    fun getFishTankDepth(): Float? {
        return this.depth
    }

    fun getFishTankWaterResistance(): Float? {
        return this.waterFilled
    }

    fun getFishTankCubicFishCapacity(): Float? {
        return this.cubicFishCapacity
    }

    fun getFishTankMeasureUnit(): String? {
        return this.measureUnit
    }

    /*fun getFishTankFishInside(): ArrayList<Fish> {
        return fishInside
    }*/

    fun addThisFish(fish: Fish?) {
        if (fish!!.currentAquarium!!.compareTo("No specified") != 0) {
            if (fishSpace["width"]!! <= (this.width!!.plus(this.length!!)) && (
                        fish!!.width!!.compareTo(this.width!!.times(0.8)) <= 0 &&
                                fish.length!!.compareTo(this.length!!.times(0.8)) <= 0)
            ) {
                this.fishInside.add(fish)
                this.fishSpace["width"]!!.plus(fish.width!!)
                this.fishSpace["length"]!!.plus(fish.length!!)
                println("Fish added successfully")
            } else println("This Aquarium is full")
        } else return println("No Aquarium specified")
    }

/*    fun putFish(fish: Fish) {
        if (cubicFishCapacity!!.compareTo(fish.volume!!) == 1 ||
            cubicFishCapacity!!.compareTo(fish.volume!!) == -1
        ) {
            cubicFishCapacity!!.minus(fish.volume!!)
            this.fishInside.add(fish)
            this.fishQuantity!!.plus(1)
        } else println("There's no more space, just $cubicFishCapacity left inside this Aquarium")
    }*/

    private fun calculateFishCapacity(): Float? {
        if (this.width!!.compareTo(0.0F) != 0.or(-1) ||
            this.length!!.compareTo(0.0F) != 0.or(-1) ||
            this.depth!!.compareTo(0.0F) != 0.or(-1)
        ) {
            return (width!!.times(length!!).times(depth!!))
            TODO("Calcular la media del tamaño de los peces que hayan actualmente")
        } else return 0.0F

    }
}
