package com.example.library.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category, String> {
}
