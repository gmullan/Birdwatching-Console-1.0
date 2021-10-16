package org.mad.birdwatching.models

interface BirdStore {
    fun findAll(): List<BirdModel>
    fun findOne(id: Long): BirdModel?
    fun create(bird: BirdModel)
    fun update(bird: BirdModel)
    fun delete(bird: BirdModel)
}