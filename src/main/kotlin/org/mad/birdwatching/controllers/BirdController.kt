package org.wit.birdwatching.controllers

import mu.KotlinLogging
import org.mad.birdwatching.models.BirdJSONStore
import org.mad.birdwatching.models.BirdModel
import org.wit.bird.console.views.BirdView


class BirdController {

 //   val birds = BirdMemStore()
    val birds = BirdJSONStore()
    val birdView = BirdView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Bird Console App" }
        println("Bird Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Bird Console App" }
    }


    fun menu() :Int { return birdView.menu() }

    fun add(){
        var aBird = BirdModel()

        if (birdView.addBirdData(aBird))
            birds.create(aBird)
        else
            logger.info("Bird Not Added")
    }

    fun list() {
        //birdView.listBirds(birds)
        birdView.listBird(birds)
    }

    fun update() {

        birdView.listBird(birds)
        var searchId = birdView.getId()
        val aBird = search(searchId)

        if(aBird != null) {
            if(birdView.updateBirdData(aBird)) {
                birds.update(aBird)
                //birdView.showBird(aBird)
                birdView.showBirds(aBird)
                logger.info("Bird Updated : [ $aBird ]")
            }
            else
                logger.info("Bird Not Updated")
        }
        else
            println("Bird Not Updated...")
    }

    fun delete(){
        birdView.listBird(birds)
        var searchId = birdView.getId()
        val aBird = search(searchId)

        if(aBird != null) {
            birds.delete(aBird)
            println("Bird Deleted...")
            birdView.listBird(birds)
        }
        else
            println("Bird Not Deleted...")
    }

    fun search() {
        val aBird = search(birdView.getId())!!
       // birdView.showBird(aBird)
        birdView.showBirds(aBird)
    }


    fun search(id: Long) : BirdModel? {
        var foundBird = birds.findOne(id)
        return foundBird
    }

    fun dummyData() {
        birds.create(BirdModel(title = "New York New York", description = "So Good They Named It Twice"))
        birds.create(BirdModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        birds.create(BirdModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}