package cinema.entity

data class CinemaHall(
    val rows: Int,
    val lines: Int
) {

    var movies: MutableList<Movie> = mutableListOf()
    var sessions: MutableList<Session> = mutableListOf()

}