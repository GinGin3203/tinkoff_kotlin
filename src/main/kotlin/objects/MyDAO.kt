package objects

interface MyDAO {
    abstract fun getById(id: Int): Any?
    abstract fun getAll(): Any
}