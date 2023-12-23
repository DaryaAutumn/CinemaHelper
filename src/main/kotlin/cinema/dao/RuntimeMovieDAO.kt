package cinema.dao

import cinema.entity.Movie
import cinema.serialization.CinemaSerializer

class RuntimeMovieDAO : MovieDAO {

    override fun addActor(name: String, movie: Movie) {
        val movies = CinemaSerializer.deserializeMovies()
        val m = findMovieId(movie, movies)
        m.actors.add(name)
        CinemaSerializer.serializeMovies(movies)
    }

    // finds the same object as 'movie' in collection of movies
    override fun findMovieId(movie: Movie, movies: MutableList<Movie>): Movie {
        var ind = 0
        for (i in 0..<movies.size) {
            if (movies[i].equals(movie)) {
                ind = i
            }
        }
        return movies[ind]
    }
}