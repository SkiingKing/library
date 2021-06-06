package com.Libary.libary.Controller;

import com.Libary.libary.entity.Book;
import com.Libary.libary.entity.Category;
import com.Libary.libary.service.BookService;
import com.Libary.libary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * The value is used to access book repository and operations
     */
    private final BookService bookService;


    @Autowired
    public UserController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Method provide get mapping to result
     * @param model to add attributes booklist of book
     * @return result page
     */
    @GetMapping("/user/result")
    public String result(Model model) {
        logger.info("Result page visited");
        List<Book> bookList = bookService.findAll();
        Comparator<Book> NameComparator = Comparator.comparing(Book::getBook_category);
        Collections.sort(bookList,NameComparator);

        model.addAttribute("bookList", bookList);
        return "/user/result";
    }

    /**
     * Method searches books according text parameter
     * @param text used for search condition
     * @param model to add attributes booklist of book
     * @return page with results
     */

    @PostMapping("/search")
    public String search(@RequestParam(name = "text") String text, Model model) {
        List<Book> bookList = bookService.findByAuthorOrName(text);
        model.addAttribute("bookList", bookList);
        return "/user/result";
    }
}



