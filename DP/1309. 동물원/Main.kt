import java.io.BufferedReader
import java.io.InputStreamReader

const val modedNum = 9901

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var caseEmpty = 1
    var caseLeftExist = 1
    var caseRightExist = 1

    var currentCaseEmpty = 1
    var currentCaseLeftEmpty = 1
    var currentCaseRightEmpty = 1

    for (i in 2..n) {

        currentCaseEmpty = caseEmpty.plus(caseLeftExist).plus(caseRightExist).mod(modedNum)
        currentCaseLeftEmpty = caseEmpty.plus(caseRightExist).mod(modedNum)
        currentCaseRightEmpty = caseEmpty.plus(caseLeftExist).mod(modedNum)


        caseEmpty = currentCaseEmpty
        caseLeftExist = currentCaseLeftEmpty
        caseRightExist = currentCaseRightEmpty
    }
    println(currentCaseEmpty.plus(currentCaseLeftEmpty).plus(currentCaseRightEmpty).mod(modedNum))
}