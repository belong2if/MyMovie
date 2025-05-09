package com.moviesearch.movie.service;

import com.moviesearch.movie.dto.MovieSearchResponse;
import com.moviesearch.movie.dto.MovieDetailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TmdbService {

    private final RestTemplate restTemplate;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbService() {
        this.restTemplate = new RestTemplate();
    }

    public MovieSearchResponse searchMovies(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = String.format(
                "https://api.themoviedb.org/3/search/movie?api_key=%s&language=ko-KR&query=%s",
                apiKey, encodedQuery
        );
        return restTemplate.getForObject(url, MovieSearchResponse.class);
    }

    public String getPopularMovies() {
        String url = String.format(
                "https://api.themoviedb.org/3/movie/popular?api_key=%s&language=ko-KR&page=1",
                apiKey
        );
        return restTemplate.getForObject(url, String.class);
    }

    public String getRecommendationsByMovieId(String movieId) {
        String url = String.format(
            "https://api.themoviedb.org/3/movie/%s/recommendations?api_key=%s&language=ko-KR&page=1",
            movieId, apiKey
        );
        return restTemplate.getForObject(url, String.class);
    }

    public MovieDetailResponse getMovieDetail(Long movieId) {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&language=ko-KR";

        ResponseEntity<MovieDetailResponse> response =
                restTemplate.getForEntity(url, MovieDetailResponse.class);

        return response.getBody();
    }
}