package org.mad.birdwatching.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.mad.birdwatching.helpers.exists
import org.mad.birdwatching.helpers.read
import org.mad.birdwatching.helpers.write

import org.mad.birdwatching.models.BirdModel
import org.mad.birdwatching.models.BirdStore

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "birds.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<BirdModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BirdJSONStore : BirdStore {

    var birds = mutableListOf<BirdModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BirdModel> {
        return birds
    }

    override fun findOne(id: Long) : BirdModel? {
        var foundBird: BirdModel? = birds.find { p -> p.id == id }
        return foundBird
    }

    override fun create(bird: BirdModel) {
        bird.id = generateRandomId()
        birds.add(bird)
        serialize()
    }

    override fun update(bird: BirdModel) {
        var foundBird = findOne(bird.id!!)
        if (foundBird != null) {
            foundBird.title = bird.title
            foundBird.description = bird.description
        }
        serialize()
    }

    override fun delete(bird: BirdModel) {
        birds.remove(bird)
        serialize()
    }

    internal fun logAll() {
        birds.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(birds, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        birds = Gson().fromJson(jsonString, listType)
    }
}