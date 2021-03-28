import java.time.LocalDate

fun main() {
    // Testing example class
    val br = BirthdayReminderer(LocalDate.of(2000, 3, 1))
    println(br.daysUntilBirthday(LocalDate.of(2021, 3, 28)))
    println(br.isBirthdayToday(LocalDate.of(2022, 3, 1)))
}