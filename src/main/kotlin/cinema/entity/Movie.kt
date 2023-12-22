package cinema.entity

data class Movie(
    var name: String,
    var description: String
) {

    var actors: MutableList<String> = mutableListOf()

    override fun toString(): String {
        var string = "$name. $description."
        if (actors.isNotEmpty()){
            for (actor in actors){
                string += "$actor "
            }
        }
        return string
    }

}