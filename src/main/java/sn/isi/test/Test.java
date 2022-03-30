package sn.isi.test;


import java.util.Iterator;

import sn.isi.dao.IUser;
import sn.isi.dao.UserDao;
import sn.isi.entities.User;

public class Test {
	
	public static void main(String[] args) {
		User user = new User();
		user.setNom("tes");
		user.setPrenom("tes");
		user.setEmail("test@ndiaye.sn");
		user.setPassword("passer1234#@!");
		
		IUser userdao = new UserDao();
		//int result = userdao.save(user);
		//System.out.println(result);
	
		
		user = userdao.get(3);
		user.setPassword("senegalcoupe");
		//userdao.update(user);
		//userdao.delete(4);
		try {
			user = userdao.getLogin("aziz@ndiaye.sn", "senegalcoupe");
			System.out.println(user.getPrenom());
		} catch (Exception e) {
			System.out.println("Cet utilisateur n'existe pas !");
		}
		
		//userdao.getAll()
		//.forEach(u -> System.out.println(u.getNom() + "  " + u.getPrenom() + "  " + u.getPassword()));
		
		/*for (User u : userdao.getAll()) {
			System.out.println(u.getNom() + "  " + u.getPrenom() + "  " + u.getPassword());
		} */
	}
	
	
	
	/*@Before
	public void initialiser() throws Exception {
	    personne = new Personne("nom1","prenom1");
	  }

	  @After
	  public void nettoyer() throws Exception {
	    personne = null;
	  }
	
	@Test
	public void saveUser() {
		
	    assertEquals("L'id est ", 1, personne.getNom());
	  }
	
	@BeforeAll
	static public void initStartingTime() {
		System.out.println("Appel avant tous les tests");
	}

	@AfterAll
	static public void showTestDuration() {
		System.out.println("Appel après tous les tests");
	}*/
}
