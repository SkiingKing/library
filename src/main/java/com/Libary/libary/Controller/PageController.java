package com.Libary.libary.Controller;

import com.Libary.libary.Dto.UserDto;
import com.Libary.libary.entity.Role;
import com.Libary.libary.entity.User;
import com.Libary.libary.exeption.UserNotFoundException;
import com.Libary.libary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;


@Controller
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
     * Method provide pos mapping to login
     *
     * @return welcome page
     */
    @PostMapping("/login_form")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "/user/result";
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

    @GetMapping("/accessDenied")
    public String access() {
        return "/accessDenied";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserNotFoundException.class})
    public String handleException() {
        return "/user/exception";
    }
}
