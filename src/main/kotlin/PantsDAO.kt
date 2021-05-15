package main.kotlin

class PantsDAO {
    private val storage = listOf(
        Pants(198, "cotton", "red"),
        Pants(3305, "textile", "black"),
        Pants(1350, "denim", "blue"),
        Pants(294, "synthetic", "black"),
        Pants(1023, "synthetic", "green"),
        Pants(1334, "cotton", "white"),
        Pants(10293, "textile", "black"),
    )

    fun getAllData(): List<Pants> = storage

    fun getByModelId(modelId: Int): Pants? = storage.firstOrNull { it.modelId == modelId }
}
