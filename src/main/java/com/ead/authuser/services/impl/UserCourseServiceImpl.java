package com.ead.authuser.services.impl;

import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.clients.CourseApiClient;
import com.ead.authuser.dtos.CourseDTO;
import com.ead.authuser.exception.notfound.UserNotFoundException;
import com.ead.authuser.exception.validation.UserAlreadySubscribedException;
import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserCourseRepository;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserCourseService;
import com.ead.authuser.specifications.CourseFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    CourseApiClient courseApiClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCourseRepository userCourseRepository;

    @Override
    public PageResponse<CourseDTO> getCourses(CourseFilter courseFilter, Pageable pageable) {
        return courseApiClient.getCourses(courseFilter, pageable);
    }

    @Override
    public void subscribeUserInCourse(UUID userId, UUID courseId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        log.info("user found successfully");

        if (userCourseRepository.existsByCourseIdAndUser(courseId, user)) {
            throw new UserAlreadySubscribedException();
        }

        log.info("user can be subscribed");

        UserCourseModel userCourseModel = new UserCourseModel();
        userCourseModel.setCourseId(courseId);
        userCourseModel.setUser(user);

        userCourseRepository.save(userCourseModel);

        log.info("user subscribed in course successfully");
    }
}
