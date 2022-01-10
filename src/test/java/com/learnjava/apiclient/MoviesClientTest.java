package com.learnjava.apiclient;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MoviesClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MoviesClient moviesClient = new MoviesClient(webClient);

    @RepeatedTest(10)
    void retrieveMovie() {
        var movieId = 1L;

        var movie = moviesClient.retrieveMovie(movieId);

        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
    }

    @RepeatedTest(10)
    void retrieveMovieCF() {
        var movieId = 1L;

        var movie = moviesClient.retrieveMovieCompletableFuture(movieId).join();

        assertNotNull(movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
    }

    @RepeatedTest(10)
    void retrieveMovies() {
        var movieIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        var movies = moviesClient.retrieveMovies(movieIds);

        assertNotNull(movies);
        assertEquals(7, movies.size());
    }

    @RepeatedTest(10)
    void retrieveMoviesCF() {
        var movieIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        var movies = moviesClient.retrieveMoviesCompletableFuture(movieIds);

        assertNotNull(movies);
        assertEquals(7, movies.size());
    }
}