// https://www.codewars.com/kata/54b724efac3d5402db00065e/kotlin
package morsecode

fun decodeMorse(code: String): String {
    val mutableMorseCode = MorseCode.toMutableMap()
    mutableMorseCode.put("_", " ")
    val manipulated = code.trim().replace("   ", " _ ").split(" ")
    var decoded = ""
    manipulated.forEach { decoded += mutableMorseCode[it] }
    return decoded
}

// 함수형답게 파이프 한 번으로 끝낼 수 있다.
// fun decodeMorse(code: String): String {
//   return code.split(" ")
//              .map({ x -> MorseCode[x] ?: " " })
//              .joinToString("")
//              .replace("  "," ")
//              .trim()
// }
