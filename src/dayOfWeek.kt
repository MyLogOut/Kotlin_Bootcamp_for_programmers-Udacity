import java.util.*
import kotlin.math.sign

fun dayOfWeek() {
    println("What day is today?")
    var weekDayNumber: Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
/*    var weekDayName: String? = null

    when (weekDayNumber) {
        1 -> weekDayName = "Lunes"
        2 -> weekDayName = "Martes"
        3 -> weekDayName = "Miercoles"
        4 -> weekDayName = "Jueves"
        5 -> weekDayName = "Viernes"
        6 -> weekDayName = "Sábado"
        7 -> weekDayName = "Domingo"
        else -> weekDayName = null
    }

    print(weekDayName + " ")
    println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))*/
    println(when (weekDayNumber) {
        1 -> "Lunes"
        2 -> "Martes"
        3 -> "Miercoles"
        4 -> "Jueves"
        5 -> "Viernes"
        6 -> "Sábado"
        7 -> "Domingo"
        else -> null
    })


}
