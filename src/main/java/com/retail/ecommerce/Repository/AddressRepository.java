package com.retail.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
