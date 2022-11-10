package com.ba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ba.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
