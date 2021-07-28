package com.vaccination.app.repositories;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import com.vaccination.app.models.VaccinationCentre;

@Repository
public class VaccinationCentreRepository {
	@PersistenceUnit(name="vaccination_project")
	EntityManagerFactory emf;

	public VaccinationCentre registerCentre(VaccinationCentre centre) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(centre);
			et.commit();
			em.close();
			return centre;			
		}catch(Exception e) {
			e.printStackTrace();
			et.rollback();
			return null;
		}
		
	}

	public ArrayList<VaccinationCentre> getAllCentres() {
		ArrayList<VaccinationCentre> centres = new ArrayList<VaccinationCentre>();
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<VaccinationCentre> tq = em.createQuery("SELECT c from VaccinationCentre c", 
            		VaccinationCentre.class);
            centres = (ArrayList<VaccinationCentre>) tq.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centres;
		
	}
}
