package com.retail.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.retail.ecommerce.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>{

}
