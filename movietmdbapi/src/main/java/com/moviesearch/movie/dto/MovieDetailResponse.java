package com.moviesearch.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailResponse {
    private String title;
    private String overview;
    private String release_date;
    private String poster_path;
    private List<Genre> genres;

    @Getter
    @Setter
    public static class Genre {
        private String name;
    }
}