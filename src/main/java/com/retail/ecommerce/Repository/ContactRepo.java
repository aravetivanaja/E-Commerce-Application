package com.retail.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
