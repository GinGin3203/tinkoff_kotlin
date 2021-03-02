package main

import kotlin.random.Random

class MediaGallery(
        override val name: String,
        private val supportedFileFormat: String,
        override val features: HashSet<String>
) : Application() {
    override val applicationType: String = "media gallery"
    override val purpose: String = "viewing media"
    private val seed: Int = 198274

    override fun about() = println(getDescription() +
            " I only support files in $supportedFileFormat format")

    fun shufflePeopleOnPhoto(people: List<String>): List<String> = people.shuffled(Random(seed))
}
