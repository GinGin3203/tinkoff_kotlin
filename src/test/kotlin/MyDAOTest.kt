import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import objects.MyDAO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MyDAOTest {

    val myDao = mockk<MyDAO>()

    @Test
    fun `mock getAll()`() {
        every { myDao.getAll() }.returns(listOf<Any>())

        val res = myDao.getAll()
        assertEquals(listOf<Any>(), res)
        verify { myDao.getAll() }
    }

    @Test
    fun `mock getById when Id less than 5 returns null`() {
        every { myDao.getById(less(5)) }.returns(null)

        for (i in 1..4)
            assertEquals(null, myDao.getById(i))
        verify(exactly = 4) { myDao.getById(less(5)) }


    }


    @Test
    fun `mock getById when Id larger or equal than 5 returns some value`() {
        every { myDao.getById(more(5, andEquals = true)) }.returns("SomeValue")

        for (i in 5..9)
            assertEquals("SomeValue", myDao.getById(i))
        verify(exactly = 5) { myDao.getById(more(5, andEquals = true)) }


    }


}