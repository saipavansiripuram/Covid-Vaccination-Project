package com.vaccination.app.repositories;
import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.vaccination.app.models.Admin;


@Repository
public class AdminRepository {
	@PersistenceUnit(name="vaccination_project")
	EntityManagerFactory emf;

	public Admin registerAdmin(Admin admin) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			
			et.begin();
			em.persist(admin);
			et.commit();
			em.close();
			return admin;
		}catch(Exception e) {
			et.rollback();
			e.printStackTrace();
			return null;
		}
		
	}

	public Admin loginAdmin(Admin admin) {
		EntityManager em=emf.createEntityManager();
        TypedQuery<Admin> tq = em.createQuery("SELECT a FROM Admin a WHERE a.aadhaar=:uname AND a.password=:pswd", Admin.class);
        tq.setParameter("uname",admin.getAadhaar());
        tq.setParameter("pswd",admin.getPassword());
        try{
            Admin result=tq.getSingleResult();// Should have exactly 1 result
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
		
		
	}
}
