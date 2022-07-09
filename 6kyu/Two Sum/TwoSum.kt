// https://www.codewars.com/kata/52c31f8e6605bcc646000082/kotlin
package kata

object TwoSum {
	fun twoSum(numbers: IntArray, target: Int): Pair<Int,Int> {
		numbers.forEachIndexed { i, f ->
            numbers.forEachIndexed { j, s -> 
                if (f + s === target && i !== j) return Pair<Int, Int>(i, j)
            }
        }
        throw AssertionError()
	}
}