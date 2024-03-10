package ui

import model.LevenshteinCalculator

fun main() {
    do {
        println("""
            
            Choose an option:
            1 - Calculate the Levenshtein distance
            2 - Print the Wagner-Fischer matrix
            3 - Exit
        """.trimIndent())

        val option = readlnOrNull()

        when (option) {
            "1" -> {
                val words = askWords()
                printLevenshtein(words[0], words[1])
            }

            "2" -> {
                val words = askWords()
                printMatrix(words[0], words[1])
            }

            "3" -> {
                println("Exit from the program")
                break
            }

            else -> {
                println("\nInvalid input")
                continue
            }
        }
    } while (true)
}

fun askWords(): List<String> {
    println("Write the first word:")
    val firstWord = readln()
    println("Write the second word:")
    val secondWord = readln()

    val words = listOf(
        firstWord,
        secondWord
    )

    return words
}

fun printLevenshtein(firstWord: String, secondWord: String) {
    if (firstWord == "" || secondWord == "") {
        println("It's impossible to calculate the distance with an empty word, please write a non-empty word")
    } else {
        val levenshteinCalculator = LevenshteinCalculator(firstWord.uppercase(), secondWord.uppercase())
        println("The Levenshtein distance for the words $firstWord and $secondWord is ${levenshteinCalculator.getLevenshteinDistance()}")
    }
}

fun printMatrix(firstWord: String, secondWord: String) {
    if (firstWord == "" || secondWord == "") {
        println("It's impossible to calculate the matrix with an empty word, please write a non-empty word")
    } else {
        val levenshteinCalculator = LevenshteinCalculator(firstWord.uppercase(), secondWord.uppercase())
        println("This is the Wagner-Fischer matrix for the words $firstWord and $secondWord")
        levenshteinCalculator.printMatrix()
    }
}