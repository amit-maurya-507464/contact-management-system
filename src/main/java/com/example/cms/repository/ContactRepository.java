package com.example.cms.repository;

import com.example.cms.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(nativeQuery = true, value = "select Exists (Select * from contact where email =:email and id != :id)")
    boolean existsByEmailAndNotId(String email, long id);

    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "Select * from contact where first_name ILIKE %:keyword% or last_name ILIKE %:keyword% or email ILIKE %:keyword%")
    List<Contact> findByFirstNameOrLastNameOrEmail(String keyword);

}