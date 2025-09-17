
class Date(
    val year: Int = 2025,
    val month: Int = 1,
    val day: Int = 1
) {
    //constructor(): this(2025,1,1)
    //val leapYear: Boolean get() = year.isLeapYear
}

private val dayOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)

val Date.lastDayOfMonth: Int
    get() = when(month) {
        2 -> if (leapYear) 29 else 28
        4,6,9,11 -> 30
        else -> 31
    }

val Date.leapYear: Boolean get() = year.isLeapYear

val Int.isLeapYear get() = this%4==0 && this%100!=0 || this%400==0