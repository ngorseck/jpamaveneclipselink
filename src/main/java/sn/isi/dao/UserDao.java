package sn.isi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sn.isi.entities.User;

public class UserDao implements IUser {

	private EntityManager em;
	
	public UserDao () {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpamaveneclipselink");
		em = emf.createEntityManager();
	}
	
	
	@Override
	public int save(User user) {
		
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public int delete(int id) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(User.class, id));
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int update(User user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<User> getAll() {
		
		return em.createQuery("SELECT u FROM User u").getResultList();
	}
	@Override
	public User getLogin(String email, String password) {
		
		return (User) em.createQuery("SELECT u FROM User u WHERE u.email = :em AND u.password = :pwd")
					.setParameter("em", email)
					.setParameter("pwd", password)
					.getSingleResult();
	}
	@Override
	public User get(int id) {
		
		return em.find(User.class, id);
	}

}
