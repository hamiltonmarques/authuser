package com.ead.authuser.specifications;

import com.ead.authuser.enums.CourseLevel;
import com.ead.authuser.enums.CourseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CourseFilter {
    private String name;
    private UUID instructorId;
    private CourseStatus status;
    private CourseLevel level;
    private UUID userId;
}