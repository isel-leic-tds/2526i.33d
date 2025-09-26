class MutableStack<T> {
    private class Node<U>(val next: Node<U>?, val elem: U)

    private var head: Node<T>? = null

    private val first get() = head ?: throw NoSuchElementException("stack empty")

    fun push(e: T) { head = Node(head, e) }
    fun pop(): T = first.also { head = it.next }.elem
    fun isEmpty() = head == null
    val top: T get() = first.elem

    override fun equals(other: Any?): Boolean {
        if (other !is MutableStack<*>) return false
        var n1 = head
        var n2 = other.head
        while (n1 != null && n2 != null) {
            if (n1.elem != n2.elem) return false
            n1 = n1.next
            n2 = n2.next
        }
        return n1 == null && n2 == null
    }

    override fun hashCode(): Int {
        var res = 1
        var n = head
        while (n != null) {
            res = res * 31 + n.elem.hashCode()
            n = n.next
        }
        return res
    }
}








