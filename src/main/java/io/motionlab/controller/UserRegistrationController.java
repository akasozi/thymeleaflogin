package io.motionlab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.motionlab.model.User;
import io.motionlab.repository.UserRepository;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping
	public String showRegistrationForm(User user) {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@Valid User user, BindingResult result, Model model) {
		
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		
		if (result.hasErrors()) {
			
			return "registration";
		}
		
		userRepository.save(user);
		return "redirect:/registration?success";
	}

}
