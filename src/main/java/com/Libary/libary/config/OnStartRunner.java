package com.Libary.libary.config;

import com.Libary.libary.Dto.BookDto;
import com.Libary.libary.Dto.CategoryDto;
import com.Libary.libary.entity.Category;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.service.BookService;
import com.Libary.libary.service.CategoryService;
import com.Libary.libary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class OnStartRunner implements CommandLineRunner {

    private UserService userService;

    private BookService bookService;

    private CategoryService categoryService;

    @Autowired
    public OnStartRunner(UserService userService, BookService bookService,CategoryService categoryService) {
        this.userService = userService;
        this.bookService = bookService;
        this.categoryService = categoryService;

    }


    public void run(String... args) throws Exception {
        User librarian = User.builder()
                .password(bCryptPasswordEncoder().encode("librarian"))
                .email("librarian@mail.com")
                .role(Role.LIBRARIAN)
                .build();
        userService.createLibrarian(librarian);

        User user = User.builder()
                .password(bCryptPasswordEncoder().encode("user"))
                .email("user@mail.com")
                .role(Role.USER)
                .build();
        userService.createUser(user);

        CategoryDto categoryDto_1 = new CategoryDto();
        categoryDto_1.setId(1L);
        categoryDto_1.setName("Horror");
        categoryService.saveCategoryFromDTO(categoryDto_1);

        CategoryDto categoryDto_2 = new CategoryDto();
        categoryDto_2.setId(2L);
        categoryDto_2.setName("Adventure");
        categoryService.saveCategoryFromDTO(categoryDto_2);

        CategoryDto categoryDto_3 = new CategoryDto();
        categoryDto_3.setId(3L);
        categoryDto_3.setName("Drama");
        categoryService.saveCategoryFromDTO(categoryDto_3);

        BookDto bookDTO1 = new BookDto();
        bookDTO1.setName("Piter Pen");
        bookDTO1.setAuthor("Liane Moriarty");
        bookDTO1.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO1.setBook_category(categoryDto_1.getName());
        bookService.saveBookFromDTO(bookDTO1);

        BookDto bookDTO2 = new BookDto();
        bookDTO2.setName("Tre pigs");
        bookDTO2.setAuthor("Tom Kruz");
        bookDTO2.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO2.setBook_category(categoryDto_2.getName());
        bookService.saveBookFromDTO(bookDTO2);

        BookDto bookDTO3 = new BookDto();
        bookDTO3.setName("Red hat");
        bookDTO3.setAuthor("Jastin Helton");
        bookDTO3.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO3.setBook_category(categoryDto_3.getName());
        bookService.saveBookFromDTO(bookDTO3);

    }

    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
