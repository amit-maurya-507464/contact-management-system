package com.example.cms.utils;

import com.example.cms.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ResponseHandler {
	
	@Autowired
    private MessageSource messageSource;
	
	private ResponseHandler() {}

	public ResponseEntity<ApiResponse> generateResponse(Object response, String messageCode, HttpStatus httpStatus) {
		ApiResponse apiResponse = new ApiResponse(
				response,
				messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale()),
				new Date().getTime()
		);
		return new ResponseEntity<>(apiResponse, httpStatus);
	}

}
