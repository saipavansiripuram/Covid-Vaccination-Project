package com.vaccination.app.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.vaccination.app.models.Admin;
import com.vaccination.app.services.AdminService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/login")
	public String loginPage(Model model,HttpSession session) {
		if(session.getAttribute("loggedUser") instanceof Admin) {
			return "redirect:/admin/dashboard";
		}
		
		model.addAttribute("isAdmin",true);
		String error = (String)session.getAttribute("login_error");
		if(error!=null) {
			model.addAttribute("error",error);
			session.removeAttribute("error");
		}
		return "login";
		
	}
	@RequestMapping(path = "/login", method = RequestMethod.POST) 
	public String loginAdmin(Admin admin,HttpSession session,Model model) {
		Admin loggedAdmin = adminService.loginAdmin(admin);
		if(loggedAdmin!=null) {
			session.setAttribute("loggedUser", loggedAdmin);
			return "redirect:/admin/dashboard";
		}else {
			session.setAttribute("login_error", "Could not login. Please provide correct details");
			return "redirect:/admin/login";
		}
	}
	@RequestMapping("/register") // URL - localhost:8080/user/register Method - GET
	public ModelAndView registerPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("isAdmin",true);
		model.addObject("Admin",new Admin());
		return model;
	}
	
	@RequestMapping(path="/register",method=RequestMethod.POST)//URL - localhost:8080/user/register
	public String registerAdmin(Model model, Admin user) {
		Admin registeredAdmin = adminService.registerAdmin(user);
		if(registeredAdmin!=null) {
			return "redirect:/admin/login";
		}else {
			model.addAttribute("error", "Couldn't register user");
			model.addAttribute("isAdmin", true);
			model.addAttribute("Admin", new Admin());
			return "register";
		}
		
	}
	@RequestMapping("/dashboard")
	public String dashboardPage(Model model,HttpSession session) {
		Admin admin = (Admin)session.getAttribute("loggedUser");
		if(admin==null) {
			return "redirect:/admin/login";
		}else {		
			return "admin_dashboard";
		}
	}
	
}
