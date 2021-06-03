package com.Libary.libary.repository;

import com.Libary.libary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByName(String name);


}
