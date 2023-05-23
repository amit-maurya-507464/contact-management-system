package com.example.cms.utils;

import com.example.cms.constants.MessageConstants;
import com.example.cms.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonUtilsService {

    @Autowired
    private ResponseHandler responseHandler;

    public ResponseEntity<ApiResponse> requestValidation(BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        log.error(MessageConstants.REQUEST_ERROR, errors);
        return responseHandler.generateResponse("", errors.toString(), HttpStatus.BAD_REQUEST);
    }

}
