package com.ead.authuser.services;

import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.dtos.CourseDTO;
import com.ead.authuser.specifications.CourseFilter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserCourseService {

    PageResponse<CourseDTO> getCourses(CourseFilter courseFilter, Pageable pageable);

    void subscribeUserInCourse(UUID userId, UUID courseId);
}
