package com.study.web.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.web.exception.Error;
import com.study.web.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

		Response<Void> error = Response.<Void>builder()
				.code(Error.PERMISSION_DENIED.getCode())
				.message(Error.PERMISSION_DENIED.getMessage())
				.build();

		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		String json = new ObjectMapper().writeValueAsString(error);

		response.getWriter().write(json);
		response.getWriter().flush();

	}
}
