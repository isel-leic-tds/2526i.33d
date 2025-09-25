
const val GREGORIAN_YEAR = 1582
const val MAX_YEAR = 2200

const val DAY_BITS = 5
const val MONTH_BITS = 4
const val YEAR_BITS = 12

@JvmInline
value class Date private constructor(private val bits: Int) {
    val year: Int get() = bits shr (DAY_BITS+MONTH_BITS)
    val month: Int get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS)-1)
    val day: Int get() = bits and ((1 shl DAY_BITS)-1)

    constructor(y: Int = 2025, m: Int = 1, d: Int = 1)
        :this((y shl (DAY_BITS+MONTH_BITS)) or (m shl DAY_BITS) or d)
    {
        require(y in GREGORIAN_YEAR..MAX_YEAR) { "Invalid year $year" }
        require(m in 1..dayOfMonth.size) { "Invalid month $month" }
        require(d in 1..lastDayOfMonth) { "Invalid day $day" }
    }
    override fun toString(): String =
        "$year-" + "%02d-%02d".format(month,day)
    operator fun compareTo(d: Date) = bits - d.bits
}

private val dayOfMonth = listOf(31,28,31,30,31,30,31,31,30,31,30,31)

val Date.lastDayOfMonth: Int
    get() = if (month==2 && leapYear) 29 else dayOfMonth[month-1]

val Date.leapYear: Boolean get() = year.isLeapYear

val Int.isLeapYear get() = this%4==0 && this%100!=0 || this%400==0

operator fun Date.plus(days: Int): Date = this.addDays(days)
operator fun Int.plus(date: Date): Date = date.addDays(this)

tailrec fun Date.addDays(days :Int): Date {
    val d = day + days
    return when {
        d <= lastDayOfMonth ->
            Date(year, month, d)
        else -> {
            val dt = if (month < dayOfMonth.size) Date(year, month+1)
                     else Date(year+1)
            dt.addDays(days - (lastDayOfMonth - day + 1))
        }
    }
}








