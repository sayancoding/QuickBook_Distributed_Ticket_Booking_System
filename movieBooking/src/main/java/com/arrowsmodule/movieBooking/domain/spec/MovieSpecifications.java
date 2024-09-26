package com.arrowsmodule.movieBooking.domain.spec;

import com.arrowsmodule.movieBooking.domain.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

public final class MovieSpecifications {

    public static Specification<Movie> hasMovieName(String movieName){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("movieName"),"%"+movieName+"%"));
    }
    public static Specification<Movie> hasLanguage(String language){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("language"),language));
    }
    public static Specification<Movie> hasGenre(String genre){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"),genre));
    }

}
