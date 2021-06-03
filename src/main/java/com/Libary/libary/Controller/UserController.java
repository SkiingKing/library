package com.Libary.libary.Controller;

import com.Libary.libary.entity.Book;
import com.Libary.libary.service.BookService;
import com.Libary.libary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * The value is used to access book repository and operations
     */
    private final BookService bookService;
    /**
     * The value is used to access user repository and operations
     */
    private final UserService userService;

    @Autowired
    public UserController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    /**
     * Method searches books according text parameter
     * @param text used for search condition
     * @param model to add attributes finded books
     * @return page with results
     */
    @PostMapping("/search")
    public String search(@RequestParam(name = "text") String text, Model model) {
        List<Book> bookList = bookService.findByAuthorOrName(text);
        model.addAttribute("bookList", bookList);
        return "/user/result";
    }


}
