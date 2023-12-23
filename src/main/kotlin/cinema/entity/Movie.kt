package cinema.entity

import cinema.ui.ConsoleDrawer
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    var name: String,
    var description: String
) {

    var actors: MutableList<String> = mutableListOf()

    override fun toString(): String {
        var string = "${"\u001b[33m"}$name${"\u001b[0m"}. $description."
        if (actors.isNotEmpty()) {
            for (actor in actors) {
                string += "$actor "
            }
        }
        return string
    }

}