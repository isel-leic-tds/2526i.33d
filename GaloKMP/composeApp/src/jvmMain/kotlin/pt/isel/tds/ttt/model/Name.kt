package pt.isel.tds.ttt.model

@JvmInline
value class Name(val name: String) {
    init {
        require( isValid(name) ) { "Invalid name $name" }
    }
    override fun toString() = name
    companion object {
        fun isValid(name: String) =
            name.isNotEmpty() && name.all { it.isLetterOrDigit() }
    }
}