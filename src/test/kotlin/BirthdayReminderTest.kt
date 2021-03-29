import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.spyk
import objects.BirthdayReminder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate


@ExtendWith(MockKExtension::class)
class BirthdayReminderTest {


    @ParameterizedTest
    @CsvSource(
        "2025,3,1,true", // month and day as birthday
        "2048,9,2,false" // just some day
    )

    fun `isBirthdayToday returns true if given a date with the same month and day as dateOfBirth and false otherwise`(
        year: Int,
        month: Int,
        day: Int,
        expected: Boolean
    ) {

        val br = spyk(BirthdayReminder())
        br.dateOfBirth = LocalDate.of(2000, 3, 1)
        val res = br.isBirthdayToday(year, month, day)
        assertEquals(expected, res)

    }

    @ParameterizedTest
    @CsvSource(
        "2020,3,1,0", // same day as birthday
        "2017,5,7,298", // just some day
        "2000,2,29,1", // leap year
        "1999,2,25,4" // date until birthday
    )
    fun `daysUntilBirthday returns correct distance in days until closest birthday`(
        year: Int,
        month: Int,
        day: Int,
        expected: Long
    ) {

        val br = spyk(BirthdayReminder())
        br.dateOfBirth = LocalDate.of(2000, 3, 1)
        every { br["calculateClosestBirthday"](LocalDate.of(2020, 3, 1)) } returns (LocalDate.of(2020, 3, 1))
        every { br["calculateClosestBirthday"](LocalDate.of(2017, 5, 7)) } returns (LocalDate.of(2018, 3, 1))
        every { br["calculateClosestBirthday"](LocalDate.of(2000, 2, 29)) } returns (LocalDate.of(2000, 3, 1))
        every { br["calculateClosestBirthday"](LocalDate.of(1999, 2, 25)) } returns (LocalDate.of(1999, 3, 1))

        val res = br.daysUntilBirthday(year, month, day)
        assertEquals(expected, res)
    }
}