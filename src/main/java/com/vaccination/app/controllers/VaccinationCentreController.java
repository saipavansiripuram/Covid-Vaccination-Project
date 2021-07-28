package com.vaccination.app.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.vaccination.app.models.VaccinationCentre;
import com.vaccination.app.services.VaccinationCentreService;

@Controller
@RequestMapping("/centre")
public class VaccinationCentreController {
	
	@Autowired
	VaccinationCentreService vaccinationCentreService;
	
	@RequestMapping("/register")
	public String registerCentrePage(Model model) {
		VaccinationCentre vc = new VaccinationCentre();
		model.addAttribute("Centre", vc);
		return "register_centre";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerCentre(VaccinationCentre centre) {
		VaccinationCentre vc = vaccinationCentreService.registerCentre(centre);
		if(vc!=null) {
			return "redirect:/admin/dashboard";
			
		}else {
			return "redirect:/centre/register";
		}
	}
	
	@RequestMapping("/get/all")
	public String getAllCentres(Model model) {
		ArrayList<VaccinationCentre> list = vaccinationCentreService.getAllCentres();
		model.addAttribute("centre_list",list);
		if(list.isEmpty()) {
			model.addAttribute("isEmpty",true);
		}
		return "centre_list";
	}
}
