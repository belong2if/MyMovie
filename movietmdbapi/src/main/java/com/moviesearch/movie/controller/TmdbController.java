package com.moviesearch.movie.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.moviesearch.movie.service.TmdbService;
import com.moviesearch.movie.dto.MovieSearchResponse;
import com.moviesearch.movie.dto.MovieDetailResponse;

@Tag(name = "TMDB 영화 검색 API", description = "TMDB 외부 API를 이용한 영화 검색 기능")
@RestController
@RequestMapping("/tmdb")
public class TmdbController {

    private final TmdbService tmdbService;

    public TmdbController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @Operation(summary = "영화 제목 검색", description = "제목 키워드를 입력하면 TMDB에서 영화 목록을 반환.")
    @GetMapping("/search")
    public ResponseEntity<MovieSearchResponse> searchMovie(@RequestParam String query) {
        return ResponseEntity.ok(tmdbService.searchMovies(query));
    }

    @Operation(summary = "인기 영화 조회", description = "TMDB에서 인기 영화 목록을 가져옴.")
    @GetMapping("/popular")
    public ResponseEntity<?> getPopularMovies() {
        return ResponseEntity.ok(tmdbService.getPopularMovies());
    }

    @Operation(summary = "추천 영화 조회", description = "특정 영화 ID에 기반한 추천 영화 목록을 TMDB에서 가져옴.")
    @GetMapping("/recommend/{id}")
    public ResponseEntity<String> getRecommendations(@PathVariable String id) {
    return ResponseEntity.ok(tmdbService.getRecommendationsByMovieId(id));
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<MovieDetailResponse> getMovieDetail(@PathVariable Long id) {
    return ResponseEntity.ok(tmdbService.getMovieDetail(id));
    }
}