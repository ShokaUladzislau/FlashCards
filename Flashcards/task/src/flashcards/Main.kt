package flashcards

import java.io.File
import kotlin.random.Random

val cards = mutableMapOf<String, String>()
val mistakes = mutableMapOf<String, Int>()
val logMessages = mutableListOf<String>()

fun main(args: Array<String>) {
    for (i in args.indices) {
        if (args[i] == "-import") {
            import(args[i + 1])
        }
    }
    showMenu(args)
}

fun add() {
    printToLog("The card:")
    val term = readLine()!!.toLog()
    if (cards.containsKey(term)) {
        throw Exception("The card \"$term\" already exists.")
    }
    printToLog("The definition of the card:")
    val def = readLine()!!.toLog()
    if (cards.containsValue(def)) {
        throw Exception("The definition \"$def\" already exists.")
    }
    cards[term] = def
    printToLog("The pair (\"$term\":\"$def\") has been added.")
}

fun remove() {
    printToLog("Which card?")
    val key = readLine()!!.toLog()
    if (cards.containsKey(key)) {
        cards.remove(key)
        mistakes.remove(key)
        printToLog("The card has been removed.")
    } else {
        throw Exception("Can't remove \"$key\": there is no such card.")
    }
}

fun ask() {
    printToLog("How many times to ask?")
    val num = readLine()!!.toLog().toInt()
    for (k in 1..num) {
        val i = Random.nextInt(cards.size)
        val key = cards.keys.toList()[i]
        val def = cards[key]
        printToLog("Print the definition of \"$key\":")
        val ans = readLine()!!.toLog()
        if (ans == def) {
            printToLog("Correct!")
        } else {
            mistakes[key] = mistakes[key]?.plus(1) ?: 1
            val s = "Wrong. The right answer is \"$def\""
            if (cards.containsValue(ans)) {
                val randomCard = cards.filterValues { it == ans }.keys.first()
                printToLog("$s,  but your definition is correct for \"$randomCard\".")
            } else printToLog("$s.")
        }
    }

}

fun import(fileName: String) {
    var fn = fileName
    if (fn.isEmpty()) {
        printToLog("File name:")
        fn = readLine()!!.toLog()
    }
    if (File(fn).exists()) {
        val lines = File(fn).readLines()
        for (line in lines) {
            val (key, value, err) = line.split(" -> ")
            cards[key] = value
            mistakes[key] = err.toInt()
        }
        printToLog("${lines.size} cards have been loaded.")
    }
    if (!File(fn).exists()) {
        println("File not found.")
    }
}

fun export(filename: String) {
    var fn = filename
    if (fn.isEmpty()) {
        printToLog("File name:")
        fn = readLine()!!.toLog()
    }
    File(fn).writeText("")
    for (c in cards) {
        File(fn).appendText("${c.key} -> ${c.value} -> ${mistakes[c.key] ?: 0}\n")
    }
    printToLog("${cards.size} cards have been saved.")
}

fun hard() {
    val max = mistakes.map { it.value }.maxOrNull()
    if (mistakes.isEmpty() || max == 0) {
        printToLog("There are no cards with errors.")
        return
    }
    val hk = mistakes.filter { it.value == max }
    if (hk.size == 1) {
        printToLog("The hardest card is \"${hk.keys.first()}\". You have $max errors answering it.")
    } else {
        val list = hk.keys.joinToString { "\"${it}\"" }
        printToLog("The hardest cards are $list. You have $max errors answering them.")
    }
}

fun reset() {
    mistakes.clear()
    printToLog("Card statistics have been reset.")
}

fun showMenu(args: Array<String>) {
    while (true) {
        try {
            printToLog("")
            printToLog("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
            when (readLine()!!.toLog()) {
                "add" -> add()
                "remove" -> remove()
                "import" -> import("")
                "export" -> export("")
                "ask" -> ask()
                "log" -> log()
                "hardest card" -> hard()
                "reset stats" -> reset()
                "exit" -> {
                    for (i in args.indices) {
                        if (args[i] == "-export") {
                            export(args[i + 1])
                        }
                    }
                    break
                }

            }
        } catch (e: Exception) {
            printToLog(e.message!!)
        }
    }
    printToLog("Bye bye!")
}

fun log() {
    printToLog("File name:")
    val fn = readLine()!!.toLog()
    File(fn).writeText("")
    for (log in logMessages) {
        File(fn).appendText(log)
    }
    printToLog("The log has been saved.")
}

fun String.toLog(): String {
    logMessages.add(this + "\n")
    return this
}

fun printToLog(str: String) {
    (str).toLog()
    println(str)
}