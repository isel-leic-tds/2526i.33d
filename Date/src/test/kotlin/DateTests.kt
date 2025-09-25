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
    @Test fun `Equality of dates`() {
        val sut = Date(2025,9,19)
        //println(sut)
        // a == b  -->  a.equals(b)
        //println( sut == Date(2025,9,19) )
        assertEquals(sut,sut)
        assertEquals(Date(2025,9,19),sut)
        assertFalse(sut.equals("ABC"))
        assertFalse(sut.equals(null))

        assertEquals(Date(2025,9,19).hashCode(),sut.hashCode())
        assertNotEquals((sut+1).hashCode(), sut.hashCode())
    }
    @Test fun `relative comparation dates`() {
        val sut = Date(2025,9,19)
        assertTrue( Date(2025,11,1) > sut)
        assertTrue( Date(2024,11,1) < sut)
        assertTrue( Date(2025,9,1) < sut)
        // a > b --> a.compareTo(b) > 0
    }
    @Test fun `text representation of date`() {
        val sut = Date(2025,9,19)
        assertEquals("2025-09-19",sut.toString())
        // println(sut)
    }
}