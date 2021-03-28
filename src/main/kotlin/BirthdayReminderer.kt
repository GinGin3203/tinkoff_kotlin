import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

class BirthdayReminderer(val dateOfBirth: LocalDate) {
    private lateinit var closestBirthday: LocalDate

    private fun initializeClosestBirthday(date: LocalDate): LocalDate {
        closestBirthday = if (dateOfBirth.dayOfYear >= date.dayOfYear)
            dateOfBirth.plusYears(date.year.minus(dateOfBirth.year).toLong())
        else
            dateOfBirth.plusYears(date.year.minus(dateOfBirth.year).toLong() + 1)

        return closestBirthday
    }

    private fun getOrInitializeCB(date: LocalDate): LocalDate {
        val retval = if (this::closestBirthday.isInitialized)
            closestBirthday else initializeClosestBirthday(date)
        println(retval)
        return retval
    }

    fun isBirthdayToday(date: LocalDate) = getOrInitializeCB(date) == date

    fun daysUntilBirthday(date: LocalDate) = date.until(getOrInitializeCB(date), ChronoUnit.DAYS)
}