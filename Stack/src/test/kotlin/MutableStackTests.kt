import kotlin.test.*

class MutableStackTests {
    @Test fun `stack empty conditions`() {
        val sut = MutableStack<Int>()
        assertTrue(sut.isEmpty())
        assertFailsWith<NoSuchElementException> { sut.pop() }
        assertFailsWith<NoSuchElementException> { sut.top }
    }
    @Test fun `stack not empty conditions`() {
        val sut = MutableStack<Int>()
        sut.push(1)
        assertFalse(sut.isEmpty())
        assertEquals(1,sut.top)
        assertEquals(1,sut.pop())
        assertTrue(sut.isEmpty())
    }
    @Test fun `main test`() {
        val stk = MutableStack<Char>()
        stk.push('A')
        stk.push('B')
        assertEquals('B',stk.top)
        stk.push('C')
        var output = ""
        while ( !stk.isEmpty() ) {
            val e = stk.pop()
            output += e
        }
        assertEquals("CBA",output)
    }
    @Test fun `equal stacks`() {
        val s1 = MutableStack<String>()
        s1.push("TDS"); s1.push("ISEL")
        val s2 = MutableStack<String>()
        s2.push("TDS"); s2.push("ISEL")
        assertEquals(s2,s1)
        assertTrue(s1.equals(s2))
        assertTrue(MutableStack<Char>() == MutableStack<Int>())
    }
}