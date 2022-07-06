// https://www.codewars.com/kata/5541f58a944b85ce6d00006a/java

package prodfib

fun productFib(prod: Long): LongArray {
    var left: Long = 0
    var right: Long = 1

    while (left * right < prod) {
        var temp: Long = right
        right = left + right
        left = temp
    }

    return longArrayOf(left, right, if (prod == left * right) 1L else 0L)
    // your code
}
