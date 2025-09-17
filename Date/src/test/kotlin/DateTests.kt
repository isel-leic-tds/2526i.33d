import kotlin.test.*


class DateTests {
    @Test fun createDate() {
        val sut = Date(2025, 9, 17)
        assertEquals(2025, sut.year)
        assertEquals(9, sut.month)
        assertEquals(17, sut.day)
    }
    @Test fun createDefaultDate() {
        val sut = Date()
        assertEquals(2025, sut.year)
        assertEquals(1, sut.month)
        assertEquals(1, sut.day)
    }
    @Test fun createDateDefaultDay() {
        val sut = Date(2025,9)
        assertEquals(2025, sut.year)
        assertEquals(9, sut.month)
        assertEquals(1, sut.day)
    }
    @Test fun createDateDefaultMonthDay() {
        val sut = Date(2025)
        assertEquals(2025, sut.year)
        assertEquals(1, sut.month)
        assertEquals(1, sut.day)

    }
    @Test fun askIsLeapYear() {
        val sut = Date(2024,9,1)
        assertTrue(sut.leapYear)
        val sut2 = Date(2025)
        assertFalse(sut2.leapYear)
    }
    @Test fun lastDayOfMonth() {
        val sut = Date(2025,9)
        assertEquals(30,sut.lastDayOfMonth)
    }
}