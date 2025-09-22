
const val GREGORIAN_YEAR = 1582
const val MAX_YEAR = 2200

class Date(
    val year: Int = 2025,
    val month: Int = 1,
    val day: Int = 1
): Any() {
    init {
        require(year in GREGORIAN_YEAR..MAX_YEAR) { "Invalid year $year" }
        require(month in 1..dayOfMonth.size) { "Invalid month $month" }
        require(day in 1..lastDayOfMonth) { "Invalid day $day" }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Date) return false
        return other.year==year && other.month==month && other.day==day
    }

    override fun hashCode(): Int = (year*16 + month)*32 + day

    override fun toString(): String =
        //this::class.simpleName + "@" + hashCode().toString(16)
        "$year-" + "%02d-%02d".format(month,day)

    //constructor(): this(2025,1,1)
    //val leapYear: Boolean get() = year.isLeapYear
}

private val dayOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)

val Date.lastDayOfMonth: Int
    get() = if (month==2 && leapYear) 29 else dayOfMonth[month-1]

val Date.leapYear: Boolean get() = year.isLeapYear

val Int.isLeapYear get() = this%4==0 && this%100!=0 || this%400==0

operator fun Date.plus(days: Int): Date = this.addDays(days)
operator fun Int.plus(date: Date): Date = date.addDays(this)

tailrec fun Date.addDays(d :Int): Date = when {
    day + d <= lastDayOfMonth ->
        Date(year, month, day + d)
    month < dayOfMonth.size ->
        Date(year, month + 1, 1).addDays(d - (lastDayOfMonth - day + 1))
    else ->
        Date(year + 1, 1, 1).addDays(d - (lastDayOfMonth - day + 1))
}


tailrec fun fact(n: Int, r: Int = 1): Int =
    if (n <= 1) r
    else fact(n-1,r*n)


operator fun Date.compareTo(d: Date) = when {
    year != d.year -> year - d.year
    month != d.month -> month - d.month
    else -> day - d.day
}








