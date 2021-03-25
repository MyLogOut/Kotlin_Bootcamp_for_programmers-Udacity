package aquarium.fish

import spices.resources.colors.Color

interface FishColor {
    val color: spices.resources.colors.Color
    get() = Color.GOLDEN
}