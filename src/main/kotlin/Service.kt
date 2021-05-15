package main.kotlin

class Service(private val pantsDao: PantsDAO, private val brandDao: BrandDAO) {

    fun getPants() = pantsDao.getAllData()

    fun getBrands() = brandDao.getAllData()

    fun getBrandedPants(): List<BrandedPants> =
        getPants().mapNotNull { pants ->
            brandDao.getLatestModelId(pants.modelId)?.let { brand ->
                BrandedPants(pants.modelId, pants.material, pants.color, brand.name, brand.countryOfOrigin)
            }
        }

    fun sortPantsByModelId() = getPants().sortedBy { it.modelId }

    fun groupPantsByColor() = getPants().groupBy { it.material }

    fun getNumByCondition(pred: (T: Pants) -> Boolean) = getPants().count { pred(it) }
}