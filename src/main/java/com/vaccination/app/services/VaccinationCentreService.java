package com.vaccination.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.models.VaccinationCentre;
import com.vaccination.app.repositories.VaccinationCentreRepository;
import com.vaccination.app.util.UtilityMethods;

@Service
public class VaccinationCentreService {
	@Autowired
	UtilityMethods utilityMethods;
	@Autowired
	VaccinationCentreRepository vaccinationCentreRepository;
	
	public VaccinationCentre registerCentre(VaccinationCentre centre) {
		centre.setLicense(utilityMethods.getLicenceKey());	
		return vaccinationCentreRepository.registerCentre(centre);
	}

	public ArrayList<VaccinationCentre> getAllCentres() {
		// TODO Auto-generated method stub
		return vaccinationCentreRepository.getAllCentres();
	}

	public List<VaccinationCentre> getAvailableCentres() {
		return vaccinationCentreRepository.getAllCentres().stream()
				.filter(centre->centre.getUser_count()<50).collect(Collectors.toList());		
	}
}
