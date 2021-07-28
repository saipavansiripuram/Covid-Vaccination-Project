package com.vaccination.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vaccination.app.models.Admin;
import com.vaccination.app.models.User;

@Controller
public class CommonController {
	@RequestMapping("/")
	public String home(HttpSession session) {
		if(session.getAttribute("loggedUser") instanceof Admin) {
			return "redirect:/admin/dashboard";
		}else if(session.getAttribute("loggedUser") instanceof User) {
			return "redirect:/user/dashboard";
		}
		return "home";
	}
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
