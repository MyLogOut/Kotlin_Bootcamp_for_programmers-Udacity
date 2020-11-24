package aquarium

class Prism (
    val polygonType: Pair<String,Int>,
    val sides: Map<Int,Float>,
    var width: Float,
    var length: Float,
    var depth: Float,
    var area: Float?,
    var perimeter: Float?,
    var isRegular: Boolean
){
    init {

    }
}