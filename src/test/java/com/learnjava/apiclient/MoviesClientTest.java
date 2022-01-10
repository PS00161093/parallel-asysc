package com.learnjava.apiclient;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.reactive.function.client.WebClient;

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
}