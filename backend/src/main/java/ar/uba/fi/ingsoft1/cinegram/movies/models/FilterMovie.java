package ar.uba.fi.ingsoft1.cinegram.movies.models;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class FilterMovie {
    public static Specification<Movie> byTitle(String title) {
        return (root, query, cb) ->
                title == null ? null :
                        cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> byActor(Long actorId) {
        return (root, query, cb) -> {
            if (actorId == null) return null;
            Join<Object, Object> actors = root.join("actors", JoinType.LEFT);
            return cb.equal(actors.get("id"), actorId);
        };
    }

    public static Specification<Movie> byCategory(Long categoryId) {
        return (root, query, cb) -> {
            if (categoryId == null) return null;
            Join<Object, Object> categories = root.join("categories", JoinType.LEFT);
            return cb.equal(categories.get("id"), categoryId);
        };
    }
}
