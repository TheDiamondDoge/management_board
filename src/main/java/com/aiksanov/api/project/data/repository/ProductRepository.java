package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
