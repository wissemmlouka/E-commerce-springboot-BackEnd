package com.wissem.ecommerce.dao;


import com.wissem.ecommerce.entity.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    public List<Product> findAll(Pageable pageable);

    public List<Product> findAllByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(String searchKey1,String searchKey2, Pageable pageable);

}
