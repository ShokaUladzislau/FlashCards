import java.io.DataInputStream
import java.io.IOException


fun main(args: Array<String>) {
    var i: Double
    var x = 0.0
    var n = 0.0
    var sum = 0.0
    val obj = DataInputStream(System.`in`)
    try {
        x = readLine()!!.toDouble()
        n = 3.0
    } catch (e: IOException) {
    }
    i = 0.0
    while (i <= n) {
        sum = sum + Math.pow(x, i)
        i++
    }
    println(sum)
}
