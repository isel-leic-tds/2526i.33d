class MutableStack2<T> {
    private val mem: MutableList<T> = mutableListOf()

    fun push(e: T) = mem.addLast(e)
    fun pop(): T = mem.last().also { mem.removeLast() }
    fun isEmpty() = mem.isEmpty()
    val top: T get() = mem.last()

    override fun equals(other: Any?) =
        other is MutableStack2<T> && mem == other.mem

    override fun hashCode() = mem.hashCode()
}

/*
inline fun <T> T.also( fx: ()->Unit ): T {
    fx()
    return this
}
*/