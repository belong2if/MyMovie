package com.moviesearch.movie.dto;

import java.util.List;
import com.moviesearch.movie.dto.MovieDetailResponse;

public class MovieSearchResponse {

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public static class Movie {
        private int id;
        private String title;
        private String overview;
        private String release_date;
        private String poster_path;

        public int getId(){
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }
        public String getPoster_path() {  
        return poster_path;
    }

    public void setPoster_path(String poster_path) { 
        this.poster_path = poster_path;
    }
    }
}