fun main() {
    val stk = MutableStack2<Char>()
    stk.push('A')
    stk.push('B')
    print(stk.top)
    stk.push('C')
    while ( !stk.isEmpty() ) {
        val e = stk.pop()
        print(e)
    }
    println()
    // -> BCBA
}