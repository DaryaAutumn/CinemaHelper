package cinema.dao

import cinema.entity.Movie

interface MovieDAO {

    fun addActor(name: String, movie : Movie)

    fun changeName(name: String, movie: Movie)

    fun changeDescription(description: String, movie: Movie)

    fun findMovieId(movie: Movie, movies : MutableList<Movie>) : Movie

}