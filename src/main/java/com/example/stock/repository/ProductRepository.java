package com.example.stock.repository;

import com.example.stock.domain.Category;
import com.example.stock.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT obj FROM Product obj WHERE obj.name =:name")
    public Product findByName (@Param("name") String name);
}
