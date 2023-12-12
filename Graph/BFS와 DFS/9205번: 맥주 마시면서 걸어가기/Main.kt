import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.abs

const val MAXIMUM_BEER_COUNT = 20
const val DISTANCE_PER_BEER = 50
const val DISTANCE_LIMIT = MAXIMUM_BEER_COUNT * DISTANCE_PER_BEER

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    val output = StringBuilder()

    while (n-- > 0) {
        val cntOfMart = readLine().toInt()
        val homeInput = readLine().split(" ").map { it.toInt() }.toIntArray()

        val positionOfHome = homeInput[0] to homeInput[1]
        val positionsOfMarts = LinkedList<Pair<Int, Int>>()
        repeat(cntOfMart) {
            val martInput = readLine().split(" ").map { it.toInt() }.toIntArray()
            val positionOfMart = martInput[0] to martInput[1]
            positionsOfMarts.add(positionOfMart)
        }
        val destinationInput = readLine().split(" ").map { it.toInt() }.toIntArray()
        val positionOfDestination = destinationInput[0] to destinationInput[1]

        if (abs(positionOfHome.first - positionOfDestination.first) + abs(positionOfHome.second - positionOfDestination.second) <= DISTANCE_LIMIT) {
            output.append("happy\n")
            continue
        }

        val result = solution(positionOfHome, positionsOfMarts, positionOfDestination)
        output.append(result).append("\n")
    }

    println(output)
}

fun solution(positionOfHome: Pair<Int, Int>, positionsOfMarts: List<Pair<Int, Int>>, positionOfDestination: Pair<Int, Int>): String {
    if (dfs(positionOfHome, positionsOfMarts, BooleanArray(positionsOfMarts.size), positionOfDestination)) {
        return "happy"
    }
    return "sad"
}

fun dfs(currentPosition: Pair<Int, Int>, positionsOfMarts: List<Pair<Int, Int>>, visited: BooleanArray, positionOfDestination: Pair<Int, Int>): Boolean {
    if (getDistanceOfTwoPositions(currentPosition, positionOfDestination) <= DISTANCE_LIMIT) {
        return true
    }
    for (i in positionsOfMarts.indices) {
        if (visited[i]) {
            continue
        }
        if (getDistanceOfTwoPositions(currentPosition, positionsOfMarts[i]) > DISTANCE_LIMIT) {
            continue
        }

        visited[i] = true
        if (dfs(positionsOfMarts[i], positionsOfMarts, visited, positionOfDestination)) {
            return true
        }
    }
    return false
}

fun getDistanceOfTwoPositions(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int {
    return abs(p1.first - p2.first) + abs(p1.second - p2.second)
}