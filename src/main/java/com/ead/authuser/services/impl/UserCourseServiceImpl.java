package com.ead.authuser.services.impl;

import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.clients.CourseApiClient;
import com.ead.authuser.dtos.CourseDTO;
import com.ead.authuser.services.UserCourseService;
import com.ead.authuser.specifications.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    CourseApiClient courseApiClient;

    @Override
    public PageResponse<CourseDTO> getCourses(CourseFilter courseFilter, Pageable pageable) {
        return courseApiClient.getCourses(courseFilter, pageable);
    }
}
