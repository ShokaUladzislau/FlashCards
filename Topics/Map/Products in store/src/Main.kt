fun bill(priceList: Map<String, Int>, shoppingList: MutableList<String>): Int {
    var result = 0
    for (i in priceList) {
        for (j in shoppingList){
            if (i.key == j) {
                result += i.value
            }
        }
    }
    return result
}

