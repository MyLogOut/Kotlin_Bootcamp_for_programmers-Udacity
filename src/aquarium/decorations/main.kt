package aquarium.decorations

fun main() {
    makeDecorations()
}

fun makeDecorations() {
    val d1 = Decoration("deco1")
    val d2 = Decoration("deco2")
    val d3 = Decoration("deco2")

    println(d1 == d2)
    println(d2 == d3)

    val d4: Decoration = d3.copy()
    println(d3)
    println(d4)

    val d5 = ExtraDecorations(mutableListOf(d1, d2, d3), true)
    val d6 = ExtraDecorations(mutableListOf(d2, d3), false)
    val d7 = ExtraDecorations(mutableListOf(d1, d2, d3), true)
    println(d5==d6)
    println(d5==d7)
    val (decorations: MutableList<Decoration>, coloured) = d5
    println(decorations)
    println(coloured)
}
