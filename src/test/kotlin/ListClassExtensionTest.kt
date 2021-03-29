import io.mockk.mockkStatic
import io.mockk.verify
import main.concatenateContents
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ListClassExtensionTest {

    @Test
    fun `concatenateContents should return string of concatenated contents`() {
        mockkStatic("main.ListClassExtensionKt")

        assertEquals("123", listOf(1, 2, 3).concatenateContents())
        verify { listOf(1, 2, 3).concatenateContents() }
    }

    @Test
    fun `concatenateContents should throws IllegalArgumentException if called on an empty list`() {
        mockkStatic("main.ListClassExtensionKt")

        try {
            emptyList<Int>().concatenateContents()
        } catch (e: IllegalArgumentException) {
            assertEquals("Empty list is not accepted", e.message)
        }

        verify { emptyList<Int>().concatenateContents() }


    }

}