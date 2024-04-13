package com.example.stock.repository;

import com.example.stock.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT obj FROM Category obj WHERE obj.name =:name")
    public Category findByName (@Param("name") String name);
}
