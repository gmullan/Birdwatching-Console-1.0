package org.wit.bird.console.views

import org.mad.birdwatching.models.BirdJSONStore
import org.mad.birdwatching.models.BirdModel
//import org.mad.birdwatching.views.BirdView
//import org.wit.bird.main.birds


class BirdView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Bird")
        println(" 2. Update Bird")
        println(" 3. List All Birds")
        println(" 5. Delete Birds")
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

    fun listBird(birds: BirdJSONStore) {
        println("List All Birds")
        println()
        birds.logAll()
        println()
    }

    fun showBirds(bird : BirdModel) {
        if(bird != null)
            println("Bird Details [ $bird ]")
        else
            println("Bird Not Found...")
    }

    fun addBirdData(bird : BirdModel) : Boolean {

        println()
        print("Enter a Title : ")
        bird.title = readLine()!!
        print("Enter a Description : ")
        bird.description = readLine()!!

        return bird.title.isNotEmpty() && bird.description.isNotEmpty()
    }

    fun updateBirdData(bird : BirdModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (bird != null) {
            print("Enter a new Title for [ " + bird.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + bird.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                bird.title = tempTitle
                bird.description = tempDescription
                return true
            }
        }
        return false
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
    }}

