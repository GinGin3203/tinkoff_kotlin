package objects

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BirthdayReminder {

    var dateOfBirth: LocalDate = LocalDate.now()

    private fun calculateClosestBirthday(date: LocalDate): LocalDate =
        if (dateOfBirth.dayOfYear >= date.dayOfYear)
            dateOfBirth.plusYears(date.year.minus(dateOfBirth.year).toLong())
        else
            dateOfBirth.plusYears(date.year.minus(dateOfBirth.year).toLong() + 1)


    fun isBirthdayToday(year: Int, month: Int, day: Int): Boolean {
        val date = LocalDate.of(year, month, day)
        return dateOfBirth.month == date.month && dateOfBirth.dayOfMonth == date.dayOfMonth
    }

    fun daysUntilBirthday(year: Int, month: Int, day: Int): Long {
        val date = LocalDate.of(year, month, day)
        return date.until(calculateClosestBirthday(date), ChronoUnit.DAYS)
    }
}