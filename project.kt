import java.util.Scanner

var finish = "Draw!"
var player = 1
val sc = Scanner(System.`in`)
val map = arrayOf(
    arrayOf('_', '_', '_'),
    arrayOf('_', '_', '_'),
    arrayOf('_', '_', '_')
)

fun mapMaker() {
    println("---------")
    println("| ${map[0].joinToString(" ")} |")
    println("| ${map[1].joinToString(" ")} |")
    println("| ${map[2].joinToString(" ")} |")
    println("---------")
}

fun turn () {
    while (true) {
        if (player % 2 != 0) {
            print("X - Enter the coordinates (column and row): ")
        } else {
            print("O - Enter the coordinates (column and row): ")
        }
        val y = sc.next()
        val z = sc.next()
        if (y !in "0".."9" || z !in "0".."9") {
            println("You should enter numbers!")
        } else if (y.toInt() !in 1..3 || z.toInt() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
        } else if (map[y.toInt() - 1][z.toInt() - 1] != '_') {
            println("This cell is occupied! Choose another one!")
        } else if (map[y.toInt() - 1][z.toInt() - 1] == '_') {
            if (player == 1) {
                map[y.toInt() - 1][z.toInt() - 1] = 'X'
                player = 2
            } else {
                map[y.toInt() - 1][z.toInt() - 1] = 'O'
                player = 1
            }
            mapMaker()
            break
        }
    }
}

fun checkMap () {
    for (i in map.indices) {
        for (j in map.indices) {
            if ('O' !in map[i] && '_' !in map[i]) {
                finish = "X wins!"
            }
            if ('X' !in map[i] && '_' !in map[i]) {
                finish = "O wins!"
            }
            if (map[0][0] == 'X' && map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                finish = "X wins!"
            }
            if (map[0][0] == 'O' && map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
                finish = "O wins!"
            }
            if (map[0][2] == 'X' && map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                finish = "X wins!"
            }
            if (map[0][2] == 'O' && map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
                finish = "O wins!"
            }
            if (i == 0) {
                if (map[i][j] == 'X' && map[i][j] == map[i + 1][j] && map[i + 1][j] == map[i + 2][j]) {
                    finish = "X wins!"
                }
                if (map[i][j] == 'O' && map[i][j] == map[i + 1][j] && map[i + 1][j] == map[i + 2][j]) {
                    finish = "O wins!"
                }
            }
        }
    }
}

fun filledMap (): Boolean {
    return ('_' !in map[0] && '_' !in map[1] && '_' !in map[2])
}

fun main() {
    while (true) {
        turn()
        checkMap()
        if (finish != "Draw!" || filledMap()) break
    }
    println(finish)
}
