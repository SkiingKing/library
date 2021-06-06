package com.Libary.libary.Controller;

import com.Libary.libary.Dto.BookDto;
import com.Libary.libary.Dto.CategoryDto;
import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.entity.Book;
import com.Libary.libary.service.BookService;
import com.Libary.libary.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/librarian",  method = {RequestMethod.POST,RequestMethod.PATCH})
@PreAuthorize("hasAuthority('developers:write')")

public class LibrarianController {
    private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    private final CategoryService categoryService;

    private final BookService bookService;

    public LibrarianController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("/add_new_category")
    public String addNewCategory(Model model) {
        logger.info("Add new category page!");
        model.addAttribute("categoryDto", new CategoryDto());
        return "/librarian/add_category";
    }


    @PostMapping("/add_category")
    public String addCategory(@Valid @ModelAttribute("cetegoryDto") CategoryDto categoryDto, BindingResult bindingResult) {
        logger.info("Add new category!");
        if (bindingResult.hasErrors()) return "/user/result";
        categoryService.createCategory(categoryService.saveCategoryFromDTO(categoryDto));
        logger.warn("Category info: " + categoryDto.getName()+" ID: "+ categoryDto.getId());
        return "redirect:/user/result";
    }


    @GetMapping("/add_new_book")
    public String addNewBook(Model model) {
        logger.info("Add new book page!");
        model.addAttribute("list_of_category",categoryService.findAll());
        model.addAttribute("bookDto", new BookDto());
        return "/librarian/add_book";
    }


    @PostMapping("/add_book")
    public String addBook(@Valid @ModelAttribute("bookDto")
                                      BookDto bookDto, BindingResult bindingResult) {
        logger.info("Add new book!");
        if (bindingResult.hasErrors()) return "/user/result";
        bookService.createBook(bookService.saveBookFromDTO(bookDto));
        logger.warn("Book info: " + "name "+bookDto.getName()+" author "+bookDto.getAuthor()
                +" category "+bookDto.getBook_category());
        return "redirect:/user/result";
    }

      @GetMapping("/{id}/edit")
      public String edit(Model model,@PathVariable("id") String id){
        model.addAttribute("book",bookService.findBookById(Long.parseLong(id)));
        return "/librarian/edit_book";
      }

      @PatchMapping("/update/{id}")
        public String update(@ModelAttribute("book") Book book,
                             @PathVariable("id")Long id){
        bookService.update(id,book);
        return "redirect:/user/result";
      }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id){
        bookService.delete(Long.parseLong(id));
        return "redirect:/user/result";
    }



}
