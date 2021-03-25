package aquarium

import kotlin.math.pow

class Pescao(
    var name: String? = "No specified",
    var colours: MutableList<String?> = mutableListOf("Red","Black","White"),
    var width: Float? = 0.50F,
    var length: Float? = 5.20F,
    var girth: Float? = width!!.times(6),
    var measureUnit: String? = "inches",
    var type: String? = "Trout",
    var currentAquarium: String? = "No specified"
) {
    val properties = listOf(
        ::name.name,
        ::colours.name,
        ::width.name,
        ::length.name,
        ::girth.name,
        ::measureUnit.name,
        ::type.name,
        ::currentAquarium.name
    )
    var weight: Float? = calculateFishWeight()
    var volume: Float? = calculateFishVolume()
    private fun calculateFishWeight() = (this.girth!!.pow(2).times(length!!)).div(800)

    private fun calculateFishVolume(): Float? {
        val earthGravity: Float = 9.807F
        /*Get the mass of an object, in this case the Aquarium.Fish:
         w = m * g == weight = mass.times(gravity) => m = w/g mass = weight.div(gravity)*/
        val fishMass: Float = this.weight!!.div(earthGravity)

        //Non sense/Not a real way to get fish volume: (width*length*weight)/girth
        val fishVolume: Float? = (this.width!!.times(this.length!!).times(this.weight!!)).div(this.girth!!)

        return (fishVolume)
    }
    fun details() {
        println(
            "Showing the details for the Fishtank: ${this.name}.\n" +
                    "Width: ${this.colours} .\n" +
                    "Length: ${this.width} .\n" +
                    "Depth: ${this.length} .\n" +
                    "Measure unit: ${this.girth} .\n" +
                    "Material type: ${this.measureUnit} .\n" +
                    "Form type: ${this.type} .\n" +
                    "Water filled: ${this.currentAquarium} .\n" +
                    "Cubic fish capacity: ${this.weight} .\n" +
                    "Fish space left: ${this.volume} .\n"
        )
    }

}