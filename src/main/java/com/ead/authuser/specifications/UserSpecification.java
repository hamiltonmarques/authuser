package com.ead.authuser.specifications;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class UserSpecification {

    public static Specification<UserModel> byFilter(UserFilter filter) {
        Specification<UserModel> spec = Specification.where(null);

        if (filter.getType() != null) {
            spec = spec.and(typeEquals(filter.getType()));
        }

        if (filter.getStatus() != null) {
            spec = spec.and(statusEquals(filter.getStatus()));
        }

        if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
            spec = spec.and(emailLike(filter.getEmail()));
        }

        if (filter.getCourseId() != null) {
            spec = spec.and(hasCourseId(filter.getCourseId()));
        }

        return spec;
    }

    private static Specification<UserModel> typeEquals(UserType type) {
        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }

    private static Specification<UserModel> statusEquals(UserStatus status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    private static Specification<UserModel> emailLike(String email) {
        return (root, query, cb) ->
                cb.like(
                        cb.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"
                );
    }

    public static Specification<UserModel> hasCourseId(UUID id) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<UserModel, UserCourseModel> join = root.join("userCourses");
            return cb.equal(join.get("courseId"), id);
        };
    }
}