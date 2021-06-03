package com.Libary.libary.config;

import com.Libary.libary.Dto.BookDto;
import com.Libary.libary.entity.User;
import com.Libary.libary.service.BookService;
import com.Libary.libary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class OnStartRunner implements CommandLineRunner {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public OnStartRunner(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;

    }


    public void run(String... args) throws Exception {
        User librarian = User.builder()
                .password(bCryptPasswordEncoder().encode("librarian"))
                .email("librarian@mail.com")
                .build();
        userService.createLibrarian(librarian);

        User user = User.builder()
                .password(bCryptPasswordEncoder().encode("user"))
                .email("user@mail.com")
                .build();
        userService.createUser(user);

        BookDto bookDTO1 = new BookDto();
        bookDTO1.setName("Some text");
        bookDTO1.setAuthor("Liane Moriarty");
        bookDTO1.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO1.setCategory("Roman");
        bookService.saveBookFromDTO(bookDTO1);

        BookDto bookDTO2 = new BookDto();
        bookDTO2.setName("Adventure");
        bookDTO2.setAuthor("Tom Kruz");
        bookDTO2.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO2.setCategory("Adventure");
        bookService.saveBookFromDTO(bookDTO2);

        BookDto bookDTO3 = new BookDto();
        bookDTO3.setName("Kids");
        bookDTO3.setAuthor("Jastin");
        bookDTO3.setDescription("Dkdkjfnaskdfn dkjfksadjfk ssdifjsdjfis sdfisadfi ");
        bookDTO3.setCategory("Comedy");
        bookService.saveBookFromDTO(bookDTO3);

    }

    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
