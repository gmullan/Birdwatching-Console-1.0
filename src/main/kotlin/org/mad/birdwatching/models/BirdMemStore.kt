package org.mad.birdwatching.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BirdMemStore : BirdStore {

    val birds = ArrayList<BirdModel>()

    override fun findAll(): List<BirdModel> {
        return birds
    }

    override fun findOne(id: Long) : BirdModel? {
        var foundBird: BirdModel? = birds.find { p -> p.id == id }
        return foundBird
    }

    override fun create(bird: BirdModel) {
        bird.id = getId()
        birds.add(bird)
        logAll()
    }

    override fun update(bird: BirdModel) {
        var foundBird = findOne(bird.id!!)
        if (foundBird != null) {
            foundBird.title = bird.title
            foundBird.description = bird.description
        }
    }

    internal fun logAll() {
        birds.forEach { logger.info("${it}") }
    }
}