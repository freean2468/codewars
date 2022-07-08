// https://www.codewars.com/kata/5506b230a11c0aeab3000c1f/kotlin
import kotlin.math.log
import kotlin.math.ceil

fun evaporator(content: Double, evap_per_day: Double, threshold: Double): Int {
  return ceil(log(threshold/100.0, content) / log(1.0-evap_per_day/100.0, content)).toInt()
}