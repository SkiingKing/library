package com.Libary.libary.Controller;

import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
@RequestMapping
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method provide get mapping to login
     *
     * @return login page
     */
    @GetMapping("/login")
    public String login() {
        logger.info("Login page visited");
        return "/login";
    }


    /**
     * Method provide get mapping to registration
     *
     * @return registration page
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userDTO", new UserDto());
        logger.info("Registration page visited");
        return "/registration";
    }

    /**
     * Method provide post mapping to registration
     *
     * @return login page
     */
    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String setRegistration(@Valid @ModelAttribute("userDTO") UserDto userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/registration";
        userService.createUser(userService.createUserFromDTO(userDTO));
        logger.warn("Registered user: " + userDTO.getEmail());
        return "/user/result";
    }

}
