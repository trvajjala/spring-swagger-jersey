package com.tvajjala.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvajjala.persistence.model.Product;
/**
 *
 * Product Repository
 *
 * @author ThirupathiReddy V
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {



    Product findByProductId(String productId);


}
