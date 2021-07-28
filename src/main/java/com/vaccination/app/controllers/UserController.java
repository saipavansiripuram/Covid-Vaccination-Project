package com.vaccination.app.controllers;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.vaccination.app.models.User;
import com.vaccination.app.models.VaccinationCentre;
import com.vaccination.app.services.UserService;
import com.vaccination.app.services.VaccinationCentreService;
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private VaccinationCentreService vaccinationCentreService;
	
	@RequestMapping("/login")
	public String loginPage(Model model,HttpSession session) {
		if(session.getAttribute("loggedUser") instanceof User) {
			return "redirect:/user/dashboard";
		}
		
		model.addAttribute("isUser",true);
		String error = (String)session.getAttribute("login_error");
		if(error!=null) {
			model.addAttribute("error",error);
			session.removeAttribute("login_error");
		}
		return "login";
		
	}
		
	@RequestMapping(path="/login",method=RequestMethod.POST) 
	public String loginUser(User user,HttpSession session,Model model) {
		User loggedUser = userService.loginUser(user);
		if(loggedUser!=null) {
			session.setAttribute("loggedUser", loggedUser);
			return "redirect:/user/dashboard";
		}else {
			return "redirect:/user/login";
		}
	}
	

	@RequestMapping("/register") // URL - localhost:8080/user/register Method - GET
	public ModelAndView registerPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		model.addObject("isUser",true);
		model.addObject("User",new User());
		return model;
	}
	
	@RequestMapping(path="/register",method=RequestMethod.POST)//URL - localhost:8080/user/register
	public String registerUser(Model model, User user) {
		User registeredUser = userService.registerUser(user);
		if(registeredUser!=null) {
			return "redirect:/user/login";
		}else {
			model.addAttribute("error", "Couldn't register user");
			model.addAttribute("isUser", true);
			model.addAttribute("User", new User());
			return "register";
		}
		
	}
	@RequestMapping("/dashboard")
	public String dashboardPage(Model model,HttpSession session) {
		User admin = (User)session.getAttribute("loggedUser");
		if(admin==null) {
			return "redirect:/user/login";
		}else {		
			return "user_dashboard";
		}
	}
	@RequestMapping("/get/all")
	public String userList(Model model) {
		ArrayList<User> list = userService.getAllUsers();
		model.addAttribute("user_list",list);
		if(list.isEmpty()) {
			model.addAttribute("isEmpty",true);
		}
		return "user_list";
		
	}
	@RequestMapping("/edit/{aadhaar}")
	public String editUserPage(@PathVariable("aadhaar")String aadhaar, Model model) {
		User user = userService.getUserByAadhaar(aadhaar);
		List<VaccinationCentre> available_centres = vaccinationCentreService.getAvailableCentres();
		if(user!=null) {
			model.addAttribute("User",user);
			ArrayList<String> statusList = new ArrayList<String>();
			statusList.add("WAITING");
			statusList.add("READY");
			statusList.add("VACCINATED");
			model.addAttribute("statusList",statusList);
			model.addAttribute("centreList",available_centres);
			return "edit_user";
		}else {
			return "redirect:/user/get/all";
		}
		
	}
}
