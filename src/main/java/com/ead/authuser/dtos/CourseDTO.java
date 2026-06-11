package com.ead.authuser.dtos;

import com.ead.authuser.enums.CourseLevel;
import com.ead.authuser.enums.CourseStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID id;
    private UUID instructorId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus status;
    private CourseLevel level;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}