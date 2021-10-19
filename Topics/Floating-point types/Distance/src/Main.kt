fun main() {
        val distance = readLine()?.toInt()
        val hours = readLine()?.toInt()
        val speed = hours?.let { distance?.div(it) }
        if (speed != null) {
                print(speed.toFloat())
        }
}
