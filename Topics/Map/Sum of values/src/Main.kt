fun summator(map: Map<Int, Int>): Int {
    var result = 0

    for (i in map) {
        if (i.key % 2 == 0) {
            result += i.value
        }
    }

    return result
}