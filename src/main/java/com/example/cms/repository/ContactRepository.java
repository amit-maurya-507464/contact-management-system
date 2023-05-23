package com.example.cms.repository;

import com.example.cms.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(nativeQuery = true, value = "select Exists (Select * from contact where email =:email and id != :id and not is_deleted)")
    boolean existsByEmailAndNotId(String email, long id);

    @Query(nativeQuery = true, value = "select Exists (Select * from contact where email =:email and not is_deleted)")
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "Select * from contact where (first_name ILIKE %:keyword% or last_name ILIKE %:keyword% or email ILIKE %:keyword%) and not is_deleted")
    List<Contact> findByFirstNameOrLastNameOrEmail(String keyword);

    @Query(nativeQuery = true, value = "Select * from contact where id =:id and not is_deleted")
    Contact findContactById(long id);

    @Query(nativeQuery = true, value = "Select * from contact where not is_deleted")
    Page<Contact> findAllContacts(Pageable pageable);
}