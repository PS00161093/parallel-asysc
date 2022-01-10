package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import com.learnjava.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MoviesClient {

    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(Long movieInfoId) {
        var movieInfo = invokeMovieInfoService(movieInfoId);
        var reviews = invokeReviewService(movieInfoId);

        return new Movie(movieInfo, reviews);
    }

    private MovieInfo invokeMovieInfoService(Long movieInfoId) {
        var movieInfoServiceContextPath = "/v1/movies_infos/{movieInfoId}";
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
                .toUri();

        return webClient
                .get()
                .uri(movieInfoServiceContextPath)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
