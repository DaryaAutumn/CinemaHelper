package cinema.dao

import cinema.entity.Movie
import cinema.serialization.CinemaSerializer

class RuntimeMovieDAO : MovieDAO {

    // following methods change movie properties and save changes to json file
    override fun addActor(name: String, movie: Movie) {
        val movies = CinemaSerializer.deserializeMovies()
        val m = findMovieId(movie, movies)
        m.actors.add(name)
        CinemaSerializer.serializeMovies(movies)
    }

    override fun changeName(name: String, movie: Movie) {
        val movies = CinemaSerializer.deserializeMovies()
        val m = findMovieId(movie, movies)
        m.name = name
        CinemaSerializer.serializeMovies(movies)
    }

    override fun changeDescription(description: String, movie: Movie) {
        val movies = CinemaSerializer.deserializeMovies()
        val m = findMovieId(movie, movies)
        m.description = description
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