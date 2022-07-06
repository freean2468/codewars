// https://www.codewars.com/kata/51b6249c4612257ac0000005/kotlin
package romannumerals

fun decode(str: String): Int {
    var prod:String = str
    val roman_table:HashMap<Char, Int> = hashMapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    var sum:Int = 0
    
    while (prod.length > 0) {
        val left:Int = when (prod.get(0)) {
            is Char -> roman_table.getOrDefault(prod.get(0), 0)
            !is Char -> 0
            else -> throw AssertionError()
        }
        
        if (prod.length === 1) {
            sum += left
            break;
        }
        
        val right:Int = when (prod.get(1)) {
            is Char -> roman_table.getOrDefault(prod.get(1), 0)
            !is Char -> 0
            else -> throw AssertionError()
        }
        
        sum += if (left >= right) left else -left
        prod = prod.substring(1)
    }
    
    return sum
}

// Functional approach
// package romannumerals

// val numbersMap = mapOf('M' to 1000, 'D' to 500, 'C' to 100, 'L' to 50, 'X' to 10, 'V' to 5, 'I' to 1)

// fun decode(str: String): Int {

//     var rawNumbers = str.map { char -> numbersMap.getOrElse(char, { null }) }.filterNotNull()
//     var normalizedNumbers = rawNumbers.mapIndexed { i, num -> if (num < rawNumbers.elementAtOrElse(i + 1) { Int.MIN_VALUE }) -num else num }
//     return normalizedNumbers.toIntArray().sum()
// }