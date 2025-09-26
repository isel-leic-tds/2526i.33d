class MutableStack1<T> {
    private var mem = emptyList<T>()

    fun push(e: T) { mem = mem + e }
    fun pop(): T {
        val e = mem.last()
        mem = mem.dropLast(1)
        return e
    }
    fun isEmpty() = mem.isEmpty()
    val top: T get() = mem.last()
}