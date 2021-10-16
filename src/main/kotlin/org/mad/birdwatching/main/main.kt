package org.mad.birdwatching.main

import mu.KotlinLogging
import org.mad.birdwatching.models.BirdModel


private val logger = KotlinLogging.logger {}

val birds = ArrayList<BirdModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Bird Console App" }
    println("Bird Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addBird()
            2 -> updateBird()
            3 -> listBirds()
            4 -> searchBird()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Bird Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Bird")
    println(" 2. Update Bird")
    println(" 3. List All Bird")
    println(" 4. Search Bird")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addBird(){
    var aBird = BirdModel()
    println("Add Bird")
    println()
    print("Enter a Title : ")
    aBird.title = readLine()!!
    print("Enter a Description : ")
    aBird.description = readLine()!!

    if (aBird.title.isNotEmpty() && aBird.description.isNotEmpty()) {
        aBird.id = birds.size.toLong()
        birds.add(aBird.copy())
        logger.info("Bird Added : [ $aBird ]")
    }
    else
        logger.info("Bird Not Added")
}

fun updateBird() {
    println("Update Bird")
    println()
    listBirds()
    var searchId = getId()
    val aBird = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

//Area I edited because addBird().title was throwing an error > changed to aBird.title
    if(addBird() != null) {
        if (aBird != null) {
            print("Enter a new Title for [ " + aBird.title + " ] : ")
        }
        tempTitle = readLine()!!
        if (aBird != null) {
            print("Enter a new Description for [ " + aBird.description + " ] : ")
        }
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            if (aBird != null) {
                aBird.title = tempTitle
            }
            if (aBird != null) {
                aBird.description = tempDescription
            }
            if (aBird != null) {
                println(
                    "You updated [ " + aBird.title + " ] for title " +
                            "and [ " + aBird.description + " ] for description")
            }
            logger.info("Bird Updated : [ $aBird ]")
        }
        else
            logger.info("Bird Not Updated")
    }
    else
        println("Bird Not Updated...")
}

fun listBirds() {
    println("List All Birds")
    println()
    birds.forEach { logger.info("${it}") }
    println()
}

fun searchBird() {

    var searchId = getId()
    val aBird = search(searchId)

    if(aBird != null)
        println("Bird Details [ $aBird ]")
    else
        println("Bird Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : BirdModel? {
    var foundBird: BirdModel? = birds.find { p -> p.id == id }
    return foundBird
}

fun dummyData() {
    birds.add(BirdModel(1, "New York New York", "So Good They Named It Twice"))
    birds.add(BirdModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    birds.add(BirdModel(3, "Waterford City", "You get great Blaas Here!!"))
}