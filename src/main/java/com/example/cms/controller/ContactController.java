package com.example.cms.controller;

import com.example.cms.constants.MessageCode;
import com.example.cms.constants.MessageConstants;
import com.example.cms.constants.UrlConstants;
import com.example.cms.dto.ContactDTO;
import com.example.cms.dto.PageDTO;
import com.example.cms.entity.Contact;
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

import java.util.List;

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
    public ResponseEntity<Object> createContact(@Validated @RequestBody ContactDTO contactDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.CONTACT_CREATE_REQUEST, contactDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            contactDTO.setEmail(contactDTO.getEmail().trim().toLowerCase());
            ContactDTO contactResponse;
            if (contactService.existContactByEmail(contactDTO.getEmail())) {
                return responseHandler.generateResponse("", MessageCode.CONTACT_EXIST_EMAIL, false, HttpStatus.BAD_REQUEST);
            } else {
                contactResponse = contactService.saveContact(contactDTO);
            }
            return responseHandler.generateResponse(contactResponse, MessageCode.CONTACT_CREATED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_CREATE_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_CREATE_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<Object> editContact(@Validated @RequestBody ContactDTO contactDTO, BindingResult bindingResult) {
        try {
            log.info(MessageConstants.CONTACT_EDIT_REQUEST, contactDTO);
            if (bindingResult.hasErrors()) {
                return commonUtilsService.requestValidation(bindingResult);
            }
            contactDTO.setEmail(contactDTO.getEmail().trim().toLowerCase());
            Contact contact = contactService.findContact(contactDTO.getId());
            if (contact==null) {
                return responseHandler.generateResponse("", MessageCode.CONTACT_NOT_FOUND, false, HttpStatus.BAD_REQUEST);
            }
            ContactDTO contactResponse;
            if (contactService.existContactByEmailAndNotId(contactDTO.getEmail(), contact.getId())) {
                return responseHandler.generateResponse("", MessageCode.CONTACT_EXIST_EMAIL, false, HttpStatus.BAD_REQUEST);
            } else {
                contactResponse = contactService.editContact(contact, contactDTO);
            }
            return responseHandler.generateResponse(contactResponse, MessageCode.CONTACT_EDITED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_EDIT_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_EDIT_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<Object> deleteContact(@RequestParam long id) {
        try {
            log.info(MessageConstants.CONTACT_DELETE_REQUEST, id);
            Contact contact = contactService.findContact(id);
            if (contact==null) {
                return responseHandler.generateResponse("", MessageCode.CONTACT_NOT_FOUND, false, HttpStatus.BAD_REQUEST);
            }
            contactService.deleteContact(contact);
            return responseHandler.generateResponse("", MessageCode.CONTACT_DELETED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_DELETE_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_DELETE_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.CONTACT)
    public ResponseEntity<Object> getContact(@RequestParam long id) {
        try {
            log.info(MessageConstants.CONTACT_GET_REQUEST, id);
            ContactDTO contactDTO = contactService.findContactData(id);
            return responseHandler.generateResponse(contactDTO, MessageCode.CONTACT_FETCHED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_GET_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.ALL_CONTACT)
    public ResponseEntity<Object> getAllContact(@RequestParam(defaultValue = "5") Integer limit, @RequestParam(defaultValue = "0") Integer pageNo) {
        try {
            log.info(MessageConstants.ALL_CONTACT_GET_REQUEST, limit, pageNo);
            PageDTO contacts = contactService.findAllContactData(limit, pageNo);
            return responseHandler.generateResponse(contacts, MessageCode.CONTACT_FETCHED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_GET_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = UrlConstants.CONTACT_SEARCH)
    public ResponseEntity<Object> getContact(@RequestParam String keyword) {
        try {
            log.info(MessageConstants.CONTACT_SEARCH_REQUEST, keyword);
            List<ContactDTO> contactDTO = contactService.searchContacts(keyword);
            return responseHandler.generateResponse(contactDTO, MessageCode.CONTACT_FETCHED, true, HttpStatus.OK);
        } catch (Exception e) {
            log.error(MessageConstants.CONTACT_SEARCH_ERROR, e);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_GET_ERROR, false, HttpStatus.BAD_REQUEST);
    }

}
