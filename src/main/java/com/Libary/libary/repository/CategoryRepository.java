package com.Libary.libary.repository;

import com.Libary.libary.entity.Book;
import com.Libary.libary.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
