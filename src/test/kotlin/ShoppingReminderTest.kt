import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import objects.ShoppingReminder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate

class ShoppingReminderTest {


    @Test // На самом деле работает и без DSL (который shoppingReminder). Новая версия?
    fun `Mock in DSL style`() {

        val sr = mockk<ShoppingReminder> {
            every { remind("SupermarketName") }.returns("ShoppingReminder.remind() is stubbed")
            every { toBuy }.returns(listOf("I", "am", "stubbed"))
            every { shoppingDate }.returns(LocalDate.of(2021, 3, 29))
        }

        assertAll("Everything should be stubbed",
            { assertEquals("ShoppingReminder.remind() is stubbed", sr.remind("SupermarketName")) },
            { assertEquals(listOf("I", "am", "stubbed"), sr.toBuy) },
            { assertEquals(LocalDate.of(2021, 3, 29), sr.shoppingDate) }

        )
        verify { sr.remind("SupermarketName") }
    }
}