package com.retail.ecommerce.ServiceImpl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.retail.ecommerce.Model.Product;
import com.retail.ecommerce.RequestDTO.FilterOptions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

@Component
public class ProductSpecifications {
	
	private FilterOptions filterOptions;

	public ProductSpecifications(FilterOptions filterOptions) {
		super();
		this.filterOptions = filterOptions;
	}
	
	public Specification<Product> buildSpecification(FilterOptions options)
	{
		return (root,query,criteriaBuilder)->{
			
			List<Predicate> predicates=new ArrayList<>();
			
			if(filterOptions.getMinPrice() >0)
			{
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), filterOptions.getMinPrice()));
			}
			if(filterOptions.getMaxPrice() >0)
			{
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), filterOptions.getMaxPrice()));
			}
			if(filterOptions.getCategory()!=null && !filterOptions.getCategory().isEmpty() )
			{
				predicates.add(criteriaBuilder.equal(root.get("category"), filterOptions.getCategory()));
			}
			if(filterOptions.getAvailability()!=null)
			{
				predicates.add(criteriaBuilder.equal(root.get("availabilityStatus"),filterOptions.getAvailability()));
			}
			
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
	

}
