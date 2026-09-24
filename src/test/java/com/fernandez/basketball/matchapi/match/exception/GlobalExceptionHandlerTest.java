package com.fernandez.basketball.matchapi.match.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class GlobalExceptionHandlerTest {

    @Test
    void maps_app_exception_to_http_error_ok() {
        // given
        var request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/v1/matches/missing");
        var exception = new AppException(AppErrorCode.MATCH_NOT_FOUND);
        var handler = new GlobalExceptionHandler();

        // when
        var response = handler.handleAppException(exception, request);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().code()).isEqualTo("MATCH_NOT_FOUND");
        assertThat(response.getBody().path()).isEqualTo("/api/v1/matches/missing");
    }
}
