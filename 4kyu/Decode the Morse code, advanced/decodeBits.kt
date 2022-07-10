// https://www.codewars.com/kata/54b72c16cd7f5154e9000457/kotlin
import java.util.*

fun decodeBits(bits: String): String {
    var bitsCopy = bits.replace("^0+|0+$".toRegex(), "")
    var unit = Integer.MAX_VALUE
    val trimRegex = Regex("1+|0+")

    trimRegex.findAll(bitsCopy).forEach {
        unit = if (it.groupValues[0].length < unit) it.groupValues[0].length else unit
    }

    return bitsCopy.replace(("1{" + unit + "}").toRegex(), "1")
            .replace(("0{" + unit + "}").toRegex(), "0")
            .replace("0{7}".toRegex(), "   ")
            .replace("1{3}".toRegex(), "-")
            .replace("0{3}".toRegex(), " ")
            .replace("1{1}".toRegex(), ".")
            .replace("0{1}".toRegex(), "")
}

fun decodeMorse(code: String): String {
    val mutableMorseCode = MORSE_CODE.toMutableMap()
    mutableMorseCode.put("_", " ")
    val manipulated = code.trim().replace("   ", " _ ").split(" ")
    var decoded = ""
    manipulated.forEach { decoded += mutableMorseCode[it] }
    return decoded
}

// fun decodeBits(bits: String): String {
//     val bis = bits.trim { it == '0' }
//     val timeUnit = Regex("0+|1+").findAll(bis).fold(bis.length) { smallest, x -> if
// (x.value.length < smallest) x.value.length else smallest }
//     return bis.replace("111".repeat(timeUnit), "-")
//               .replace("000".repeat(timeUnit), " ")
//               .replace("1".repeat(timeUnit), ".")
//               .replace("0".repeat(timeUnit), "")
// }



// fun decodeMorse(code: String) = code.split(" ").map { if (it == "") " " else MORSE_CODE[it]
// }.joinToString("")
