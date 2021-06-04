package com.Libary.libary.service;

import com.Libary.libary.Dto.BookDto;
import com.Libary.libary.Dto.CategoryDto;
import com.Libary.libary.entity.Book;
import com.Libary.libary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findByAuthorOrName(String text) {
        return bookRepository.findAll().stream()
                .filter(o -> o.getAuthor().contains(text) || o.getName().contains(text))
                .collect(Collectors.toList());
    }

    public List<Book> findAll(){
        return bookRepository.findAll().stream().collect(Collectors.toList());
    }

    public Book saveBookFromDTO(BookDto bookDTO, CategoryDto categoryDto) {
        Book book = Book.builder()
                .name(bookDTO.getName())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .book_category(categoryDto.getName()).build();

        return bookRepository.save(book);
    }
}
