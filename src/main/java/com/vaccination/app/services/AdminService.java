package com.vaccination.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vaccination.app.models.Admin;
import com.vaccination.app.repositories.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	
	private static final String adminCode = "Administrator@89642";
	
	public Admin registerAdmin(Admin admin) {
		if(!admin.getAdminCode().equals(adminCode)) {
			return null;
		}
		return adminRepository.registerAdmin(admin);
	}

	public Admin loginAdmin(Admin admin) {
		return adminRepository.loginAdmin(admin);
	}

}
