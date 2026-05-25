package com.ead.authuser.specifications;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.domain.Specification;

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
}