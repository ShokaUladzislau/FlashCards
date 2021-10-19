package flashcards

fun main() {
    val card = Card("JetBrains", readLine().toString())
    println(if(readLine().toString() == card.answer) "Your answer is right!" else "Your answer is wrong...")

}

class Card(name: String, answer: String){
    val name = name
    val answer = answer
}

//
//class Spot {
//    var isEmpty: Boolean = true
//    var plate: String? = null
//    var color: String? = null
//    var number: Int = 0
//
//    constructor(_isEmpty: Boolean, _plate: String, _color: String, _number: Int) {
//        isEmpty = _isEmpty
//        plate = _plate
//        color = _color
//        number = _number
//    }
//
//    constructor(_isEmpty: Boolean, _number: Int) {
//        isEmpty = _isEmpty
//        number = _number
//    }
//}