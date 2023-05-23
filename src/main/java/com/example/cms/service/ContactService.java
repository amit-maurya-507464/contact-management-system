package com.example.cms.service;

import com.example.cms.constants.MessageCode;
import com.example.cms.dto.ApiResponse;
import com.example.cms.dto.ContactDTO;
import com.example.cms.dto.PageDTO;
import com.example.cms.entity.Contact;
import com.example.cms.repository.ContactRepository;
import com.example.cms.utils.ModalMapperUtil;
import com.example.cms.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ResponseHandler responseHandler;

    public ResponseEntity<ApiResponse> saveContact(ContactDTO contactDTO) {
        contactDTO.setEmail(contactDTO.getEmail().trim().toLowerCase());
        ContactDTO contactResponse;
        if (existContactByEmail(contactDTO.getEmail())) {
            return responseHandler.generateResponse("", MessageCode.CONTACT_EXIST_EMAIL, HttpStatus.BAD_REQUEST);
        }
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact = contactRepository.save(contact);
        return responseHandler.generateResponse(ModalMapperUtil.map(contact, ContactDTO.class), MessageCode.CONTACT_CREATED, HttpStatus.OK);
    }

    public Contact findContact(long id) {
        return contactRepository.findContactById(id);
    }

    public ResponseEntity<ApiResponse> editContact(ContactDTO contactDTO) {
        contactDTO.setEmail(contactDTO.getEmail().trim().toLowerCase());
        Contact contact = findContact(contactDTO.getId());
        if (contact!=null) {
            if (existContactByEmailAndNotId(contactDTO.getEmail(), contact.getId())) {
                return responseHandler.generateResponse("", MessageCode.CONTACT_EXIST_EMAIL, HttpStatus.BAD_REQUEST);
            }
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhoneNumber(contactDTO.getPhoneNumber());
            contact = contactRepository.save(contact);
            return responseHandler.generateResponse(ModalMapperUtil.map(contact, ContactDTO.class), MessageCode.CONTACT_EDITED, HttpStatus.OK);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> deleteContact(long id) {
        Contact contact = findContact(id);
        if (contact!=null) {
            contact.setDeleted(true);
            contactRepository.save(contact);
            return responseHandler.generateResponse("", MessageCode.CONTACT_DELETED, HttpStatus.OK);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_NOT_FOUND, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<ApiResponse> findContactData(long id) {
        Contact contact = findContact(id);
        if (contact!=null) {
            return responseHandler.generateResponse(ModalMapperUtil.map(contact, ContactDTO.class), MessageCode.CONTACT_FETCHED, HttpStatus.OK);
        }
        return responseHandler.generateResponse("", MessageCode.CONTACT_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> searchContacts(String keyword) {
        List<ContactDTO> contactDTOList = ModalMapperUtil.mapAll(contactRepository.findByFirstNameOrLastNameOrEmail(keyword), ContactDTO.class);
        return responseHandler.generateResponse(contactDTOList, MessageCode.CONTACT_FETCHED, HttpStatus.OK);
    }

    public boolean existContactByEmail(String email) {
        return contactRepository.existsByEmail(email);
    }

    public boolean existContactByEmailAndNotId(String email, Long id) {
        return contactRepository.existsByEmailAndNotId(email, id);
    }

    public ResponseEntity<ApiResponse> findAllContactData(Integer limit, Integer pageNo) {
        Page<Contact> contacts = contactRepository.findAllContacts(PageRequest.of(pageNo, limit));
        PageDTO response = new PageDTO(ModalMapperUtil.mapAll(contacts.getContent(), ContactDTO.class),
                contacts.getContent().size(), contacts.getTotalPages(), contacts.getTotalElements());
        return responseHandler.generateResponse(response, MessageCode.CONTACT_FETCHED, HttpStatus.OK);
    }
}
