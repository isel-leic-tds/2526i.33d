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
    @Test fun `create invalid date`() {
        val ex = assertFailsWith<IllegalArgumentException> {
            Date(2025,0)
        }
        assertEquals("Invalid month 0",ex.message)
    }
    @Test fun `Add days to Date in same month`() {
        val sut = Date(2025,9,30) + 2
        // a + b  -> a.plus(b)
        assertEquals(2,sut.day)
        assertEquals(10,sut.month)
        assertEquals(2025,sut.year)
    }
    @Test fun `Add days to Date in other year`() {
        val sut = Date(2025,12,30) + 2
        // a + b  -> a.plus(b)
        assertEquals(1,sut.day)
        assertEquals(1,sut.month)
        assertEquals(2026,sut.year)
    }

    @Test fun factorial() {
        assertEquals(1,fact(1))
        assertEquals(2,fact(2))
        assertEquals(6,fact(3))
        assertEquals(24,fact(4))
    }
}