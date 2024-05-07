package com.retail.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{

}
