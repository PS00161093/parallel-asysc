package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import com.learnjava.util.CommonUtil;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;

public class MoviesClient {

    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(Long movieInfoId) {
        startTimer();
        var movieInfo = invokeMovieInfoService(movieInfoId);
        var reviews = invokeReviewService(movieInfoId);
        timeTaken();
        stopWatchReset();
        return new Movie(movieInfo, reviews);
    }

    public CompletableFuture<Movie> retrieveMovieCompletableFuture(Long movieInfoId) {
        startTimer();
        var movieInfo = CompletableFuture.supplyAsync(() -> invokeMovieInfoService(movieInfoId));
        var reviews = CompletableFuture.supplyAsync(() -> invokeReviewService(movieInfoId));
        timeTaken();
        stopWatchReset();
        return movieInfo.thenCombine(reviews, Movie::new);
    }

    private MovieInfo invokeMovieInfoService(Long movieInfoId) {
        var movieInfoServiceContextPath = "/v1/movie_infos/{movieInfoId}";
        return webClient
                .get()
                .uri(movieInfoServiceContextPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReviewService(Long movieInfoId) {
        var movieInfoServiceContextPath = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toString();

        return webClient
                .get()
                .uri(movieInfoServiceContextPath)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
