package com.retail.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
