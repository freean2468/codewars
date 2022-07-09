// https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1/kotlin
fun duplicateCount(text: String): Int {
    val text_copy = text.toLowerCase()
    val set:MutableSet<Char> = mutableSetOf()
    
    text_copy.forEachIndexed { index, element ->
        if(text_copy.indexOf(element) != index) {
            set.add(element)
        }
    }
    
    return set.size
}

// groupBy와 toLowerCase를 Lambda로 넘겨줘 간단하게 구현 가능하네.
// fun duplicateCount(text: String) = text.groupBy(Char::toLowerCase).count { it.value.count() > 1 }