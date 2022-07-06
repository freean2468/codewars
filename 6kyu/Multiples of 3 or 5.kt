// https://www.codewars.com/kata/514b92a657cdc65150000006/solutions/kotlin

fun solution(number: Int): Int {
    var sum:Int = 0;
    
    for(x in 1..(number-1)) {
        sum += if (x % 3 === 0 || x % 5 === 0) x else 0;
    }
    
  return sum
}

// 함수형
// fun solution(number: Int) = (1 until number).filter { it % 3 == 0 || it % 5 == 0 }.sum()
