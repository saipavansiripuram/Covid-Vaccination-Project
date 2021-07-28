package com.vaccination.app.repositories;

import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import com.vaccination.app.models.User;

@Repository
public class UserRepository {
	@PersistenceUnit(name="vaccination_project")
	EntityManagerFactory emf;
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(user);
			et.commit();
			em.close();
			return user;
		}catch(Exception e) {
			et.rollback();
			e.printStackTrace();
			return null;
		}
	}
	public User loginUser(User user) {
		EntityManager em=emf.createEntityManager();
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.aadhaar=:aadhnum AND u.password=:pswd", User.class);
        tq.setParameter("aadhnum",user.getAadhaar());
        tq.setParameter("pswd",user.getPassword());

        try{
            User result=tq.getSingleResult();// Should have exactly 1 result
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
		
	
	}
	public ArrayList<User> getAllUsers() {
		 ArrayList<User> users = new ArrayList<User>();
	        try {
	            EntityManager em = emf.createEntityManager();
	            TypedQuery<User> tq = em.createQuery("SELECT u from User u", User.class);
	            users = (ArrayList<User>) tq.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return users;
		
	}
	public User getUserByAadhaar(String aadhaar) {
		EntityManager em=emf.createEntityManager();
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.aadhaar=:aadhnum", User.class);
        tq.setParameter("aadhnum",aadhaar);
        try{
            User result=tq.getSingleResult();// Should have exactly 1 result
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }


	}
}
