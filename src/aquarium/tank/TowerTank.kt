package aquarium.tank

import aquarium.tank.Tank
import kotlin.math.PI

class TowerTank(name: String) : Tank(name) {
    override var volume: Float = ((widthInches*heightInches*lengthInches) / 1000 * PI).toFloat()
    override val waterGallons: Float = cubicInchToGallon(if (hasDecorations) (volume) * 0.80F else (volume) * 0.90F)
}