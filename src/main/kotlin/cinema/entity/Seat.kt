package cinema.entity

data class Seat (
    val row : Int,
    val line: Int
){
    override fun toString(): String {
        return "${row + 1} ${line + 1}"
    }
}