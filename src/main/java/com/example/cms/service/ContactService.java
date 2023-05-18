package com.example.cms.service;

import com.example.cms.dto.ContactDTO;
import com.example.cms.dto.PageDTO;
import com.example.cms.entity.Contact;
import com.example.cms.repository.ContactRepository;
import com.example.cms.utils.ModalMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO saveContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact = contactRepository.save(contact);
        return ModalMapperUtil.map(contact, ContactDTO.class);
    }

    public Contact findContact(long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        return optionalContact.orElse(null);
    }

    public ContactDTO editContact(Contact contact, ContactDTO contactDTO) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setEmail(contactDTO.getEmail());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact = contactRepository.save(contact);
        return ModalMapperUtil.map(contact, ContactDTO.class);
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public ContactDTO findContactData(long id) {
        return ModalMapperUtil.map(findContact(id), ContactDTO.class);
    }

    public List<ContactDTO> searchContacts(String keyword) {
        return ModalMapperUtil.mapAll(contactRepository.findByFirstNameOrLastNameOrEmail(keyword), ContactDTO.class);
    }

    public boolean existContactByEmail(String email) {
        return contactRepository.existsByEmail(email);
    }

    public boolean existContactByEmailAndNotId(String email, Long id) {
        return contactRepository.existsByEmailAndNotId(email, id);
    }

    public PageDTO findAllContactData(Integer limit, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, limit);
        Page<Contact> contacts = contactRepository.findAll(pageable);
        return new PageDTO(ModalMapperUtil.mapAll(contacts.getContent(), ContactDTO.class), contacts.getContent().size(),
                contacts.getTotalPages(), contacts.getTotalElements());
    }
}
