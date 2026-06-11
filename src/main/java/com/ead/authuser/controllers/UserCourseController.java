package com.ead.authuser.controllers;

import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.dtos.CourseDTO;
import com.ead.authuser.dtos.ResponseDTO;
import com.ead.authuser.services.UserCourseService;
import com.ead.authuser.specifications.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

    @Autowired
    UserCourseService userCourseService;

    @GetMapping("/courses")
    public ResponseEntity<?> getCourses(CourseFilter courseFilter,
                                        @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        PageResponse<CourseDTO> pageResponse = userCourseService.getCourses(courseFilter, pageable);
        return ResponseDTO.ok("Courses listed successfully", pageResponse);
    }
}
