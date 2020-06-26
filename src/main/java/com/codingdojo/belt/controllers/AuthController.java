package com.codingdojo.belt.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.belt.models.User;
import com.codingdojo.belt.services.UserService;
import com.codingdojo.belt.validators.UserValidator;


@Controller
public class AuthController {
	
	private final UserService userService;
	private final UserValidator userValidator;
	
	public AuthController(UserService userService, UserValidator userValidator) {
		this.userValidator= userValidator;
		this.userService= userService;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user, Model model) {
		return"loginreg.jsp";
	}
	
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user,  result);
		if(result.hasErrors()) {
//			error Handling
			System.out.println(result.getAllErrors());
			return "loginreg.jsp";
		} else {
			User u = userService.registerUser(user);
			Long userId= u.getId();
			session.setAttribute("userId", userId);
			System.out.println("user authenticated, ID is: "+ userId );
			return"redirect:/ideas";
		}
	}
	
//	@GetMapping("/home")
//	public String home(HttpSession session) {
//		if(session.getAttribute("userId")==null) {
//			return"redirect:/";
//		}else {
//			return "home.jsp";
//		}
//	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//													redirect to /index route  on successful authentication		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			Long userId = (Long)session.getAttribute("userId");
			System.out.println(userId);
			return "redirect:/ideas";
		}else {
			redirectAttributes.addFlashAttribute("loginError", "Enter valid credentials");
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		System.out.println(session.getAttribute("userId"));
		if(session.getAttribute("userId")==null) {
			return"redirect:/";
		}else {
			session.removeAttribute("userId");
			return "redirect:/"; 
		}
	}

}
