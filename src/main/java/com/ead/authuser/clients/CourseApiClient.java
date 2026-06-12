package com.ead.authuser.clients;

import com.ead.authuser.api.response.ApiResponse;
import com.ead.authuser.api.response.PageResponse;
import com.ead.authuser.dtos.CourseDTO;
import com.ead.authuser.exception.ExternalApiException;
import com.ead.authuser.specifications.CourseFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseApiClient {

    private final WebClient courseWebClient;

    @Value("${app.clients.course-api.label}")
    String apiLabel;

    public PageResponse<CourseDTO> getCourses(CourseFilter filter, Pageable pageable) {
        log.info("getting courses from {}...", apiLabel);
        ApiResponse<PageResponse<CourseDTO>> apiResponse =
                courseWebClient
                        .get()
                        .uri(uriBuilder -> buildUri(uriBuilder, filter, pageable))
                        .retrieve()
                        .onStatus(HttpStatus::isError,
                                response -> response
                                        .bodyToMono(new ParameterizedTypeReference<ApiResponse<Object>>() {
                                        })
                                        .map(error -> new ExternalApiException(
                                                apiLabel,
                                                response.statusCode(),
                                                error))
                        )
                        .bodyToMono(new ParameterizedTypeReference<ApiResponse<PageResponse<CourseDTO>>>() {
                        })
                        .block();

        return Objects.requireNonNull(apiResponse).getData();
    }

    private URI buildUri(UriBuilder uriBuilder, CourseFilter filter, Pageable pageable) {
        uriBuilder.path("/courses");

        addIfPresent(uriBuilder, "name", filter.getName());
        addIfPresent(uriBuilder, "instructorId", filter.getInstructorId());
        addIfPresent(uriBuilder, "status", filter.getStatus());
        addIfPresent(uriBuilder, "level", filter.getLevel());
        addIfPresent(uriBuilder, "userId", filter.getUserId());

        uriBuilder.queryParam("page", pageable.getPageNumber());
        uriBuilder.queryParam("size", pageable.getPageSize());

        pageable.getSort().forEach(order ->
                uriBuilder.queryParam(
                        "sort",
                        order.getProperty() + "," + order.getDirection()));

        return uriBuilder.build();
    }

    private void addIfPresent(UriBuilder uriBuilder, String paramName, Object value) {
        if (value != null) {
            uriBuilder.queryParam(paramName, value);
        }
    }
}
