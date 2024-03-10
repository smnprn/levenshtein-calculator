package model

class LevenshteinCalculator(firstWord: String, secondWord: String) {
    private var matrix = createMatrix(firstWord.length, secondWord.length)

    init {
        initMatrix(matrix)
        setMatrix(firstWord, secondWord, matrix)
    }

    fun getLevenshteinDistance(): Int {
        return matrix.last().last()
    }

    fun printMatrix() {
        for (element in matrix) {
            println(element)
        }
    }

    private fun createMatrix(firstWordLength: Int, secondWordLength: Int):  MutableList<MutableList<Int>> {
        val matrix: MutableList<MutableList<Int>> = mutableListOf()

        var i = 0
        while (i < secondWordLength + 1) {
            matrix.add(
                MutableList(size = firstWordLength + 1) { -1 }
            )
            i++
        }

        return matrix
    }

    private fun initMatrix(matrix: MutableList<MutableList<Int>>) {
        val firstRow = matrix[0]

        var i = 0
        while (i < firstRow.size) {
            firstRow[i] = i
            i++
        }

        i = 0

        for (element in matrix) {
            element[0] = i
            i++
        }
    }

    private fun findMinValueAround(firstWord: String, secondWord: String, xCoordinate: Int, yCoordinate: Int, matrix: MutableList<MutableList<Int>>): Int {
        var cellX = xCoordinate - 1
        var cellY = yCoordinate - 1

        var minVal = matrix[cellX][cellY]

        while (cellX < xCoordinate + 1 && cellX <= secondWord.length) {
            while (cellY < yCoordinate + 1 && cellY <= firstWord.length) {
                if (matrix[cellX][cellY] != -1 && matrix[cellX][cellY] <= minVal) {
                    minVal = matrix[cellX][cellY]
                }

                cellY++
            }

            cellY = yCoordinate - 1
            cellX++
        }

        return minVal + 1
    }

    private fun setMatrix(firstWord: String, secondWord: String, matrix: MutableList<MutableList<Int>>) {
        var i = 1
        var j = 1

        while (i <= secondWord.length) {
            while (j <= firstWord.length) {
                var valueToSet = findMinValueAround(firstWord, secondWord, i, j, matrix)

                if (firstWord[j-1] == secondWord[i-1]) {
                    matrix[i][j] = matrix[i-1][j-1]
                } else {
                    matrix[i][j] = valueToSet
                }

                j++
            }

            j = 1
            i++
        }
    }
}