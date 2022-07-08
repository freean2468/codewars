// https://www.codewars.com/kata/554e4a2f232cdd87d9000038/kotlin
package dna

fun makeComplement(dna : String) : String {
    return (dna.map { 
      when (it) {
        'T' -> 'A'
        'A' -> 'T'
        'C' -> 'G'
        'G' -> 'C'
        else -> throw AssertionError()
      }
    }).toCharArray().concatToString()
}