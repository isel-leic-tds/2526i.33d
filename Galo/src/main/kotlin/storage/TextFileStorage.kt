package storage

import okio.*
import okio.Path.Companion.toPath

class TextFileStorage<Key,Data>(
    baseNameDirectory: String,
    val serializer: Serializer<Data>,
) : Storage<Key,Data>{
    val fs = FileSystem.SYSTEM
    val basePath: Path = baseNameDirectory.toPath()

    private fun Path.exists() = fs.exists(this)
    private fun Path.delete() = fs.delete(this)
    private fun Path.createDirectory() = fs.createDirectories(this)
    private fun Path.isDirectory() = fs.metadata(this).isDirectory
    private fun Path.writeText(data: Data) {
        fs.write(this) { writeUtf8(serializer.serialize(data)) }
    }
    private fun Path.readText(): Data =
        fs.read(this) { serializer.deserialize(readUtf8()) }

    init { with(basePath) { // Create base folder if it does not exist
        if (!exists()) createDirectory()
        else check(isDirectory()) { "$name is not a directory" }
    } }

    // Apply a function to the path of the file for the given key.
    private inline fun <R> withPath(key: Key, fx: Path.() -> R): R =
        (basePath / "$key.txt").fx()

    override fun create(k: Key, data: Data) {
        TODO("Not yet implemented")
    }
    override fun read(k: Key): Data? {
        TODO("Not yet implemented")
    }
    override fun update(k: Key, data: Data) {
        TODO("Not yet implemented")
    }
    override fun delete(k: Key) {
        TODO("Not yet implemented")
    }
}