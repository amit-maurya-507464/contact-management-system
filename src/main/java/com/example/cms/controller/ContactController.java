package com.example.cms.controller;

import com.example.cms.constants.MessageCode;
import com.example.cms.constants.MessageConstants;
import com.example.cms.constants.UrlConstants;
import com.example.cms.dto.ApiResponse;
import com.example.cms.dto.ContactDTO;
import com.example.cms.service.ContactService;
import com.example.cms.utils.CommonUtilsService;
import com.example.cms.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(UrlConstants.BASE_API_V1)
public class ContactController {

    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private CommonUtilsService commonUtilsService;
    @Autowired
    private ContactService contactService;

    @PostMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<ApiResponse> createContact(@Validated @RequestBody ContactDTO contactDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.CONTACT_CREATE_REQUEST, contactDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            return contactService.saveContact(contactDTO);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_CREATE_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_CREATE_ERROR, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<ApiResponse> editContact(@Validated @RequestBody ContactDTO contactDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.CONTACT_EDIT_REQUEST, contactDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            return contactService.editContact(contactDTO);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_EDIT_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_EDIT_ERROR, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<ApiResponse> deleteContact(@RequestParam long id) {
        try {
            log.info(MessageConstants.CONTACT_DELETE_REQUEST, id);
            return contactService.deleteContact(id);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_DELETE_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_DELETE_ERROR, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<ApiResponse> getContact(@RequestParam long id) {
        try {
            log.info(MessageConstants.CONTACT_GET_REQUEST, id);
            return contactService.findContactData(id);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_GET_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.ALL_CONTACT)
    public ResponseEntity<ApiResponse> getAllContact(@RequestParam(defaultValue = "5") Integer limit, @RequestParam(defaultValue = "0") Integer pageNo) {
        try {
            log.info(MessageConstants.ALL_CONTACT_GET_REQUEST, limit, pageNo);
            return contactService.findAllContactData(limit, pageNo);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_GET_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.CONTACT_SEARCH)
    public ResponseEntity<ApiResponse> getContact(@RequestParam String keyword) {
        try {
            log.info(MessageConstants.CONTACT_SEARCH_REQUEST, keyword);
            return contactService.searchContacts(keyword);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_SEARCH_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, HttpStatus.BAD_REQUEST);
    }

}
